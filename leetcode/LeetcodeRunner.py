#!/usr/bin/env python3
"""
LeetCode Runner (Java)

Pick a problem, write Solution.java in VS Code, then test and submit
from the terminal. Test cases that fail during Submit are saved
automatically and included in every later Test run. After submitting,
your submission page opens in the browser.

Standard library only. Run it from the folder you use in VS Code:

    python leetcode_runner.py
"""

import json
import os
import re
import shutil
import subprocess
import sys
import time
import webbrowser
from html.parser import HTMLParser
from pathlib import Path
from urllib import error, request

# ============================================================
# CONFIG
# ============================================================

ROOT = Path.cwd().resolve()

SOLUTION = ROOT / "Solution.java"
TESTS = ROOT / "tests"  # saved failed cases, one JSON file per problem
STATE_DIR = ROOT / ".lcrunner"
PROBLEM_FILE = STATE_DIR / "problem.json"
AUTH_FILE = STATE_DIR / "auth.json"  # contains your cookies: keep private

BASE = "https://leetcode.com"
API = BASE + "/graphql"
LANG = "java"

HTTP_TIMEOUT = 30
JUDGE_TIMEOUT = 90  # seconds to wait for a verdict

USER_AGENT = (
    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
    "AppleWebKit/537.36 (KHTML, like Gecko) "
    "Chrome/140.0 Safari/537.36"
)

WIDTH = 44


class AuthError(RuntimeError):
    """LeetCode rejected our login cookies."""


# ============================================================
# COLORS  (two hues only: blue and orange, in four shades each)
#
#   blue   = structure, labels, passing results
#   orange = accents, warnings, failing results
#
# Set NO_COLOR=1 to turn colors off.
# ============================================================

def _supports_color():
    if os.environ.get("NO_COLOR") or not sys.stdout.isatty():
        return False
    if os.name == "nt":
        os.system("")  # switches on ANSI escape handling in Windows terminals
    return True


USE_COLOR = _supports_color()

#          1 vivid  2 bright  3 soft  4 dim
BLUE = {1: 39, 2: 75, 3: 110, 4: 61}
ORANGE = {1: 208, 2: 214, 3: 180, 4: 137}


def paint(text, code, bold=False):
    if not USE_COLOR:
        return str(text)
    weight = "1;" if bold else ""
    return f"\033[{weight}38;5;{code}m{text}\033[0m"


def blue(text, shade=1, bold=False):
    return paint(text, BLUE[shade], bold)


def orange(text, shade=1, bold=False):
    return paint(text, ORANGE[shade], bold)


def paint_difficulty(level):
    shade = {"Easy": 4, "Medium": 2, "Hard": 1}.get(level, 3)
    return orange(level, shade, bold=(level == "Hard"))


def prompt_style(text):
    """Colored input prompt; \\001 \\002 keep readline's cursor math correct."""
    if not USE_COLOR:
        return text
    start, end = ("", "") if os.name == "nt" else ("\001", "\002")
    return f"{start}\033[1;38;5;{ORANGE[1]}m{end}{text}{start}\033[0m{end}"


# ============================================================
# SMALL HELPERS
# ============================================================

def clear():
    os.system("cls" if os.name == "nt" else "clear")


def ask(text):
    try:
        return input(prompt_style(f"{text}: ")).strip()
    except (EOFError, KeyboardInterrupt):
        print()
        return ""


def pause():
    try:
        input(blue("\nPress Enter to continue...", 4))
    except (EOFError, KeyboardInterrupt):
        pass


def banner(title):
    print(blue("=" * WIDTH, 4))
    print(blue(title.center(WIDTH), 1, bold=True))
    print(blue("=" * WIDTH, 4))
    print()


def rule():
    print(blue("-" * WIDTH, 4))


def indent(text, pad="  "):
    return "\n".join(pad + line for line in str(text).splitlines())


def short(text, limit=110):
    text = str(text).replace("\n", " | ")
    return text if len(text) <= limit else text[: limit - 3] + "..."


def safe_error(exc):
    return str(exc).strip() or f"{type(exc).__name__}: unknown error"


def remove_bom(text):
    return text[1:] if text.startswith("\ufeff") else text


def load_json(path, default=None):
    try:
        return json.loads(path.read_text(encoding="utf-8"))
    except (OSError, ValueError):
        return default


def save_json(path, data):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(json.dumps(data, indent=2, ensure_ascii=False), encoding="utf-8")


def squash(text):
    """Whitespace-insensitive form used to compare outputs."""
    return re.sub(r"\s+", "", str(text))


# ============================================================
# HTML -> TEXT (problem descriptions)
# ============================================================

class _TextExtractor(HTMLParser):
    BLOCKS = {"p", "div", "ul", "ol", "pre", "h1", "h2", "h3", "br"}

    def __init__(self):
        super().__init__(convert_charrefs=True)
        self.out = []

    def handle_starttag(self, tag, attrs):
        if tag == "li":
            self.out.append("\n  - ")
        elif tag == "sup":
            self.out.append("^")
        elif tag in self.BLOCKS:
            self.out.append("\n")

    def handle_endtag(self, tag):
        if tag in self.BLOCKS:
            self.out.append("\n")

    def handle_data(self, data):
        self.out.append(data)


def html_to_text(html):
    parser = _TextExtractor()
    parser.feed(html or "")
    text = "".join(parser.out).replace("\xa0", " ")
    text = re.sub(r"[ \t]+\n", "\n", text)
    return re.sub(r"\n{3,}", "\n\n", text).strip()


def colorize_description(text):
    """Highlight section headings inside a plain-text description."""
    out = []
    for line in text.splitlines():
        if re.match(r"^(Example \d+:|Constraints:|Follow[- ]?up:)", line):
            out.append(orange(line, 1, bold=True))
        elif line.lstrip().startswith(("Input:", "Output:", "Explanation:")):
            out.append(blue(line, 2))
        else:
            out.append(line)
    return "\n".join(out)


# ============================================================
# HTTP
# ============================================================

def http_json(url, payload=None, headers=None):
    data = json.dumps(payload).encode("utf-8") if payload is not None else None
    req = request.Request(url, data=data, method="POST" if data is not None else "GET")

    merged = {
        "Content-Type": "application/json",
        "Accept": "application/json",
        "User-Agent": USER_AGENT,
        "Referer": BASE + "/",
    }
    merged.update(headers or {})
    for key, value in merged.items():
        req.add_header(key, value)

    try:
        with request.urlopen(req, timeout=HTTP_TIMEOUT) as response:
            raw = response.read()
    except error.HTTPError as exc:
        if exc.code in (401, 403) and "Cookie" in merged:
            raise AuthError(
                f"LeetCode rejected your session (HTTP {exc.code}).\n"
                "Your cookies may have expired, or the request was blocked."
            )
        if exc.code == 403:
            raise RuntimeError("LeetCode denied the request (HTTP 403).")
        if exc.code == 429:
            raise RuntimeError("Rate limited by LeetCode (HTTP 429). Wait a bit and retry.")
        try:
            body = exc.read().decode("utf-8", errors="replace")[:300]
        except Exception:
            body = ""
        raise RuntimeError(f"LeetCode returned HTTP {exc.code}.\n{body}".strip())
    except error.URLError as exc:
        raise RuntimeError(
            f"Could not connect to LeetCode. Check your internet.\nDetails: {exc.reason}"
        )
    except OSError as exc:
        raise RuntimeError(f"Network error while contacting LeetCode.\nDetails: {exc}")

    try:
        return json.loads(raw.decode("utf-8", errors="replace"))
    except ValueError:
        raise RuntimeError("LeetCode returned a non-JSON response (possibly a bot check).")


def graphql(query, variables=None, operation=None):
    payload = {"query": query, "variables": variables or {}}
    if operation:
        payload["operationName"] = operation

    result = http_json(API, payload)

    errors = result.get("errors")
    if errors:
        messages = [
            str(e.get("message"))
            for e in errors
            if isinstance(e, dict) and e.get("message")
        ]
        raise RuntimeError("LeetCode GraphQL error:\n" + "\n".join(messages))

    return result.get("data") or {}


# ============================================================
# AUTH (cookies from your browser session)
# ============================================================

def prompt_cookies():
    print(blue("LeetCode login cookies are needed for Test and Submit.\n", 2))
    print(orange("How to get them:", 1, bold=True))
    print(blue("  1. Log in to leetcode.com in your browser", 3))
    print(blue("  2. Press F12 -> Application (Chrome/Edge) or Storage (Firefox)", 3))
    print(blue("  3. Cookies -> https://leetcode.com", 3))
    print(blue("  4. Copy the values of LEETCODE_SESSION and csrftoken\n", 3))
    print(blue("They are saved only in .lcrunner/auth.json (do not share or commit it).\n", 4))

    session = ask("LEETCODE_SESSION").strip("\"'")
    csrf = ask("csrftoken").strip("\"'")

    if not session or not csrf:
        raise RuntimeError("Login cancelled: both cookies are required.")

    auth = {"session": session, "csrf": csrf}
    save_json(AUTH_FILE, auth)
    try:
        os.chmod(AUTH_FILE, 0o600)
    except OSError:
        pass
    return auth


def has_saved_auth():
    auth = load_json(AUTH_FILE)
    return bool(auth and auth.get("session") and auth.get("csrf"))


def ensure_auth():
    auth = load_json(AUTH_FILE)
    if auth and auth.get("session") and auth.get("csrf"):
        return auth

    auth = prompt_cookies()
    print(blue("\nSaved.\n", 2))
    return auth


def clear_auth():
    try:
        AUTH_FILE.unlink()
    except OSError:
        pass


def auth_headers(slug):
    auth = ensure_auth()
    return {
        "Cookie": f"LEETCODE_SESSION={auth['session']}; csrftoken={auth['csrf']}",
        "x-csrftoken": auth["csrf"],
        "Origin": BASE,
        "Referer": f"{BASE}/problems/{slug}/",
    }


def verify_login(auth):
    """Return the LeetCode username if the cookies are valid, else None."""
    headers = {
        "Cookie": f"LEETCODE_SESSION={auth['session']}; csrftoken={auth['csrf']}",
        "x-csrftoken": auth["csrf"],
        "Origin": BASE,
    }
    payload = {
        "operationName": "globalData",
        "query": "query globalData { userStatus { isSignedIn username } }",
        "variables": {},
    }
    result = http_json(API, payload, headers)
    status = (result.get("data") or {}).get("userStatus") or {}
    return status.get("username") if status.get("isSignedIn") else None


def account():
    clear()
    banner("LOGIN / ACCOUNT")

    if has_saved_auth():
        print(blue("Saved cookies found. Checking with LeetCode...\n", 3))
        try:
            name = verify_login(load_json(AUTH_FILE))
        except AuthError:
            name = None

        if name:
            print(blue("Logged in as: ", 3) + orange(name, 1, bold=True))
        else:
            print(orange("Cookies are saved, but LeetCode says you are NOT signed in.", 1))
            print(orange("They have probably expired. Log in again.", 3))
        print()
        print(orange("[1]", 1, True) + blue(" Log in again (replace cookies)", 2))
        print(orange("[2]", 1, True) + blue(" Log out (delete saved cookies)", 2))
        print(orange("[3]", 1, True) + blue(" Back\n", 2))
    else:
        print(orange("You are not logged in.\n", 2))
        print(orange("[1]", 1, True) + blue(" Log in", 2))
        print(orange("[3]", 1, True) + blue(" Back\n", 2))

    choice = ask("Choose")

    if choice == "1":
        print()
        auth = prompt_cookies()
        print(blue("\nChecking...", 3))
        try:
            name = verify_login(auth)
        except AuthError:
            name = None
        if name:
            print(blue("Login successful. Signed in as: ", 2) + orange(name, 1, bold=True))
        else:
            print(orange("Cookies saved, but LeetCode did not accept them.", 1))
            print(orange("Re-copy both values from your browser and try again.", 3))
    elif choice == "2" and has_saved_auth():
        clear_auth()
        print(blue("\nLogged out. Saved cookies deleted.", 2))


# ============================================================
# PROBLEM LOOKUP
# ============================================================

def get_slug(value):
    value = value.strip()
    if not value:
        return None

    match = re.search(r"leetcode\.com/problems/([^/?#]+)", value, re.IGNORECASE)
    if match:
        return match.group(1).lower()

    if value.isdigit():
        return find_slug_by_number(value)

    if re.fullmatch(r"[A-Za-z0-9-]+", value):
        return value.lower()

    return None


def _match_number(questions, target):
    for q in questions or []:
        fid = str(q.get("questionFrontendId", "")).strip().lstrip("0") or "0"
        if fid == target and q.get("titleSlug"):
            return q["titleSlug"]
    return None


def _search_v2(target):
    query = """
    query problemsetQuestionListV2(
        $limit: Int, $skip: Int, $searchKeyword: String, $categorySlug: String
    ) {
        problemsetQuestionListV2(
            limit: $limit, skip: $skip,
            searchKeyword: $searchKeyword, categorySlug: $categorySlug
        ) {
            questions { titleSlug questionFrontendId }
        }
    }
    """
    data = graphql(
        query,
        {"limit": 100, "skip": 0, "searchKeyword": target,
         "categorySlug": "all-code-essentials"},
        "problemsetQuestionListV2",
    )
    block = data.get("problemsetQuestionListV2") or {}
    return _match_number(block.get("questions"), target)


def _search_v1(target):
    query = """
    query problemsetQuestionList(
        $categorySlug: String, $limit: Int, $skip: Int,
        $filters: QuestionListFilterInput
    ) {
        problemsetQuestionList: questionList(
            categorySlug: $categorySlug, limit: $limit,
            skip: $skip, filters: $filters
        ) {
            questions: data { questionFrontendId titleSlug }
        }
    }
    """
    data = graphql(
        query,
        {"categorySlug": "", "limit": 50, "skip": 0,
         "filters": {"searchKeywords": target}},
        "problemsetQuestionList",
    )
    block = data.get("problemsetQuestionList") or {}
    return _match_number(block.get("questions"), target)


def find_slug_by_number(number):
    target = number.lstrip("0") or "0"
    print(blue(f"Searching problem #{target}...", 3))

    last_error = None
    for attempt in (_search_v2, _search_v1):
        try:
            slug = attempt(target)
        except RuntimeError as exc:
            last_error = exc
            continue
        if slug:
            print(blue(f"Found: {slug}", 2))
            return slug

    if last_error:
        raise last_error
    return None


def find_java_code(snippets):
    for snippet in snippets or []:
        if not isinstance(snippet, dict):
            continue
        if str(snippet.get("langSlug", "")).lower() == "java":
            code = snippet.get("code")
            if code:
                return remove_bom(str(code))
    return None


def download_problem(slug):
    query = """
    query questionData($titleSlug: String!) {
        question(titleSlug: $titleSlug) {
            questionId
            questionFrontendId
            title
            titleSlug
            difficulty
            content
            isPaidOnly
            sampleTestCase
            exampleTestcases
            metaData
            codeSnippets { langSlug lang code }
        }
    }
    """
    data = graphql(query, {"titleSlug": slug}, "questionData")
    q = data.get("question")
    if not q:
        return None

    java_code = find_java_code(q.get("codeSnippets"))
    if not java_code:
        if q.get("isPaidOnly"):
            raise RuntimeError("This is a Premium problem; LeetCode did not return its code.")
        raise RuntimeError("LeetCode did not return Java starter code for this problem.")

    params = []
    try:
        meta = json.loads(q.get("metaData") or "{}")
        params = [f"{p['name']} ({p['type']})" for p in meta.get("params", [])]
    except (ValueError, KeyError, TypeError):
        pass

    sample = str(q.get("sampleTestCase") or "")
    return {
        "question_id": str(q.get("questionId", "")),
        "number": str(q.get("questionFrontendId", "")),
        "title": str(q.get("title", "")),
        "slug": str(q.get("titleSlug", slug)),
        "difficulty": str(q.get("difficulty", "")),
        "content": q.get("content") or "",
        "sample": sample,
        "examples": str(q.get("exampleTestcases") or sample),
        "params": params,
        "java_code": java_code,
    }


# ============================================================
# STATE / FILES
# ============================================================

def load_current_problem():
    return load_json(PROBLEM_FILE)


def save_current_problem(problem):
    stored = {k: v for k, v in problem.items() if k != "java_code"}
    save_json(PROBLEM_FILE, stored)


def require_problem():
    problem = load_current_problem()
    if not problem:
        print(orange("No problem selected. Use 'Change Problem' first.", 2))
    return problem


def read_solution():
    if not SOLUTION.exists():
        raise RuntimeError("Solution.java does not exist. Select a problem first.")
    code = remove_bom(SOLUTION.read_text(encoding="utf-8"))
    if not code.strip():
        raise RuntimeError("Solution.java is empty.")
    return code


def params_per_case(problem):
    return max(1, len(problem.get("sample", "").splitlines()))


def split_cases(text, per):
    lines = text.strip("\n").split("\n") if text.strip() else []
    return ["\n".join(lines[i:i + per]) for i in range(0, len(lines), per)]


# ------------------------------------------------------------
# Failed cases (captured automatically when a submission fails)
# ------------------------------------------------------------

def failed_cases_file(problem):
    return TESTS / f"{problem['number']}-{problem['slug']}.json"


def load_failed_cases(problem):
    return load_json(failed_cases_file(problem), []) or []


def save_failed_case(problem, res):
    """Store the case that broke a submission. Returns 'new', 'exists' or None."""
    inp = (res.get("last_testcase") or "").strip("\n")
    if not inp or res.get("compile_error") or res.get("full_compile_error"):
        return None

    expected = str(res.get("expected_output") or "")
    cases = load_failed_cases(problem)
    key = squash(inp)

    for case in cases:
        if squash(case["input"]) == key:
            if expected and not case.get("expected"):
                case["expected"] = expected
                save_json(failed_cases_file(problem), cases)
            return "exists"

    cases.append({
        "input": inp,
        "expected": expected,
        "verdict": res.get("status_msg", ""),
        "saved_at": time.strftime("%Y-%m-%d %H:%M"),
    })
    save_json(failed_cases_file(problem), cases)
    return "new"


# ============================================================
# JUDGE (Run / Submit through LeetCode)
# ============================================================

def _judge(url, problem, body):
    headers = auth_headers(problem["slug"])
    started = http_json(url, body, headers)

    job_id = started.get("interpret_id") or started.get("submission_id")
    if not job_id:
        raise RuntimeError(f"LeetCode did not accept the request:\n{json.dumps(started)[:300]}")

    check_url = f"{BASE}/submissions/detail/{job_id}/check/"
    deadline = time.time() + JUDGE_TIMEOUT

    while time.time() < deadline:
        time.sleep(1)
        print(blue(".", 3), end="", flush=True)
        result = http_json(check_url, headers=headers)
        if result.get("state") == "SUCCESS":
            print()
            return job_id, result

    print()
    raise RuntimeError("Timed out waiting for LeetCode's verdict.")


def judge_run(problem, code, data_input):
    url = f"{BASE}/problems/{problem['slug']}/interpret_solution/"
    body = {
        "data_input": data_input,
        "lang": LANG,
        "question_id": int(problem["question_id"]),
        "test_mode": False,
        "typed_code": code,
        "judge_type": "large",
    }
    return _judge(url, problem, body)[1]


def judge_submit(problem, code):
    url = f"{BASE}/problems/{problem['slug']}/submit/"
    body = {
        "lang": LANG,
        "question_id": int(problem["question_id"]),
        "test_mode": False,
        "typed_code": code,
        "judge_type": "large",
    }
    return _judge(url, problem, body)


def print_errors(res):
    """Print compile/runtime errors. Returns True if any were shown."""
    shown = False
    compile_err = res.get("full_compile_error") or res.get("compile_error")
    runtime_err = res.get("full_runtime_error") or res.get("runtime_error")

    if compile_err:
        print(orange("COMPILE ERROR\n", 1, bold=True))
        print(orange(compile_err, 3))
        shown = True
    elif runtime_err:
        print(orange("RUNTIME ERROR\n", 1, bold=True))
        print(orange(runtime_err, 3))
        shown = True

    if shown and res.get("last_testcase"):
        print(blue("\nLast test case:", 3))
        print(indent(short(res["last_testcase"], 300)))
    return shown


# ============================================================
# ACTIONS
# ============================================================

def test():
    clear()
    banner("TEST")

    problem = require_problem()
    if not problem:
        return

    code = read_solution()
    per = params_per_case(problem)

    cases = []  # (label, input, expected override or None, is_failed_case)
    for i, inp in enumerate(split_cases(problem.get("examples", ""), per), 1):
        cases.append((f"Example {i}", inp, None, False))
    for i, saved in enumerate(load_failed_cases(problem), 1):
        cases.append((f"Failed {i}", saved["input"], saved.get("expected") or None, True))

    if not cases:
        print(orange("This problem has no example test cases.", 2))
        return

    extra = sum(1 for c in cases if c[3])
    note = f" (+{extra} saved from failed submissions)" if extra else ""
    print(blue(f"Running {len(cases)} test case(s){note}", 2), end="", flush=True)
    print(blue(" ", 3), end="")
    res = judge_run(problem, code, "\n".join(c[1] for c in cases))
    print()

    if print_errors(res):
        return

    if not res.get("run_success") and res.get("status_msg"):
        print(orange(f"Result: {res['status_msg']}", 1, bold=True))
        return

    got_list = res.get("code_answer") or []
    exp_list = res.get("expected_code_answer") or []
    out_list = res.get("std_output_list") or []
    passed = 0

    for i, (label, inp, expected_override, is_failed) in enumerate(cases):
        got = got_list[i] if i < len(got_list) else "(no output)"
        if expected_override is not None:
            expected = expected_override
        else:
            expected = exp_list[i] if i < len(exp_list) else "?"

        ok = squash(got) == squash(expected)
        passed += ok

        tag = "PASS" if ok else "FAIL"
        head = f"[{tag}] {label}"
        print(blue(head, 1, True) if ok else orange(head, 1, True))
        print(blue("  Input   : ", 3) + short(inp))
        print(blue("  Output  : ", 3) + (blue(got, 2) if ok else orange(got, 2)))
        print(blue("  Expected: ", 3) + blue(expected, 2))
        if i < len(out_list) and out_list[i]:
            print(blue("  Stdout  : ", 3) + str(out_list[i]).strip())
        print()

    rule()
    summary = f"{passed}/{len(cases)} passed"
    print(blue(summary, 1, True) if passed == len(cases) else orange(summary, 1, True))
    if res.get("status_runtime"):
        print(blue(f"Runtime: {res['status_runtime']}", 3))


def submit():
    clear()
    banner("SUBMIT")

    problem = require_problem()
    if not problem:
        return

    code = read_solution()

    print(blue(f"#{problem['number']} - {problem['title']}", 1, True))
    if ask("Submit to LeetCode now? (Y/N)").lower() != "y":
        return

    print(blue("\nSubmitting", 2), end="", flush=True)
    print(blue(" ", 3), end="")
    submission_id, res = judge_submit(problem, code)
    print()

    status = res.get("status_msg", "Unknown")
    total = res.get("total_testcases")
    accepted = status == "Accepted"
    tone = blue if accepted else orange

    print(tone("=" * WIDTH, 4))
    print(tone(f"Verdict: {status}", 1, True))
    print(tone("=" * WIDTH, 4))

    if total is not None:
        print(blue("Test cases: ", 3) + f"{res.get('total_correct')}/{total} passed")

    if accepted:
        for label, value, pct in (
            ("Runtime", res.get("status_runtime"), res.get("runtime_percentile")),
            ("Memory", res.get("status_memory"), res.get("memory_percentile")),
        ):
            if value:
                extra = f"  (beats {pct:.2f}%)" if isinstance(pct, (int, float)) else ""
                print(blue(f"{label}: ", 3) + blue(f"{value}{extra}", 1, True))
    else:
        if not print_errors(res):
            if res.get("last_testcase"):
                print(blue("\nFailed on:", 3))
                print(indent(short(res["last_testcase"], 300)))
            if res.get("expected_output") is not None:
                print(blue("\nExpected: ", 3) + blue(res.get("expected_output"), 2))
                print(blue("Output  : ", 3) + orange(res.get("code_output"), 2))

        outcome = save_failed_case(problem, res)
        if outcome == "new":
            print(orange("\nSaved this failing case. It will run with every Test from now on.", 2))
        elif outcome == "exists":
            print(blue("\nThis failing case is already saved in your Test set.", 3))

    page = f"{BASE}/problems/{problem['slug']}/submissions/{submission_id}/"
    print(blue("\nSubmission page:", 3))
    print(orange(page, 2))
    print(blue("\nOpening it in your browser...", 4))
    if not webbrowser.open(page):
        print(orange("Could not open a browser automatically. Open the link above.", 3))


def failed_cases():
    clear()
    banner("FAILED CASES")

    problem = require_problem()
    if not problem:
        return

    cases = load_failed_cases(problem)
    if not cases:
        print(blue("None yet.", 2))
        print(blue("Cases that fail during Submit are saved here automatically", 4))
        print(blue("and run with every Test.", 4))
        return

    for i, case in enumerate(cases, 1):
        print(orange(f"#{i}", 1, True) + blue(f"  {case.get('verdict', '')}  {case.get('saved_at', '')}", 4))
        print(blue("  Input   : ", 3) + short(case["input"]))
        print(blue("  Expected: ", 3) + (case.get("expected") or blue("(taken from LeetCode when tested)", 4)))
        print()

    print(orange("[1]", 1, True) + blue(" Delete one", 2))
    print(orange("[2]", 1, True) + blue(" Clear all", 2))
    print(orange("[3]", 1, True) + blue(" Back\n", 2))

    choice = ask("Choose")
    if choice == "1":
        raw = ask("Number to delete")
        if raw.isdigit() and 1 <= int(raw) <= len(cases):
            cases.pop(int(raw) - 1)
            save_json(failed_cases_file(problem), cases)
            print(blue("Deleted.", 2))
        else:
            print(orange("Nothing deleted.", 3))
    elif choice == "2":
        if ask("Delete all saved failed cases? (Y/N)").lower() == "y":
            save_json(failed_cases_file(problem), [])
            print(blue("Cleared.", 2))


def show_description():
    clear()
    problem = require_problem()
    if not problem:
        return

    banner(f"#{problem['number']} {problem['title']}")
    print(blue("Difficulty: ", 3) + paint_difficulty(problem["difficulty"]) + "\n")

    if not problem.get("content"):
        print(orange("No description available (Premium problem?).", 2))
        print(blue(f"{BASE}/problems/{problem['slug']}/", 3))
        return

    print(colorize_description(html_to_text(problem["content"])))


def open_solution():
    if not SOLUTION.exists():
        print(orange("Solution.java does not exist. Select a problem first.", 2))
        return

    code = shutil.which("code")
    if code:
        try:
            subprocess.Popen([code, str(ROOT), "-g", str(SOLUTION)])
            print(blue("Opened in VS Code.", 2))
            return
        except OSError:
            pass

    if os.name == "nt":
        try:
            os.startfile(str(SOLUTION))  # type: ignore[attr-defined]
            return
        except OSError:
            pass

    print(orange("Could not open VS Code. Make sure the 'code' command is in your PATH.", 2))


def change_problem():
    clear()
    banner("SELECT LEETCODE PROBLEM")

    print(blue("Enter a problem number, URL, or slug.\n", 2))
    print(blue("Examples:  1   69   two-sum", 3))
    print(blue("           https://leetcode.com/problems/sqrtx/\n", 3))

    value = ask("Problem")
    if not value:
        return

    slug = get_slug(value)
    if not slug:
        print(orange("\nCould not find that problem.", 2))
        return

    print(blue("\nLoading problem...", 3))
    problem = download_problem(slug)
    if not problem:
        print(orange("\nProblem was not found.", 2))
        return

    SOLUTION.write_text(problem["java_code"], encoding="utf-8")
    save_current_problem(problem)

    print()
    banner("PROBLEM READY")
    print(blue(f"#{problem['number']} - {problem['title']}", 1, True))
    print(blue("Difficulty: ", 3) + paint_difficulty(problem["difficulty"]) + "\n")
    print(blue("Solution.java has been replaced.\n", 3))

    saved = len(load_failed_cases(problem))
    if saved:
        print(orange(f"{saved} failed case(s) from earlier submissions will run in Test.\n", 2))

    if ask("Open in VS Code? (Y/N)").lower() == "y":
        open_solution()


# ============================================================
# MENU
# ============================================================

def menu_item(number, label):
    return orange(f"[{number}]", 1, True) + " " + blue(label, 2)


def menu():
    actions = {
        "1": test,
        "2": submit,
        "3": failed_cases,
        "4": show_description,
        "5": open_solution,
        "6": change_problem,
        "7": account,
    }

    while True:
        clear()
        print(blue("=" * WIDTH, 4))
        print(blue("LEETCODE RUNNER".center(WIDTH), 1, True))
        print(blue("=" * WIDTH, 4))
        print()

        current = load_current_problem() or {}
        number, title = current.get("number", ""), current.get("title", "")
        print(blue("Problem   : ", 3) + blue(f"#{number} - {title}", 1, True))
        print(blue("Difficulty: ", 3) + paint_difficulty(current.get("difficulty", "")))
        print(blue("Language  : ", 3) + "Java")
        logged = has_saved_auth()
        print(blue("Login     : ", 3) + (blue("cookies saved", 2) if logged else orange("not logged in", 2)))
        if current:
            saved = len(load_failed_cases(current))
            if saved:
                print(blue("Failed    : ", 3) + orange(f"{saved} saved case(s)", 2))
        print()
        rule()
        print()
        print(menu_item(1, "Test"))
        print(menu_item(2, "Submit"))
        print(menu_item(3, "Failed Cases"))
        print(menu_item(4, "Show Description"))
        print(menu_item(5, "Open Solution.java"))
        print(menu_item(6, "Change Problem"))
        print(menu_item(7, "Login / Account"))
        print(menu_item(8, "Exit"))
        print()

        choice = ask("Choose")

        if choice == "8":
            return

        action = actions.get(choice)
        if not action:
            print(orange("\nInvalid choice.", 2))
            pause()
            continue

        try:
            action()
        except AuthError as exc:
            clear_auth()
            print(orange(f"\n{safe_error(exc)}", 2))
            print(blue("Saved cookies were removed. You'll be asked for new ones next time.", 3))
        except KeyboardInterrupt:
            print(orange("\nCancelled.", 2))
        except Exception as exc:
            print()
            print(orange("=" * WIDTH, 4))
            print(orange("ERROR", 1, True))
            print(orange("=" * WIDTH, 4))
            print(f"\n{safe_error(exc)}")

        pause()


def main():
    TESTS.mkdir(parents=True, exist_ok=True)
    STATE_DIR.mkdir(parents=True, exist_ok=True)

    try:
        menu()
    except KeyboardInterrupt:
        print("\nExiting...")


if __name__ == "__main__":
    main()
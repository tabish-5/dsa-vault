package Day01;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        boolean ans = true;
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch>='0' && ch<='9'))
                sb.append(ch);
            // else if
            //     return false;
        }
        int len = sb.toString().length();
        String str = sb.toString().toLowerCase();
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1))
                return false;
        }
        return ans;
    }
}

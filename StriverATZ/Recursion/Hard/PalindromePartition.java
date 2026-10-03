package StriverATZ.Recursion.Hard;

import java.util.ArrayList;
import java.util.HashSet;
// import java.util.HashSet;
import java.util.List;
// import java.util.Set;
import java.util.Set;

public class PalindromePartition {
    List<List<String>> res= new ArrayList<>();
    public List<List<String>> partition(String s) {
        solve(s, new ArrayList<>(), 0);
        return res;
    }
    private void solve(String s, ArrayList<String> temp, int start){
        if(start==s.length()){
            res.add(new ArrayList<>(temp));
            return;
        }
        for(int i=start;i<s.length();i++){
            if(isPalindrome(s,start,i)){
                temp.add(s.substring(start,i+1));
                solve(s,temp,i+1);
                temp.remove(temp.size()-1);
            }
        }
    }
    private boolean isPalindrome(String s, int start, int end){
        while(start<=end){
            if(s.charAt(start)!=s.charAt(end)) return false;
            start++;
            end--;
        }
        return true;
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        for (int i = 1; i <= n; i++) {
            for (int len = 1; len <= maxLen && len <= i; len++) {
                if (dp[i - len] && dict.contains(s.substring(i - len, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }


    public static void main(String[] args) {
        PalindromePartition pp = new PalindromePartition();
        String s = "leetcode";
        List<String> ss = new ArrayList<>();
        ss.add("code");
        ss.add("leet");
        System.out.println(pp.wordBreak(s, ss));
    }

}

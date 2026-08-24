package StriverATZ.Strings.Medium;

public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s.length() <2) return s;
        int low, high;
        String ans ="";

        for(int i =0; i<s.length()-1; i++){
            //odd
            low = i -1;
            high = i +1;
            while(low >=0 && high < s.length()){
                if(s.charAt(low) != s.charAt(high)) break;
                low--;
                high++;
            }
            if(high -low > ans.length()) ans = s.substring(low+1, high);


            //even
            if(i+1 <s.length() && s.charAt(i) != s.charAt(i+1)) continue;
            low = i -1;
            high = i +2;
            while(low >=0 && high < s.length()){
                if(s.charAt(low) != s.charAt(high)) break;
                low--;
                high++;
            }
            if(high -low > ans.length()) ans = s.substring(low+1, high);

        }



        return ans;
    }
}

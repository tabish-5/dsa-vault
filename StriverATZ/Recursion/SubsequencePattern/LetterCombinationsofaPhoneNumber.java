package StriverATZ.Recursion.SubsequencePattern;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsofaPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        String[] set ={"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        // StringBuilder sb = new StringBuilder();
        
        helper(0, digits, set, new StringBuilder(), result);

        return result;
    }

    private void helper(int index, String digits, String[] set, StringBuilder sb, List<String> result){
        //base case
        if(index >= digits.length()){
            result.add(sb.toString());
            return;
        }
        int ele = digits.charAt(index)-'0';

        for(int i = 0; i < set[ele].length(); i++){
            sb.append(set[ele].charAt(i));
            helper(index +1, digits, set, sb, result);
            sb.deleteCharAt(index);
        }
    }
}

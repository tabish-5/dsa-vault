package StriverATZ.Recursion.SubsequencePattern;

import java.util.ArrayList;
import java.util.List;

public class GenerateParanthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(n, 0, 0, "", result);
        return result;
    }
    static void generate(int n, int l, int r, String curr, List<String> result) {
        if (curr.length() == 2 *n) {
            // for(String s :result){
            //     if(s.equals(curr)) return;
            // }
            result.add(curr);
            return;
        }
        if( r > l) return;
        
        if(l == n){
            generate(n, l, r+1, curr + ")", result);
            return;
        } 

        generate(n, l+1, r, curr + "(", result);
        generate(n, l, r+1, curr + ")", result);

        // if (curr.isEmpty() || curr.charAt(curr.length() - 1) != '1') {
        //     generate(n, curr + "1", result);
        // }
    }
}

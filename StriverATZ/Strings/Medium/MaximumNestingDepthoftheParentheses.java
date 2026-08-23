package StriverATZ.Strings.Medium;

public class MaximumNestingDepthoftheParentheses {
    public int maxDepth(String s) {
        int count = 0;
        int max = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
                max = Math.max(count,max);
            } else if(c == ')') {
                count--;
            }
        }

        return max;
    }
}

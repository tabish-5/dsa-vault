package StriverATZ.Recursion.Hard;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        String oprtr = "*/+-";
        createStringPC(oprtr, num, target, result, new StringBuilder());
        return result;
    }

    void createStringPC(String op, String num, int target, List<String> result, StringBuilder sb){
        //base case
        if (sb.length() == num.length()-1) {
            calculate(sb.toString(), num, target, result);
            return;
        }

        int len;
        for (int i = 0; i < 4; i++) {
            len = sb.length();
            sb.append(op.charAt(i));
            createStringPC(op, num, target, result, sb);
            sb.setLength(len);
        }
    }

    void calculate(String fop, String num, int target, List<String> result){
        StringBuilder sb = new StringBuilder();
        sb.append(num.charAt(0));
        int ans = num.charAt(0) -'0';
        for (int i = 0; i < fop.length(); i++) {
            sb.append(fop.charAt(i));
            sb.append(num.charAt(i +1));
            switch (fop.charAt(i) -'*') {
                case 0:
                    ans *= num.charAt(i +1) -'0';
                    break;
                case 1:
                    ans += num.charAt(i +1) -'0';
                    break;
                case 3:
                    ans -= num.charAt(i +1) -'0';
                    break;
                case 5:
                    if (num.charAt(i +1) == '0') {
                        return;
                    }
                    ans /= num.charAt(i +1) -'0';
                    break;
            }
        }
        if (ans == target) {
            result.add(sb.toString());
        }
    }
}

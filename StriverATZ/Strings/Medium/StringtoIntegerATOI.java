package StriverATZ.Strings.Medium;

public class StringtoIntegerATOI {
    public int myAtoi(String s) {
        int i = 0, sign = 1, num = 0;

        while (i < s.length() && s.charAt(i) == ' ') i++;

        if (i < s.length() && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = s.charAt(i++) == '-' ? -1 : 1;
        }

        while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            int d = s.charAt(i++) - '0';

            if (num > (Integer.MAX_VALUE - d) / 10)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            num = num * 10 + d;
        }

        return num * sign;
    }
}

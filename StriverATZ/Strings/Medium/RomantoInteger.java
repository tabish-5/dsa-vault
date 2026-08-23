package StriverATZ.Strings.Medium;

public class RomantoInteger {
    public int romanToInt(String s) {
        int I = 1;
        int V = 5;
        int X = 10;
        int L = 50;
        int C = 100;
        int D = 500;
        int M = 1000;
        int input[] = new int[s.length()];

        for(int i = 0; i<input.length; i++){
            char c = s.charAt(i);
            if (c == 'I') input[i] = I;
            else if (c == 'V') input[i] = V;
            else if (c == 'X') input[i] = X;
            else if (c == 'L') input[i] = L;
            else if (c == 'C') input[i] = C;
            else if (c == 'D') input[i] = D;
            else if (c == 'M') input[i] = M;
        }

        int sum = 0;
        
        for(int i = 0; i<input.length; i++){
            if(i < input.length-1 && input[i] < input[i + 1]){
                sum -= input[i];
            }
            else{
                sum += input[i];
            }
        }
        return sum;
    }
}

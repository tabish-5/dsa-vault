package Day01;
public class ReverseInteger {
    
    public int reverse(int x) {
        boolean check = true;
        int ans = 0;
        if (x < 0)
            x = x * -1;
        else
            check = false;
        while (x > 0) {
            if (ans > Integer.MAX_VALUE / 10)
                return 0;
            else if (ans * 10 > Integer.MAX_VALUE - x % 10)
                return 0;
            ans = (ans * 10) + (x % 10);
            x /= 10;
        }
        if (check)
            ans *= -1;
        return ans;

    }
}

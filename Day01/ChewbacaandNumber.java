package Day01;

import java.util.Scanner;

public class ChewbacaandNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong(), count = 1, temp = 0;
        sc.close();
        while (x > 0) {
            if (x % 10 == 9 && x / 10 == 0) {
                temp += 9 * count;
            } else if (x % 10 > 4) {
                temp += (9 - (x % 10)) * count;
            } else {
                temp += (x % 10) * count;
            }
            count *= 10;
            x /= 10;
        }
        System.out.println(temp);
    }
}
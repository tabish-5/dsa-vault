package Day01;

import java.util.Scanner;

public class Bitpp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt(), count = 0;
        String[] res = new String[x];
        for (int i = 0; i < x; i++) {
            res[i] = sc.next();
        }
        sc.close();
        for(String s : res){
            if (s.charAt(1) == '+') count++;
            else count--;
        }
        System.out.println(count);
    }
}

package Day01;

import java.util.Scanner;

public class BeautifulMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] grid = new int[5][5];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = sc.nextInt();
            }
            System.out.println();
        }
        sc.close();
        for(int[] ar : grid ){
            for(int i : ar){
                // i = sc.nextInt();
                System.out.print(i+" ");
            }
            System.out.println();
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    if (i<2) i = 2-i;
                    else i = i -2;
                    if (j<2) j = 2-j;
                    else j = j -2;
                    System.out.println("result"+(i+j));
                    return;
                }
            }
        }
    }
}

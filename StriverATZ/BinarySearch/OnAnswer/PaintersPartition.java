package StriverATZ.BinarySearch.OnAnswer;

import java.util.*;
public class PaintersPartition {


    public int countPainters(int[] boards, int time) {
        int painters = 1;
        int boardsPainter = 0;

        for (int board : boards) {
            if (boardsPainter + board <= time) {
                boardsPainter += board;
            } else {
                painters++;
                boardsPainter = board;
            }
        }

        return painters;
    }

    public int findLargestMinDistance(int[] boards, int k) {
        int low = Arrays.stream(boards).max().getAsInt();
        int high = Arrays.stream(boards).sum();
        int result = high;

        while (low <= high) {
            int mid = (low + high) / 2;
            int painters = countPainters(boards, mid);

            if (painters > k) {
                low = mid + 1; 
            } else {
                result = mid; 
                high = mid - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] boards = {10, 20, 30, 40};
        int k = 2;

        PaintersPartition pp = new PaintersPartition();
        int ans = pp.findLargestMinDistance(boards, k);
        System.out.println("The answer is: " + ans); // Expected: 60
    }
}

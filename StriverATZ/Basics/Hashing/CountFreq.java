package StriverATZ.Basics.Hashing;

import java.util.*;
public class CountFreq {

    public void Frequency(int[] arr, int n) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        int[] arr = {10, 5, 10, 15, 10, 5};
        int n = arr.length;

        CountFreq sol = new CountFreq();

        sol.Frequency(arr, n);
    }
}

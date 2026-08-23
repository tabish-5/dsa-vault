package StriverATZ.Strings.Medium;

import java.util.Arrays;
// import java.util.Comparator;

public class SortCharacterbyFrequency {
    public String frequencySort(String s) {
        int[][] freq = new int[123][2];
        for (int i = 0; i < 123; i++) {
            freq[i][0] = i; 
        }
        for(char c : s.toCharArray()){
            freq[c][1]++;
        }
        Arrays.sort(freq, (a, b) -> Integer.compare(b[1], a[1]));
        // print(freq);
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i < freq.length){
            if (freq[i][1] == 0) {
                break;
            }
            while(freq[i][1] != 0){
                sb.append((char)(freq[i][0]));
                freq[i][1]--;
            }
            i++;
        }
        return sb.toString();

    }

    void print(int[][] arr){
        for(int[] ar : arr){
            for(int e : ar){
                System.out.print(e+" ");
            }
            System.out.println();
        }
    }
}

package StriverATZ.BitManipulation.AadvanceMath;

import java.util.ArrayList;
import java.util.List;

public class DivisorofaNumber {
    public int[] divisors(int n) {
        List<Integer> li = new ArrayList<>();

        for(int i =1; i <=n; i++){
            if(n %i == 0) li.add(i);
        }
        // Integer[] fruits = li.toArray(new Integer[0]);
        return li.stream().mapToInt(Integer::intValue).toArray();
    }
}

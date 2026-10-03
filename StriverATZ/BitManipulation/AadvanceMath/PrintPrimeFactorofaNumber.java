package StriverATZ.BitManipulation.AadvanceMath;

import java.util.ArrayList;
import java.util.List;

public class PrintPrimeFactorofaNumber {
    public List<List<Integer>> primeFactors(int[] queries) {
        //your code goes here

        List<List<Integer>> result = new ArrayList<>();
        List<Integer> li = new ArrayList<>();

        for(int query : queries){
            li = new ArrayList<>();
            // int n = query;
            for(int i = 2; i *i<=query; i++ ){
                if(query %i == 0){
                    li.add(i);
                    query/=i;
                    while(query %i == 0){
                        li.add(i);
                        query /= i;
                    }
                }
            }
            if(query != 1) li.add(query);
            result.add(new ArrayList<>(li));
        }

        return result;
    }
}

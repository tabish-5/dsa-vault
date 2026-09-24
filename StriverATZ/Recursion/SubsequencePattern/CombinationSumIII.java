package StriverATZ.Recursion.SubsequencePattern;

import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    List<List<Integer>> result = new ArrayList<>();


    public List<List<Integer>> combinationSum3Better(int k, int n) {
        backtrack(k, n, 1, new ArrayList<>());
        return result;
    }

    void backtrack(int k, int target, int start,
                   List<Integer> list) {

        if (list.size() == k) {
            if (target == 0) {
                result.add(new ArrayList<>(list));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {

            if (i > target) {
                break;
            }

            list.add(i);

            backtrack(
                k,
                target - i,
                i + 1,
                list
            );

            list.remove(list.size() - 1);
        }
    }




    public List<List<Integer>> combinationSum3(int k, int n) {
        if ((firstk(k) >= n) || (last(k) <= n))
            return result;

        helper(0, 1, new ArrayList<>(), k, n);

        return result;
    }

    private void helper(int sum, int index, List<Integer> current, int k, int n) {
        if (current.size() == k) {
            if (sum != n) {
                return;
            }
            result.add(new ArrayList<>(current));
            return;
        }
        if (index == 10)
            return;

        current.add(index);
        helper(sum + index, index + 1, current, k, n);

        current.remove(current.size() - 1);
        helper(sum, index + 1, current, k, n);
    }

    int firstk(int k) {
        return k * (k + 1) / 2;
    }

    int last(int k) {
        int sum = 0;
        for (int i = k; i > 9 - k; i--)
            sum += i;
        return sum;
    }
}

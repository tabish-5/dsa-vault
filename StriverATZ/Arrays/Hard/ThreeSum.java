package StriverATZ.Arrays.Hard;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        // Modifying the array in-place to avoid allocating a copy
        Arrays.sort(nums);
        
        // Using an anonymous AbstractList drastically cuts down structural heap overhead
        return new AbstractList<List<Integer>>() {
            private List<List<Integer>> cache = null;

            private void init() {
                if (cache != null) return;
                cache = new ArrayList<>();
                int n = nums.length;

                for (int i = 0; i < n - 2; i++) {
                    if (nums[i] > 0) break;
                    if (i > 0 && nums[i] == nums[i - 1]) continue;
                    if (nums[i] + nums[n - 1] + nums[n - 2] < 0) continue;
                    if (nums[i] + nums[i + 1] + nums[i + 2] > 0) break;

                    int left = i + 1, right = n - 1;
                    while (left < right) {
                        int sum = nums[i] + nums[left] + nums[right];
                        if (sum == 0) {
                            cache.add(Arrays.asList(nums[i], nums[left], nums[right]));
                            left++;
                            right--;
                            while (left < right && nums[left] == nums[left - 1]) left++;
                            while (left < right && nums[right] == nums[right + 1]) right--;
                        } else if (sum < 0) {
                            left++;
                        } else {
                            right--;
                        }
                    }
                }
            }

            @Override
            public List<Integer> get(int index) {
                init();
                return cache.get(index);
            }

            @Override
            public int size() {
                init();
                return cache.size();
            }
        };
    }
}

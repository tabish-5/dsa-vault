package StriverATZ.SortingTechniques;

// import java.util.*;
class InsertionSort {
    public int[] sort(int[] nums) {
        int n = nums.length; 
        
        for (int i = 1; i < n; i++) {
            int key = nums[i];
            int j = i - 1;
            
            while (j >= 0 && nums[j] > key) {
                nums[j + 1] = nums[j];
                j--;
                System.out.println("key :"+ key+" j :"+j);
                print(nums);
            }
            
            nums[j + 1] = key;
            System.out.println("key :"+ key);
            print(nums);
        }
        
        return nums;
    }


    public static void main(String[] args) {
        InsertionSort insort = new InsertionSort();
        
        int[] nums = {13, 46, 24, 52, 20, 9};
        
        System.out.println("Before Using Insertion Sort: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
        
        nums = insort.sort(nums);
        
        System.out.println("After Using Insertion Sort: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }



    void print(int[] arr){
        for(int i : arr){
            System.out.print(i+ " ");
        }
        System.out.println();
    }
}
package StriverATZ.BitManipulation.LearnBM;

public class SwapTwoNumbers {
    public int[] swap(int a, int b) {
        // Your code goes here

        a = a +b; // a = a+b  b = b
        b = a -b; // a = a+b  b = a
        a = a -b; // a = b    b = a






        a = a ^b; //a = a^b  b = b
        b = a ^b; //a = a^b  b = a
        a = a ^b; //a = b    b = a
        return new int[]{a,b};
    }
}

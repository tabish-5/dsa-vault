package StriverATZ.BitManipulation.LearnBM;

public class NumberofithBit {
    public int hammingWeight(int n) {
        int count =0;
        while(n != 0){
            n = n & (n - 1);
            count++;
        }
        // while(n>0){
        //     if(n%2==1){
        //         count++;
        //     }
        //     n=n/2;
        // }
        return count;
    }
}

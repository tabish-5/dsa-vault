package StriverATZ.BitManipulation.AadvanceMath;

public class CountPrimes {
    public int countPrimes(int n) {
        //Sieve of Eratosthenes

        // if (n<=1){
        //     return 0;
        // }
        boolean [] arr = new boolean[n];
        int count = n -2;

        for(int i =2; i *i < n; i++){
            if(!arr[i]){
                for (int j = i *i; j < n; j += i) {
                    if(!arr[j]){
                        arr[j] = true;
                        count--;
                    }
                }
            }
        }

        // for(int e :arr){
        //     if(e == 1) count++;
        // }

        return count <=0? 0: count;
    }
}

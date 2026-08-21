package StriverATZ.BinarySearch.OnAnswer;

public class MinimizeMaxDistToGasStation {


    public int numberOfGasStationsRequired(double dist, int[] arr) {
        int n = arr.length;
        int count = 0;

        for (int i = 1; i < n; i++) {
            int numberInBetween = (int) ((arr[i] - arr[i - 1]) / dist);
            if ((arr[i] - arr[i - 1]) == (dist * numberInBetween)) {
                numberInBetween--;
            }
            count += numberInBetween; 
        }

        return count;  
    }

    public double minimiseMaxDistance(int[] arr, int k) {
        int n = arr.length;
        double low = 0, high = 0;

        for (int i = 0; i < n - 1; i++) {
            high = Math.max(high, arr[i + 1] - arr[i]);
        }

        double diff = 1e-6;

        while (high - low > diff) {
            double mid = (low + high) / 2.0;
            int count = numberOfGasStationsRequired(mid, arr);

            if (count > k) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return high;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4;

        MinimizeMaxDistToGasStation optimizer = new MinimizeMaxDistToGasStation();
        double result = optimizer.minimiseMaxDistance(arr, k);

        System.out.println("The answer is: " + result);
    }
    
}
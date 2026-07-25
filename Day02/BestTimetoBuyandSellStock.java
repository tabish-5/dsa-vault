package Day02;

public class BestTimetoBuyandSellStock {
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE, max = 0, profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i]< min) {
                min = max = prices[i];
            }
            else if (prices[i]> max) {
                max = prices[i];
            }
            profit = Math.max(profit, max - min);
        }
        return profit;
    }
}

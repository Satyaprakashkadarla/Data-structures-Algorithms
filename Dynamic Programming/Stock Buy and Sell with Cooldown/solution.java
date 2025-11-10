class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if (n <= 1) return 0;
        
        int buy = -prices[0];
        int sell = 0;
        int sell_prev = 0; // sell[i-1]
        int sell_prev2 = 0; // sell[i-2]
        
        for (int i = 1; i < n; i++) {
            buy = Math.max(buy, sell_prev2 - prices[i]);
            sell = Math.max(sell, buy + prices[i]);
            
            // update for next iteration
            sell_prev2 = sell_prev;
            sell_prev = sell;
        }
        
        return sell;
    }
}
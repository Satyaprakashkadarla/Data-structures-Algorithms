class Solution {
    public int maxProfit(int arr[], int k) {
        int n = arr.length;
        if (n == 0) return 0;
        
        int cash = 0;
        int hold = -arr[0];
        
        for (int i = 1; i < n; i++) {
            cash = Math.max(cash, hold + arr[i] - k);
            hold = Math.max(hold, cash - arr[i]);
        }
        
        return cash;
    }
}
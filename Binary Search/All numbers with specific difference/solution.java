class Solution {
    public int getCount(int n, int d) {
        int low = 1, high = n;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid - sumOfDigits(mid) >= d) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        if (ans == -1) return 0;
        return n - ans + 1;
    }
    
    private int sumOfDigits(int x) {
        int sum = 0;
        while (x > 0) {
            sum += x % 10;
            x /= 10;
        }
        return sum;
    }
}
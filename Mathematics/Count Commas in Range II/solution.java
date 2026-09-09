class Solution {
    public long countCommas(long n) {
        long ans = 0;
        long power = 1000;
        
        while (power <= n) {
            ans += n - power + 1;
            power *= 1000;
        }
        
        // Numbers >= 1,000,000 have 2 commas,
        // >= 1,000,000,000 have 3, etc.
        // The loop above naturally adds each comma.
        return ans;
    }
}

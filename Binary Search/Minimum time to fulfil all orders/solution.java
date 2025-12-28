class Solution {
    public int minTime(int[] ranks, int n) {
        int m = ranks.length;
        
        long low = 1;
        // Upper bound: worst chef (max rank) makes all n donuts
        int maxRank = 0;
        for (int r : ranks) maxRank = Math.max(maxRank, r);
        long high = (long) maxRank * n * (n + 1) / 2; // safe upper bound
        
        while (low < high) {
            long mid = low + (high - low) / 2;
            
            if (canMake(ranks, n, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        
        return (int) low;
    }
    
    private boolean canMake(int[] ranks, int n, long time) {
        long total = 0;
        for (int r : ranks) {
            // Find max k such that k*(k+1)/2 * r <= time
            // k*(k+1) <= 2*time/r
            long k = (long) Math.sqrt(2.0 * time / r);
            // Adjust k
            while (k * (k + 1) * r > 2 * time) {
                k--;
            }
            while ((k + 1) * (k + 2) * r <= 2 * time) {
                k++;
            }
            total += k;
            if (total >= n) return true;
        }
        return total >= n;
    }
}
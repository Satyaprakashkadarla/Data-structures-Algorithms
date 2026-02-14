class Solution {
    public int minTime(int[] arr, int k) {
        int lo = 0, hi = 0;
        for (int time : arr) {
            lo = Math.max(lo, time);
            hi += time;
        }
        
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (canPaint(arr, k, mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
    
    private boolean canPaint(int[] arr, int k, int maxTime) {
        int painters = 1;
        int sum = 0;
        for (int time : arr) {
            if (sum + time <= maxTime) {
                sum += time;
            } else {
                painters++;
                sum = time;
                if (painters > k) return false;
            }
        }
        return true;
    }
}
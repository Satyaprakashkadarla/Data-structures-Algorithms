class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        long ans=0,deductions=0;
        int n = happiness.length;
        Arrays.sort(happiness);
        for(int i=n-1;i>=n-k;i--) {
            ans += Math.max(happiness[i] - deductions, 0);
            deductions++;
        }
        return ans;
    }
}
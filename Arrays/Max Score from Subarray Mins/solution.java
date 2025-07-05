class Solution {
    public int maxSum(int arr[]) {
        int n = arr.length;
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            ans = Math.max(arr[i] + arr[i - 1], ans);
        }
        return ans;
    }
}
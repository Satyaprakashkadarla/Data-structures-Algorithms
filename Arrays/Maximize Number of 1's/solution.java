class Solution {
    public int solve(int[] arr, int k) {
        int i = 0, j = 0, cnt = 0, ans = 0;
        while (j < arr.length) {
            if (arr[j] == 0) {
                cnt++;
            }

            while (cnt > k) {
                if (arr[i] == 0) {
                    cnt--;
                }
                i++;
            }

            ans = Math.max(ans, j - i + 1);
            j++;
        }
        return ans;
    }

    public int maxOnes(int[] arr, int k) {
        return solve(arr, k);
    }
}

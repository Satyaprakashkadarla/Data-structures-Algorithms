class Solution {
    public int countPrimeSetBits(int left, int right) {
        boolean[] arr = {
            false, false, true, true, false, true, false, true, false, false,
            false, true, false, true, false, false, false, true, false, true,
            false, false, false, true, false, false, false, false, false, true,
            false, true, false
        };
        int ans = 0;
        for (int i = left; i <= right; i++) {
            int n = i;
            int cnt = 0;
            while (n > 0) {
                n = n & (n - 1);
                cnt++;
            }
            if (arr[cnt]) ans++;
        }
        return ans;
    }
}
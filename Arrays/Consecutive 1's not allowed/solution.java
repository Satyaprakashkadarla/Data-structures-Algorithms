class Solution {
    int countStrings(int n) {
        int dp0 = 1, dp1 = 1;
        for (int i = 2; i <= n; i++) {
            int newDp0 = dp0 + dp1;
            int newDp1 = dp0;
            dp0 = newDp0;
            dp1 = newDp1;
        }
        return dp0 + dp1;
    }
}
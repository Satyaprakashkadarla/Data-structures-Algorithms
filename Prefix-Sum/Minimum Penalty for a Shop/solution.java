class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        int[] N = new int[n + 1];
        int[] Y = new int[n + 1];

        int cnt = Integer.MAX_VALUE;
        int ans = 0;

        // Prefix count of 'N'
        for (int i = 1; i <= n; i++) {
            if (customers.charAt(i - 1) == 'N')
                N[i] = N[i - 1] + 1;
            else
                N[i] = N[i - 1];
        }

        // Suffix count of 'Y'
        for (int i = n - 1; i >= 0; i--) {
            if (customers.charAt(i) == 'Y')
                Y[i] = Y[i + 1] + 1;
            else
                Y[i] = Y[i + 1];
        }

        // Find minimum penalty
        for (int i = 0; i <= n; i++) {
            if (N[i] + Y[i] < cnt) {
                cnt = N[i] + Y[i];
                ans = i;
            }
        }

        return ans;
    }
}
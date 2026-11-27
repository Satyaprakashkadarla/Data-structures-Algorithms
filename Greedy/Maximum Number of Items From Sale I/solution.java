class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int minPrice = Integer.MAX_VALUE;

        int[] bonus = new int[n];

        // Calculate free copies received by activating each item.
        for (int i = 0; i < n; i++) {
            minPrice = Math.min(minPrice, items[i][1]);

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    bonus[i]++;
                }
            }
        }

        // 0/1 Knapsack:
        // Activating item i costs price[i] and gives 1 + bonus[i].
        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int cost = items[i][1];
            int value = 1 + bonus[i];

            for (int b = budget; b >= cost; b--) {
                dp[b] = Math.max(dp[b],
                                 dp[b - cost] + value);
            }
        }

        // Spend remaining budget on the cheapest item.
        int ans = 0;

        for (int b = 0; b <= budget; b++) {
            int total = dp[b] + (budget - b) / minPrice;
            ans = Math.max(ans, total);
        }

        return ans;
    }
}
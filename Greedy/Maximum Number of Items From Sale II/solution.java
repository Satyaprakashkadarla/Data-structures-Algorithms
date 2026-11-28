
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] freq = new int[n + 1];
        int minPrice = Integer.MAX_VALUE;

        for (int[] item : items) {
            freq[item[0]]++;
            minPrice = Math.min(minPrice, item[1]);
        }

        // Count all divisible factors.
        int[] multiples = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                multiples[i] += freq[j];
            }
        }

        // Sort items by price.
        long[] order = new long[n];

        for (int i = 0; i < n; i++) {
            order[i] = ((long) items[i][1] << 17) | i;
        }

        Arrays.sort(order);

        long remaining = budget;
        long bonusCount = 0;

        for (long key : order) {
            int idx = (int) (key & ((1 << 17) - 1));
            long price = key >>> 17;

            // Two regular copies are at least as good.
            if (price >= 2L * minPrice) {
                break;
            }

            int factor = items[idx][0];

            // Exclude only the current item.
            int capacity = multiples[factor] - 1;

            if (capacity <= 0) {
                continue;
            }

            long take = Math.min(
                (long) capacity,
                remaining / price
            );

            bonusCount += take;
            remaining -= take * price;

            if (take < capacity) {
                break;
            }
        }

        long regularCount = remaining / minPrice;

        return (int) (2 * bonusCount + regularCount);
    }
}

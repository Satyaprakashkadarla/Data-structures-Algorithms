import java.util.ArrayList;

class Solution {
    public ArrayList<Integer> constructArr(int[] arr) {
        ArrayList<Integer> res = new ArrayList<>();
        int m = arr.length;

        // Find n such that n*(n-1)/2 == m (use integer arithmetic to avoid FP errors)
        int n = 0;
        for (int cand = 1; ; cand++) {
            long pairs = 1L * cand * (cand - 1) / 2;
            if (pairs == m) { n = cand; break; }
            if (pairs > m) { // invalid input size (shouldn't happen per problem statement)
                return res;
            }
        }

        // Special case: n == 2 (only one pair). We can return any two numbers that sum to arr[0].
        if (n == 2) {
            // choose 0 and arr[0] (always valid). If you prefer positive values, use 1 and arr[0]-1.
            res.add(0);
            res.add(arr[0]);
            return res;
        }

        // Use long to avoid overflow when adding/subtracting large pair sums
        long s01 = arr[0];      // a0 + a1
        long s02 = arr[1];      // a0 + a2
        long s12 = arr[n - 1];  // a1 + a2  (index n-1: after the first block of (n-1) sums with a0)

        // Compute a0 = (s01 + s02 - s12) / 2
        long a0long = (s01 + s02 - s12) / 2;
        int a0 = (int) a0long;
        res.add(a0);

        // a1, a2
        int a1 = (int) (s01 - a0long);
        int a2 = (int) (s02 - a0long);
        res.add(a1);
        res.add(a2);

        int idx = n; // index of a1 + a3
        for (int i = 3; i < n; i++) {
            // a_i = (a1 + a_i) - a1
            long sumi = arr[idx];
            int ai = (int) (sumi - a1);
            res.add(ai);
            idx++;
        }

        return res;
    }
}

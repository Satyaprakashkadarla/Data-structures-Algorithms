import java.util.HashMap;

class Solution {
    public int longestCommonSum(int[] a1, int[] a2) {
        int n = a1.length;
        HashMap<Integer, Integer> mp = new HashMap<>(); // maps diff -> first index it was seen
        int sum1 = 0, sum2 = 0, result = 0;

        for (int i = 0; i < n; i++) {
            sum1 += a1[i];
            sum2 += a2[i];
            int diff = sum1 - sum2;

            if (diff == 0) {
                // from 0..i is balanced
                result = Math.max(result, i + 1);
            } else if (mp.containsKey(diff)) {
                // seen this diff before, subarray (mp.get(diff) + 1..i) is balanced
                result = Math.max(result, i - mp.get(diff));
            } else {
                // first time seeing this diff
                mp.put(diff, i);
            }
        }
        return result;
    }
}

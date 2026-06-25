class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + (nums[i] == target ? 1 : -1);
        }

        int[] vals = prefix.clone();
        java.util.Arrays.sort(vals);

        java.util.Map<Integer, Integer> rank = new java.util.HashMap<>();
        int idx = 1;
        for (int v : vals) {
            if (!rank.containsKey(v)) {
                rank.put(v, idx++);
            }
        }

        Fenwick bit = new Fenwick(idx + 2);
        long ans = 0;

        for (int p : prefix) {
            int r = rank.get(p);

            // count previous prefix sums strictly smaller than p
            ans += bit.query(r - 1);

            bit.add(r, 1);
        }

        return (int) ans;
    }

    static class Fenwick {
        int[] bit;

        Fenwick(int n) {
            bit = new int[n];
        }

        void add(int idx, int val) {
            while (idx < bit.length) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int res = 0;
            while (idx > 0) {
                res += bit[idx];
                idx -= idx & -idx;
            }
            return res;
        }
    }
}
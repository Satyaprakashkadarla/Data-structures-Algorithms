class Solution {
    class Fenwick {
        int[] bit;
        Fenwick(int n) {
            bit = new int[n + 2];
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

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        int[] pre = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + (nums[i] == target ? 1 : -1);
        }

        int[] vals = pre.clone();
        java.util.Arrays.sort(vals);

        int m = 1;
        for (int i = 1; i < vals.length; i++) {
            if (vals[i] != vals[m - 1]) {
                vals[m++] = vals[i];
            }
        }

        Fenwick ft = new Fenwick(m);
        long ans = 0;

        for (int x : pre) {
            int rank = lowerBound(vals, m, x) + 1;

            // previous prefix sums strictly smaller than current
            ans += ft.query(rank - 1);

            ft.add(rank, 1);
        }

        return ans;
    }

    private int lowerBound(int[] arr, int len, int target) {
        int l = 0, r = len;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return l;
    }
}
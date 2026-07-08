import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        ArrayList<Integer> posList = new ArrayList<>();
        ArrayList<Integer> digitList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int d = s.charAt(i) - '0';
            if (d != 0) {
                posList.add(i);
                digitList.add(d);
            }
        }

        int k = posList.size();

        if (k == 0) {
            return new int[queries.length];
        }

        int[] pos = new int[k];
        long[] prefNum = new long[k + 1];
        int[] prefSum = new int[k + 1];
        long[] pow10 = new long[k + 1];

        pow10[0] = 1;
        for (int i = 1; i <= k; i++)
            pow10[i] = (pow10[i - 1] * 10) % MOD;

        for (int i = 0; i < k; i++) {
            pos[i] = posList.get(i);
            int d = digitList.get(i);
            prefNum[i + 1] = (prefNum[i] * 10 + d) % MOD;
            prefSum[i + 1] = prefSum[i] + d;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];

            int left = lowerBound(pos, l);
            int right = upperBound(pos, r) - 1;

            if (left > right) {
                ans[i] = 0;
                continue;
            }

            int len = right - left + 1;

            long num = (prefNum[right + 1]
                    - (prefNum[left] * pow10[len]) % MOD
                    + MOD) % MOD;

            int sum = prefSum[right + 1] - prefSum[left];

            ans[i] = (int) ((num * sum) % MOD);
        }

        return ans;
    }

    private int lowerBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    private int upperBound(int[] arr, int target) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] > target)
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }
}
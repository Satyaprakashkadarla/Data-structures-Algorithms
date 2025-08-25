import java.util.Arrays;

class Solution {
    private boolean isPossible(int[] arr, int target, long k) {
        int n = arr.length;
        if (n % 2 == 1) {
            for (int i = n / 2; i < n; ++i) {
                if (arr[i] < target) {
                    k -= (target - arr[i]);
                    if (k < 0) return false;
                }
            }
        } else {
            if (arr[n / 2] <= target) {
                k -= (target - arr[n / 2]);
                if (k < 0) return false;
                k -= (target - arr[n / 2 - 1]);
                if (k < 0) return false;
            } else {
                k -= (2L * target - (arr[n / 2] + arr[n / 2 - 1]));
                if (k < 0) return false;
            }
            for (int i = n / 2 + 1; i < n; ++i) {
                if (arr[i] < target) {
                    k -= (target - arr[i]);
                    if (k < 0) return false;
                }
            }
        }
        return true;
    }

    public int maximizeMedian(int[] arr, int k) {
        Arrays.sort(arr);
        int n = arr.length;

        int iniMedian;
        if (n % 2 == 0) {
            iniMedian = (arr[n / 2] + arr[n / 2 - 1]) / 2;
        } else {
            iniMedian = arr[n / 2];
        }

        int low = iniMedian, high = iniMedian + k;
        int bestMedian = iniMedian;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isPossible(arr, mid, k)) {
                bestMedian = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return bestMedian;
    }
}

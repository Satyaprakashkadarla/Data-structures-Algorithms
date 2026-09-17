class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = n + 1;
        int[] best = new int[n];

        Arrays.fill(best, INF);

        int left = 0;
        long sum = 0;
        int answer = INF;
        int minPrev = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            while (sum > target && left <= right) {
                sum -= arr[left++];
            }

            // If a target-sum subarray ends at 'right'
            if (sum == target) {
                int len = right - left + 1;

                // Combine with the best subarray ending before 'left'
                if (left > 0 && best[left - 1] != INF) {
                    answer = Math.min(answer, len + best[left - 1]);
                }

                minPrev = len;
            }

            // Best single subarray found so far ending at or before 'right'
            if (right == 0) {
                best[right] = (sum == target) ? right - left + 1 : INF;
            } else {
                best[right] = best[right - 1];

                if (sum == target) {
                    best[right] = Math.min(best[right],
                                           right - left + 1);
                }
            }
        }

        return answer == INF ? -1 : answer;
    }
}
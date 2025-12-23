class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int n = events.length;
        int[] maxValueFromIndex = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            maxValueFromIndex[i] = Math.max(maxValueFromIndex[i + 1], events[i][2]);
        }
        int maxSum = 0;
        for (int[] currentEvent : events) {
            int currentValue = currentEvent[2];
            int currentEndTime = currentEvent[1];
            int left = 0;
            int right = n - 1;
            int firstTrueIndex = -1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (events[mid][0] > currentEndTime) {
                    firstTrueIndex = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            if (firstTrueIndex != -1) {
                currentValue += maxValueFromIndex[firstTrueIndex];
            }
            maxSum = Math.max(maxSum, currentValue);
        }
        return maxSum;
    }
}
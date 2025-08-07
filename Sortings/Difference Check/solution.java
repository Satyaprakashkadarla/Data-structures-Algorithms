class Solution {
    public int minDifference(String[] arr) {
        int n = arr.length;
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            String[] parts = arr[i].split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            int seconds = Integer.parseInt(parts[2]);
            times[i] = hours * 3600 + minutes * 60 + seconds;
        }
        Arrays.sort(times);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            minDiff = Math.min(minDiff, times[i] - times[i - 1]);
        }
        minDiff = Math.min(minDiff, 86400 - (times[n - 1] - times[0]));
        return minDiff;
    }
}

public class Solution {
    public boolean canAttend(int[][] arr) {
        // Sort the intervals based on the start time (arr[i][0])
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        // Check for any overlap between consecutive intervals
        for (int i = 1; i < arr.length; i++) {
            // If the end time of the previous meeting is greater than the start time of the current meeting
            if (arr[i - 1][1] > arr[i][0]) {
                return false;
            }
        }
        
        return true;
    }
}
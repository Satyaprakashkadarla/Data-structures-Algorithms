import java.util.Arrays;

class Solution {
    public int assignHole(int[] mices, int[] holes) {
        Arrays.sort(mices);
        Arrays.sort(holes);
        int maxTime = 0;
        for (int i = 0; i < mices.length; i++) {
            int time = Math.abs(mices[i] - holes[i]);
            if (time > maxTime) {
                maxTime = time;
            }
        }
        return maxTime;
    }
}
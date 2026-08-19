import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // row -> bitmask of reserved seats
        Map<Integer, Integer> reserved = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Seat 1 -> bit 0, seat 10 -> bit 9
            reserved.put(row, reserved.getOrDefault(row, 0) | (1 << (col - 1)));
        }

        // Masks for:
        // [2,3,4,5] -> bits 1..4
        // [4,5,6,7] -> bits 3..6
        // [6,7,8,9] -> bits 5..8
        final int LEFT   = 0b0000011110;
        final int MIDDLE = 0b0001111000;
        final int RIGHT  = 0b0111100000;

        // Every row without reservations can fit 2 groups.
        long answer = 2L * n;

        // Recalculate only rows affected by reservations.
        for (int mask : reserved.values()) {
            // Initially, this row was counted as 2.
            answer -= 2;

            boolean leftAvailable   = (mask & LEFT) == 0;
            boolean middleAvailable = (mask & MIDDLE) == 0;
            boolean rightAvailable  = (mask & RIGHT) == 0;

            if (leftAvailable && rightAvailable) {
                answer += 2;
            } else if (leftAvailable || middleAvailable || rightAvailable) {
                answer += 1;
            }
        }

        return (int) answer;
    }
}
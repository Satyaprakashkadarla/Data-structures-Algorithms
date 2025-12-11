import java.util.*;
class Solution {
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, TreeSet<Integer>> row = new HashMap<>();
        Map<Integer, TreeSet<Integer>> col = new HashMap<>();
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            row.computeIfAbsent(x, k -> new TreeSet<>()).add(y);
            col.computeIfAbsent(y, k -> new TreeSet<>()).add(x);
        }
        int res = 0;
        for (int[] b : buildings) {
            int x = b[0], y = b[1];
            if (row.get(x).lower(y) != null &&  // A building to the left
                row.get(x).higher(y) != null && // A building to the right
                col.get(y).lower(x) != null &&  // A building above
                col.get(y).higher(x) != null) { // A building below
                res++;  // It's covered, so increment the result
            }
        }
        
        return res;
    }
}
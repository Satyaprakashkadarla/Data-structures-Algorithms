import java.util.*;

class Solution {
    public ArrayList<ArrayList<Integer>> nearest(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Create result matrix
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(-1); // Initialize with -1 (unvisited)
            }
            result.add(row);
        }
        
        // Queue for BFS
        Queue<int[]> queue = new LinkedList<>();
        
        // Add all 1's to queue with distance 0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j, 0});
                    result.get(i).set(j, 0);
                }
            }
        }
        
        // Directions: up, right, down, left
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        
        // BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];
            int dist = current[2];
            
            // Check all four directions
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                
                // Check if new position is valid and not visited
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n 
                    && result.get(newRow).get(newCol) == -1) {
                    
                    result.get(newRow).set(newCol, dist + 1);
                    queue.offer(new int[]{newRow, newCol, dist + 1});
                }
            }
        }
        
        return result;
    }
}
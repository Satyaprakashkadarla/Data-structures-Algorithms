class Solution {
    private int[][] vis = new int[0][0];
    private boolean[][] blocked = new boolean[0][0];
    private final int[][] directions = new int[][] {
        new int[] { 0, 1 }, // Down
        new int[] { 1, 0 }, // Right
        new int[] { -1, 0 }, // Left
        new int[] { 0, -1 } // Up
    };
    private int row = -1, col = -1;

    public int latestDayToCross(int row, int col, int[][] cells) {
        this.row = row; this.col = col;

        vis = new int[row][col];
        blocked = new boolean[row][col];
        for (int i = 0; i < row; i++) 
            for (int j = 0; j < col; j++) 
                blocked[i][j] = false;
        
        int left = 0, right = cells.length;
        int ans = 0;
        // Use Binary Search from {day1, day2, ..., dayEnd} to find the day it fails
        /**
        Rationale, Key property (monotonicity)
            If you can cross on day d, then you can cross on any earlier day
            If you cannot cross on day d, then you cannot cross on any later day
            This is a monotonic boolean function. E.g.
                true true false false false
         */ 

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (canCross(mid, cells)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }

    private boolean canCross(int day, int[][] cells) {
        // Each time canCross is run, we assume a new grid, fresh exploration
        blocked = new boolean[row][col];
        vis = new int[row][col];

        // Flood first "left" cells
        for (int i = 0; i < day; i++) {
            int r = cells[i][0] - 1;
            int c = cells[i][1] - 1;
            blocked[r][c] = true;
        }

        // Try DFS from top row
        for (int z = 0; z < col; z++) {
            if (!blocked[0][z]) {
                if (dfs(0, z)) return true;
            }
        }
        return false;
    }

    private boolean dfs(int r, int c) {
        // Do not explore visited cell & water cell
        if (vis[r][c] > 0 || blocked[r][c]) return false;

        vis[r][c] = 1; // Mark cell as visited
        
        // Reached the end
        if (r == row - 1) return true;

        // Not at the end yet - Go in all directions & explore
        for (int[] dir : directions) {
            int dx = dir[0], dy = dir[1];
            int newC = c + dx, newR = r + dy;

            // If can reach the endpoint, we return directly
            if (0 <= newC && newC < col && 0 <= newR && newR < row) 
                if (dfs(newR, newC)) return true; 
        }
        return false;
    }
}
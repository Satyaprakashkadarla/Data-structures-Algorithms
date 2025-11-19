class Solution {
    public int minCostPath(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        
        int minVal = Integer.MAX_VALUE;
        int maxVal = Integer.MIN_VALUE;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                minVal = Math.min(minVal, mat[i][j]);
                maxVal = Math.max(maxVal, mat[i][j]);
            }
        }
        // Binary search on the maximum allowed difference
        int left = 0;
        int right = maxVal - minVal;
        int answer = right;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (canReachWithThresholdBFS(mat, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        
        return answer;
    }
    
    private boolean canReachWithThresholdBFS(int[][] mat, int threshold) {
        int n = mat.length;
        int m = mat[0].length;
        
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int i = curr[0], j = curr[1];
            
            if (i == n - 1 && j == m - 1) {
                return true;
            }
            
            for (int[] dir : directions) {
                int ni = i + dir[0];
                int nj = j + dir[1];
                
                if (ni >= 0 && ni < n && nj >= 0 && nj < m && !visited[ni][nj]) {
                    if (Math.abs(mat[i][j] - mat[ni][nj]) <= threshold) {
                        visited[ni][nj] = true;
                        queue.offer(new int[]{ni, nj});
                    }
                }
            }
        }
        
        return false;
    }
}
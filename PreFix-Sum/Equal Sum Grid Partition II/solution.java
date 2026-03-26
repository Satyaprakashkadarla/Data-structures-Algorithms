class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        Map<Long,Integer> allEl = new HashMap<>();
        long[] row = new long[n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                row[i] += grid[i][j];
                allEl.put(1l*grid[i][j],allEl.getOrDefault(1l*grid[i][j],0)+1);
            }
            if(i > 0) row[i] += row[i-1];
        }
        long colSum = 0l, totSum = row[n-1];
        Map<Long,Integer> partial = new HashMap<>();
        for(int i = 0; i < n-1; i++){
            for(int j = 0; j < m; j++){
                partial.put(1l*grid[i][j],partial.getOrDefault(1l*grid[i][j],0)+1);
            }
            if(row[i] == totSum-row[i]) return true;
            else if(row[i] > totSum-row[i]){
                long diff = (row[i]-(totSum-row[i]));
                if(m == 1){
                    if(i > 0){
                        if(1l*grid[0][0] == diff || 1l*grid[i][0] == diff) return true;
                    }
                }else{
                    if(i == 0){
                        if(1l*grid[0][0] == diff || 1l*grid[0][m-1] == diff) return true;
                    }else{
                        if(partial.containsKey(diff)) return true;
                    }
                }
            }else{
                long diff = ((totSum-row[i])-row[i]);
                if(m == 1){
                    if(i < n-1){
                        if(1l*grid[n-1][0] == diff || 1l*grid[i+1][0] == diff) return true;
                    }
                }else{
                    if(i == n-2){
                        if(1l*grid[n-1][0] == diff || 1l*grid[n-1][m-1] == diff) return true;
                    }else{
                        if(allEl.containsKey(diff) && (allEl.get(diff)-partial.getOrDefault(diff,0)) > 0) return true;
                    }
                }
            }
        } 
        partial.clear();
        for(int j = 0; j < m-1; j++){
            for(int i = 0; i < n; i++){
                colSum += grid[i][j];
                partial.put(1l*grid[i][j],partial.getOrDefault(1l*grid[i][j],0)+1);
            }
            if(colSum == totSum-colSum) return true;
            else if(colSum > totSum-colSum){
                long diff = (colSum-(totSum-colSum));
                if(n == 1){
                    if(j > 0){
                        if(1l*grid[0][0] == diff || 1l*grid[0][j] == diff) return true;
                    }
                }else{
                    if(j == 0){
                        if(1l*grid[0][0] == diff || 1l*grid[n-1][0] == diff) return true;
                    }else{
                        if(partial.containsKey(diff)) return true;
                    }
                }
            }else{
                long diff = ((totSum-colSum)-colSum);
                if(n == 1){
                    if(j < m-2){
                        if(1l*grid[0][m-1] == diff || 1l*grid[0][j+1] == diff) return true;
                    }
                }else{
                    if(j == n-2){
                        if(1l*grid[0][m-1] == diff || 1l*grid[n-1][m-1] == diff) return true;
                    }else{
                        if(allEl.containsKey(diff) && (allEl.get(diff)-partial.getOrDefault(diff,0)) > 0) return true;
                    }
                }
            }
        }
        return false;
    }
}
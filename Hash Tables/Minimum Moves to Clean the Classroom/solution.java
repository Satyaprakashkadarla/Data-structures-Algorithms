import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startX = -1, startY = -1;
        List<int[]> litterList = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = classroom[i].charAt(j);
                if (c == 'S') {
                    startX = i;
                    startY = j;
                } else if (c == 'L') {
                    litterList.add(new int[]{i, j});
                }
            }
        }
        
        int numLitter = litterList.size();
        if (numLitter == 0) return 0;
        
        Map<String, Integer> litterMap = new HashMap<>();
        for (int i = 0; i < numLitter; i++) {
            litterMap.put(litterList.get(i)[0] + "," + litterList.get(i)[1], i);
        }
        
        // BFS queue stores: [x, y, energy, mask]
        Queue<int[]> queue = new LinkedList<>();
        // maxEnergy[x][y][mask] tracks maximum energy reached for state pruning
        int[][][] maxEnergy = new int[m][n][1 << numLitter];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergy[i][j], -1);
            }
        }
        
        int initialMask = 0;
        queue.offer(new int[]{startX, startY, energy, initialMask});
        maxEnergy[startX][startY][initialMask] = energy;
        
        int moves = 0;
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int targetMask = (1 << numLitter) - 1;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] curr = queue.poll();
                int x = curr[0], y = curr[1], curEnergy = curr[2], mask = curr[3];
                
                if (mask == targetMask) {
                    return moves;
                }
                
                if (curEnergy <= 0) continue;
                
                for (int i = 0; i < 4; i++) {
                    int nx = x + dr[i];
                    int ny = y + dc[i];
                    
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                    char nextChar = classroom[nx].charAt(ny);
                    if (nextChar == 'X') continue;
                    
                    int nxtEnergy = curEnergy - 1;
                    if (nxtEnergy < 0) continue;
                    
                    int nxtMask = mask;
                    if (nextChar == 'R') {
                        nxtEnergy = energy;
                    } else if (nextChar == 'L') {
                        String key = nx + "," + ny;
                        if (litterMap.containsKey(key)) {
                            int idx = litterMap.get(key);
                            nxtMask |= (1 << idx);
                        }
                    }
                    
                    if (nxtMask == targetMask) {
                        return moves + 1;
                    }
                    
                    if (nxtEnergy > maxEnergy[nx][ny][nxtMask]) {
                        maxEnergy[nx][ny][nxtMask] = nxtEnergy;
                        queue.offer(new int[]{nx, ny, nxtEnergy, nxtMask});
                    }
                }
            }
            moves++;
        }
        
        return -1;
    }
}
import java.util.*;

class Solution {
    public ArrayList<String> ratInMaze(int[][] maze) {
        ArrayList<String> result = new ArrayList<>();
        int n = maze.length;
        
        // If start or end is blocked, no path exists
        if (maze[0][0] == 0 || maze[n-1][n-1] == 0) {
            return result;
        }
        
        int[][] visited = new int[n][n];
        
        findPaths(maze, 0, 0, visited, "", result);
        
        return result;
    }
    
    private void findPaths(int[][] maze, int row, int col, int[][] visited, 
                          String path, ArrayList<String> result) {
        int n = maze.length;
        
        if (row == n-1 && col == n-1) {
            result.add(path);
            return;
        }
        
        // Mark current cell as visited
        visited[row][col] = 1;
        
        // Try all 4 directions in lexicographical order: D, L, R, U
        
        // Down
        if (isValid(maze, row + 1, col, visited)) {
            findPaths(maze, row + 1, col, visited, path + "D", result);
        }
        
        // Left
        if (isValid(maze, row, col - 1, visited)) {
            findPaths(maze, row, col - 1, visited, path + "L", result);
        }
        
        // Right
        if (isValid(maze, row, col + 1, visited)) {
            findPaths(maze, row, col + 1, visited, path + "R", result);
        }
        
        // Up
        if (isValid(maze, row - 1, col, visited)) {
            findPaths(maze, row - 1, col, visited, path + "U", result);
        }
        
        visited[row][col] = 0;
    }
    
    private boolean isValid(int[][] maze, int row, int col, int[][] visited) {
        int n = maze.length;
        
        if (row < 0 || row >= n || col < 0 || col >= n) {
            return false;
        }
        
        if (maze[row][col] == 0 || visited[row][col] == 1) {
            return false;
        }
        
        return true;
    }
}
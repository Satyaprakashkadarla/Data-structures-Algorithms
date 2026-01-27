class Solution {
    public boolean isWordExist(char[][] mat, String word) {
        int n = mat.length;
        int m = mat[0].length;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == word.charAt(0)) {
                    if (dfs(mat, word, i, j, 0, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] mat, String word, int i, int j, int idx, boolean[][] visited) {
        if (idx == word.length()) {
            return true;
        }

        // boundary + character + visited check
        if (i < 0 || j < 0 || i >= mat.length || j >= mat[0].length ||
            visited[i][j] || mat[i][j] != word.charAt(idx)) {
            return false;
        }

        visited[i][j] = true;

        boolean found =
            dfs(mat, word, i + 1, j, idx + 1, visited) ||
            dfs(mat, word, i - 1, j, idx + 1, visited) ||
            dfs(mat, word, i, j + 1, idx + 1, visited) ||
            dfs(mat, word, i, j - 1, idx + 1, visited);

        visited[i][j] = false; // backtrack
        return found;
    }
}
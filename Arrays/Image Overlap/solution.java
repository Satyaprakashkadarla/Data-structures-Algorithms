class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;

        // Store coordinates of 1s in both images
        int[][] a = new int[n * n][2];
        int[][] b = new int[n * n][2];
        int aCount = 0, bCount = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    a[aCount++] = new int[]{i, j};
                }
                if (img2[i][j] == 1) {
                    b[bCount++] = new int[]{i, j};
                }
            }
        }

        // Map translation (row shift, col shift) -> overlap count
        int[][] count = new int[2 * n - 1][2 * n - 1];

        int ans = 0;

        for (int i = 0; i < aCount; i++) {
            for (int j = 0; j < bCount; j++) {
                int dr = b[j][0] - a[i][0] + n - 1;
                int dc = b[j][1] - a[i][1] + n - 1;

                ans = Math.max(ans, ++count[dr][dc]);
            }
        }

        return ans;
    }
}

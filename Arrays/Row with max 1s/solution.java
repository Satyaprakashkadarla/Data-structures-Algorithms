class Solution {
    public int rowWithMax1s(int arr[][]) {
        int n = arr.length;
        if (n == 0) return -1;
        int m = arr[0].length;
        
        int maxRow = -1;
        int row = 0, col = m - 1;
        
        while (row < n && col >= 0) {
            if (arr[row][col] == 1) {
                maxRow = row;
                col--;
            } else {
                row++;
            }
        }
        return maxRow;
    }
}
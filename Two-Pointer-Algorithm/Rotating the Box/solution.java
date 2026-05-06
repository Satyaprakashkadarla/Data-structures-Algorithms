class Solution {
    public char[][] rotateTheBox(char[][] box) {
        int rows = box.length;
        int cols = box[0].length;
        char[][] result = new char[cols][rows];

        for (int i = 0; i < rows; i++) {
            int emptySpot = cols - 1;
            for (int j = cols - 1; j >= 0; j--) {
                if (box[i][j] == '#') {
                    box[i][j] = '.';
                    box[i][emptySpot--] = '#';
                } else if (box[i][j] == '*') {
                    emptySpot = j - 1;
                }
            }
            for (int j = 0; j < cols; j++) {
                result[j][rows - 1 - i] = box[i][j];
            }
        }

        return result;
    }
}
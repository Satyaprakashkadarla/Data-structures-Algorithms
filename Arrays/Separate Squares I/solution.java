class Solution {
    public double separateSquares(int[][] squares) {
        double totalArea = 0.0;
        double minY = Double.POSITIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;

        for (int[] square : squares) {
            double yi = square[1];
            double li = square[2];
            totalArea += li * li;
            minY = Math.min(minY, yi);
            maxY = Math.max(maxY, yi + li);
        }

        double targetArea = totalArea / 2.0;
        double low = minY, high = maxY;

        // Binary search for approximately 100 iterations for high precision
        for (int i = 0; i < 100; i++) {
            double mid = (low + high) / 2.0;
            if (computeAreaAbove(squares, mid) > targetArea) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return low;
    }

    private double computeAreaAbove(int[][] squares, double k) {
        double area = 0.0;
        for (int[] sq : squares) {
            double yBottom = sq[1];
            double l = sq[2];
            double yTop = yBottom + l;

            if (k <= yBottom) {
                area += l * l;
            } else if (k < yTop) {
                area += (yTop - k) * l;
            }
        }
        return area;
    }
}
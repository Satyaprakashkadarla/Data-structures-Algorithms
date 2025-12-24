class Solution {
    public int countLessEqual(int[] arr, int x) {
        int n = arr.length;
        if (n == 0) return 0;
        
        int pivot = findPivot(arr);
        
        int count1 = countInSegment(arr, pivot, n - 1, x);
        if (pivot > 0) {
            int count2 = countInSegment(arr, 0, pivot - 1, x);
            return count1 + count2;
        }
        return count1;
    }
    private int findPivot(int[] arr) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] > arr[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    
    private int countInSegment(int[] arr, int low, int high, int x) {
        if (low > high) return 0;
        // Find first index > x
        int start = low, end = high + 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] <= x) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start - low;
    }
}
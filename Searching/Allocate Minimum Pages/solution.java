class Solution {
    public int findPages(int[] arr, int k) {
        int n = arr.length;
        if (k > n) {
            return -1;
        }
        
        int low = 0;
        int high = 0;
        for (int pages : arr) {
            high += pages;
            if (pages > low) {
                low = pages;
            }
        }
        
        int result = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isFeasible(arr, k, mid)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return result;
    }
    
    private boolean isFeasible(int[] arr, int k, int maxPages) {
        int students = 1;
        int currentSum = 0;
        
        for (int pages : arr) {
            if (currentSum + pages > maxPages) {
                students++;
                currentSum = pages;
                if (students > k) {
                    return false;
                }
            } else {
                currentSum += pages;
            }
        }
        return true;
    }
}
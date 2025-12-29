class Solution {
    public int kthElement(int a[], int b[], int k) {
        int n1 = a.length, n2 = b.length;
        
        // Ensure a is smaller
        if (n1 > n2) {
            return kthElement(b, a, k);
        }
        
        int low = Math.max(0, k - n2), high = Math.min(k, n1);
        
        while (low <= high) {
            int cutA = low + (high - low) / 2;
            int cutB = k - cutA;
            
            int leftA = (cutA == 0) ? Integer.MIN_VALUE : a[cutA - 1];
            int rightA = (cutA == n1) ? Integer.MAX_VALUE : a[cutA];
            int leftB = (cutB == 0) ? Integer.MIN_VALUE : b[cutB - 1];
            int rightB = (cutB == n2) ? Integer.MAX_VALUE : b[cutB];
            
            if (leftA <= rightB && leftB <= rightA) {
                // Found valid partition
                return Math.max(leftA, leftB);
            } else if (leftA > rightB) {
                high = cutA - 1;
            } else {
                low = cutA + 1;
            }
        }
        
        return -1; // Should never reach
    }
}
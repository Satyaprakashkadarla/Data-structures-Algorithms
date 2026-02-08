class Solution {
    public int maxProduct(int[] arr) {
        int maxEnd = arr[0], minEnd = arr[0], maxProd = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < 0) {
                int temp = maxEnd;
                maxEnd = minEnd;
                minEnd = temp;
            }
            maxEnd = Math.max(arr[i], maxEnd * arr[i]);
            minEnd = Math.min(arr[i], minEnd * arr[i]);
            maxProd = Math.max(maxProd, maxEnd);
        }
        
        return maxProd;
    }
}
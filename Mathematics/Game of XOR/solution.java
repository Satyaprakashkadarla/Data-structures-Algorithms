class Solution {
    public int subarrayXor(int[] arr) {
        int n = arr.length;
        int result = 0;
        
        // If n is even, all elements appear even times → result = 0
        // If n is odd, only elements at even indices appear odd times
        if (n % 2 == 0) {
            return 0;
        }
        
        for (int i = 0; i < n; i += 2) {
            result ^= arr[i];
        }
        
        return result;
    }
}
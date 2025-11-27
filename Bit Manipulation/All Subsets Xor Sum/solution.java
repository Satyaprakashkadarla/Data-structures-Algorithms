class Solution {
    int subsetXORSum(int arr[]) {
        int n = arr.length;
        int OR = 0;
        
        // Compute bitwise OR of all elements
        for (int num : arr) {
            OR |= num;
        }
        
        // Multiply by 2^(n-1)
        return OR * (1 << (n - 1));
    }
}
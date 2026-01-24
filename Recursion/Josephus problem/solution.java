class Solution {
    public int josephus(int n, int k) {
        int safePosition = 0; // For 1 person, safe position is 0 (0-based index)
        
        // Build up the solution for i people
        for (int i = 2; i <= n; i++) {
            safePosition = (safePosition + k) % i;
        }
        
        // Convert from 0-based to 1-based indexing
        return safePosition + 1;
    }
}
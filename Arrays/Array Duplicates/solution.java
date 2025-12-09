class Solution {
    public ArrayList<Integer> findDuplicates(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        int n = arr.length;
        
        for (int i = 0; i < n; i++) {
            int val = Math.abs(arr[i]);
            int index = val - 1;
            
            if (arr[index] < 0) {
                // Already seen
                result.add(val);
            } else {
                // Mark as visited
                arr[index] = -arr[index];
            }
        }
        
        return result;
    }
}
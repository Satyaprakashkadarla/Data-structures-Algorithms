class Solution {
    ArrayList<Integer> findTwoElement(int arr[]) {
        int n = arr.length;
        int dup = -1, miss = -1;
        
        // First pass: find duplicate using negation
        for (int i = 0; i < n; i++) {
            int val = Math.abs(arr[i]);
            int idx = val - 1;
            if (arr[idx] < 0) {
                dup = val;
            } else {
                arr[idx] = -arr[idx];
            }
        }
        
        // Second pass: find missing (positive index)
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                miss = i + 1;
                break;
            }
        }
        
        ArrayList<Integer> res = new ArrayList<>();
        res.add(dup);
        res.add(miss);
        return res;
    }
}
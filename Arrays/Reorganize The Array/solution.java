class Solution {
    public List<Integer> rearrange(int[] arr) {
        int n = arr.length;
        int[] util = new int[n];
        
        // Fill the utility array
        for (int i : arr) {
            if (i >= 0 && i < n) {
                util[i] = 1;
            }
        }
        
        List<Integer> ans = new ArrayList<>();
        
        // Construct the answer list
        for (int i = 0; i < n; i++) {
            if (util[i] > 0) {
                ans.add(i);
            } else {
                ans.add(-1);
            }
        }
        
        return ans;
    }
}
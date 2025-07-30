class Solution {
    public int cntSubarrays(int[] arr, int k) {
        int count = 0;
        int prefixSum = 0;
        HashMap<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1); 
        
        for (int num : arr) {
            prefixSum += num;
            if (prefixCount.containsKey(prefixSum - k)) {
                count += prefixCount.get(prefixSum - k);
            }
            prefixCount.put(prefixSum, prefixCount.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}

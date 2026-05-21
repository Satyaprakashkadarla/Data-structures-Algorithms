class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        // Store all possible prefixes from arr1
        Set<Integer> prefixSet = new HashSet<>();
      
        
        for (int number : arr1) {
            while (number > 0) {
                prefixSet.add(number);
                number /= 10;  // Remove the last digit
            }
        }
      
        int maxPrefixLength = 0;
      
        for (int number : arr2) {
            
            while (number > 0) {
                if (prefixSet.contains(number)) {
                    int currentPrefixLength = String.valueOf(number).length();
                    maxPrefixLength = Math.max(maxPrefixLength, currentPrefixLength);
                    break;  // No need to check shorter prefixes
                }
                number /= 10;  // Remove the last digit to get shorter prefix
            }
        }
      
        return maxPrefixLength;
    }
}

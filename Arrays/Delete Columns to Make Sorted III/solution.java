class Solution {
    public int minDeletionSize(String[] strs) {
        int columnCount = strs[0].length();
        int[] dp = new int[columnCount];
        Arrays.fill(dp, 1);
        for (int currentCol = 1; currentCol < columnCount; ++currentCol) {
           
            for (int previousCol = 0; previousCol < currentCol; ++previousCol) {
               
                boolean isNonDecreasing = true;
                for (String str : strs) {
                    
                    if (str.charAt(previousCol) > str.charAt(currentCol)) {
                        isNonDecreasing = false;
                        break;
                    }
                }
              
                if (isNonDecreasing) {
                    dp[currentCol] = Math.max(dp[currentCol], dp[previousCol] + 1);
                }
            }
        }
      
        int maxIncreasingLength = Arrays.stream(dp).max().getAsInt();
      
        return columnCount - maxIncreasingLength;
    }
}

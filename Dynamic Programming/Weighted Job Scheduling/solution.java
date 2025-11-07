import java.util.*;

class Solution {
    public int maxProfit(int[][] jobs) {
        int n = jobs.length;
        
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
        
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0); // Base case
        
        int maxProfit = 0;
        
        for (int[] job : jobs) {
            int start = job[0];
            int end = job[1];
            int profit = job[2];
            
            Integer prevEnd = dp.floorKey(start);
            int currentProfit = (prevEnd != null ? dp.get(prevEnd) : 0) + profit;
            
            if (currentProfit > maxProfit) {
                maxProfit = currentProfit;
                dp.put(end, maxProfit);
            }
        }
        
        return maxProfit;
    }
}
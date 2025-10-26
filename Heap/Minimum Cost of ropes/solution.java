import java.util.PriorityQueue;

class Solution {
    public static int minCost(int[] arr) {
        // If there's only one rope or no ropes, cost is 0
        if (arr == null || arr.length <= 1) {
            return 0;
        }
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int rope : arr) {
            minHeap.offer(rope);
        }
        
        int totalCost = 0;
        
        // Continue until only one rope remains
        while (minHeap.size() > 1) {
            // Extract two smallest ropes
            int first = minHeap.poll();
            int second = minHeap.poll();
            
            // Connect them and calculate cost
            int cost = first + second;
            totalCost += cost;
            
            // Add the new rope back to heap
            minHeap.offer(cost);
        }
        
        return totalCost;
    }
}
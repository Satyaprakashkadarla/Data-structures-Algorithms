import java.util.PriorityQueue;
import java.util.Collections;

class Solution {
    public int minOperations(int[] arr) {
        PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
        double totalSum = 0;
        
        for (int num : arr) {
            pq.add((double) num);
            totalSum += num;
        }
        
        double target = totalSum / 2.0;
        int operations = 0;
        
        while (totalSum > target) {
            operations++;
            double largest = pq.poll();
            totalSum -= largest;
            double half = largest / 2.0;
            totalSum += half;
            pq.add(half);
        }
        
        return operations;
    }
}

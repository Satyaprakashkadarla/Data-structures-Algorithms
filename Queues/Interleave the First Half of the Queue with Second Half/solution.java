import java.util.*;

class Solution {
    public void rearrangeQueue(Queue<Integer> q) {
        int n = q.size();
        Queue<Integer> firstHalf = new LinkedList<>();
        
        // Move first half to separate queue
        for (int i = 0; i < n / 2; i++) {
            firstHalf.add(q.poll());
        }
        
        // Interleave
        while (!firstHalf.isEmpty()) {
            q.add(firstHalf.poll());
            q.add(q.poll());
        }
    }
}
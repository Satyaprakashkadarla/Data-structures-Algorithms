import java.util.*;

class Solution {
    public int kBitFlips(int[] arr, int k) {
        int n = arr.length;
        int ops = 0;
        Queue<Integer> q = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            // Remove outdated flips
            if (!q.isEmpty() && q.peek() + k <= i) {
                q.poll();
            }
            
            // Current value after previous flips
            int current = arr[i] ^ (q.size() % 2);
            if (current == 0) {
                if (i + k > n) return -1; // not enough room to flip
                q.add(i);
                ops++;
            }
        }
        return ops;
    }
}
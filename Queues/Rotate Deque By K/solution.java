import java.util.Deque;

class Solution {
    public static void rotateDeque(Deque<Integer> dq, int type, int k) {
        int n = dq.size();
        if (n == 0) return;
        
        k = k % n; 

        if (type == 1) {
            for (int i = 0; i < k; i++) {
                int last = dq.pollLast();
                dq.addFirst(last);
            }
        } else if (type == 2) {
            for (int i = 0; i < k; i++) {
                int first = dq.pollFirst();
                dq.addLast(first);
            }
        }
    }
}

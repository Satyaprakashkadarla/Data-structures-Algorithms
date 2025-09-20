import java.util.Stack;

class Solution {
    public int longestSubarray(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] next = new int[n];
        int[] prev = new int[n];
        
        for (int i = 0; i < n; i++) {
            next[i] = n;
            prev[i] = -1;
        }
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                next[stack.pop()] = i;
            }
            stack.push(i);
        }
        
        stack.clear();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                prev[stack.pop()] = i;
            }
            stack.push(i);
        }
        
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int len = next[i] - prev[i] - 1;
            if (arr[i] <= len) {
                ans = Math.max(ans, len);
            }
        }
        
        return ans;
    }
}

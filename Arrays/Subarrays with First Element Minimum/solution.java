import java.util.*;

class Solution {
    public int countSubarrays(int[] arr) {
        int n = arr.length;
        int[] nextSmaller = new int[n];
        Arrays.fill(nextSmaller, n);
        
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                nextSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }
        
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += (nextSmaller[i] - i);
        }
        return total;
    }
}
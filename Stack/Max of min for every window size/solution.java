import java.util.*;

class Solution {
    public ArrayList<Integer> maxOfMins(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));
        
        int[] nextSmaller = new int[n];
        int[] prevSmaller = new int[n];
        
        Arrays.fill(nextSmaller, n);
        Arrays.fill(prevSmaller, -1);
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                nextSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }
        
        stack.clear();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                prevSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }
        
        
        for (int i = 0; i < n; i++) {
            int windowSize = nextSmaller[i] - prevSmaller[i] - 1;
            result.set(windowSize - 1, Math.max(result.get(windowSize - 1), arr[i]));
        }
        
        for (int i = n - 2; i >= 0; i--) {
            result.set(i, Math.max(result.get(i), result.get(i + 1)));
        }
        
        return result;
    }
}
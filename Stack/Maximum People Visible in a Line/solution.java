import java.util.*;

class Solution {
    public int maxPeople(int[] arr) {
        int n = arr.length;
        int[] leftGE = new int[n];
        int[] rightGE = new int[n];
        
        // Next greater or equal on right
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                rightGE[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            rightGE[stack.pop()] = n;
        }
        
        // Next greater or equal on left
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                leftGE[stack.pop()] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            leftGE[stack.pop()] = -1;
        }
        
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            int leftBound = leftGE[i];
            int rightBound = rightGE[i];
            int count = (i - leftBound - 1) + (rightBound - i - 1) + 1;
            maxCount = Math.max(maxCount, count);
        }
        
        return maxCount;
    }
}
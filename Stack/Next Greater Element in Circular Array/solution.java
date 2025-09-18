import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    public ArrayList<Integer> nextGreater(int[] arr) {
        int n = arr.length;
        ArrayList<Integer> result = new ArrayList<>(n);
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            result.add(-1);
        }
        
        for (int i = 0; i < 2 * n; i++) {
            int currentIndex = i % n;
            
            while (!stack.isEmpty() && arr[stack.peek()] < arr[currentIndex]) {
                int index = stack.pop();
                result.set(index, arr[currentIndex]);
            }
            
            if (i < n) {  
                stack.push(currentIndex);
            }
        }
        
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] arr1 = {1, 3, 2, 4};
        int[] arr2 = {0, 2, 3, 1, 1};
        
        System.out.println(solution.nextGreater(arr1)); // Output: [3, 4, 4, -1]
        System.out.println(solution.nextGreater(arr2)); // Output: [2, 3, -1, 2, 2]
    }
}

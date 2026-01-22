import java.util.*;

class Solution {
    public int subarrayRanges(int[] arr) {
        int n = arr.length;
        long sumMax = sumOfMax(arr);
        long sumMin = sumOfMin(arr);
        return (int)(sumMax - sumMin);
    }
    
    private long sumOfMax(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] nextGreater = new int[n];
        int[] prevGreater = new int[n];
        
        Arrays.fill(nextGreater, n);
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                nextGreater[stack.pop()] = i;
            }
            stack.push(i);
        }
        stack.clear();
        
        Arrays.fill(prevGreater, -1);
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                prevGreater[stack.pop()] = i;
            }
            stack.push(i);
        }
        
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long left = i - prevGreater[i];
            long right = nextGreater[i] - i;
            sum += arr[i] * left * right;
        }
        return sum;
    }
    
    private long sumOfMin(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] nextSmaller = new int[n];
        int[] prevSmaller = new int[n];
        
        Arrays.fill(nextSmaller, n);
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                nextSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }
        stack.clear();
        
        Arrays.fill(prevSmaller, -1);
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                prevSmaller[stack.pop()] = i;
            }
            stack.push(i);
        }
        
        long sum = 0;
        for (int i = 0; i < n; i++) {
            long left = i - prevSmaller[i];
            long right = nextSmaller[i] - i;
            sum += arr[i] * left * right;
        }
        return sum;
    }
}
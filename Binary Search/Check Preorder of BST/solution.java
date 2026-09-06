class Solution {
    public boolean canRepresentBST(List<Integer> arr) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num < root) return false;
            while (!stack.isEmpty() && stack.peek() < num) {
                root = stack.pop();
            }
            stack.push(num);
        }
        return true;
    }
}
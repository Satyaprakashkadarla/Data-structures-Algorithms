
class Solution {
    public ArrayList<Integer> calculateSpan(int[] arr) {
        ArrayList<Integer> span = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<arr.length;i++) {
            while(!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                span.add(i+1);
            } else {
                span.add(i-stack.peek());
            }
            stack.push(i);
        }
        return span;
    }
}
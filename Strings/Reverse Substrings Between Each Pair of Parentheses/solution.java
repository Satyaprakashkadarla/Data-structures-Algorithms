class Solution {
    public String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        int[] stack = new int[s.length()];
        int top = -1;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack[++top] = sb.length();
            } else if (c == ')') {
                int start = stack[top--];

                // Reverse characters inside the matching parentheses
                for (int i = start, j = sb.length() - 1; i < j; i++, j--) {
                    char temp = sb.charAt(i);
                    sb.setCharAt(i, sb.charAt(j));
                    sb.setCharAt(j, temp);
                }
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}

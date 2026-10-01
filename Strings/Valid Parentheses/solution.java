class Solution {
    public boolean isValid(String s) {
        char[] stack = new char[s.length()];
        int top = -1;

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack[++top] = c;
            } else {
                if (top < 0) return false;

                char open = stack[top--];

                if ((c == ')' && open != '(') ||
                    (c == ']' && open != '[') ||
                    (c == '}' && open != '{')) {
                    return false;
                }
            }
        }

        return top == -1;
    }
}

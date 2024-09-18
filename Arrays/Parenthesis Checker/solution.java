import java.util.Stack;

class Solution {
    public boolean ispar(String x) {
        Stack<Character> st = new Stack<>();
        
        for (int i = 0; i < x.length(); i++) {
            char ch = x.charAt(i);
            
            // Push opening brackets onto the stack
            if (ch == '{' || ch == '[' || ch == '(') {
                st.push(ch);
            } else {
                // If stack is empty or top doesn't match, return false
                if (st.isEmpty()) {
                    return false;
                }
                
                if (ch == '}' && st.peek() != '{') {
                    return false;
                } else if (ch == ']' && st.peek() != '[') {
                    return false;
                } else if (ch == ')' && st.peek() != '(') {
                    return false;
                }
                
                st.pop(); // Pop the matched opening bracket
            }
        }
        
        // Return true if stack is empty, meaning all brackets are matched
        return st.isEmpty();
    }
}

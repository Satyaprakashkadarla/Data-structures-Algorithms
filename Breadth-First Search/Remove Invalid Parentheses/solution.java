import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();

        int leftRemove = 0;
        int rightRemove = 0;

        // Calculate minimum removals needed
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove > 0) {
                    leftRemove--;
                } else {
                    rightRemove++;
                }
            }
        }

        dfs(s, 0, leftRemove, rightRemove, ans);

        return ans;
    }

    private void dfs(String s, int start,
                     int leftRemove, int rightRemove,
                     List<String> ans) {

        // If no removals are left, check validity
        if (leftRemove == 0 && rightRemove == 0) {
            if (isValid(s)) {
                ans.add(s);
            }
            return;
        }

        for (int i = start; i < s.length(); i++) {

            // Skip duplicate removals
            if (i > start && s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            char c = s.charAt(i);

            // Remove '('
            if (c == '(' && leftRemove > 0) {
                String next = s.substring(0, i) + s.substring(i + 1);

                dfs(next, i, leftRemove - 1, rightRemove, ans);
            }

            // Remove ')'
            if (c == ')' && rightRemove > 0) {
                String next = s.substring(0, i) + s.substring(i + 1);

                dfs(next, i, leftRemove, rightRemove - 1, ans);
            }
        }
    }

    private boolean isValid(String s) {
        int balance = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                if (--balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}

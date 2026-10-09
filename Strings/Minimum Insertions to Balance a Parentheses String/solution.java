class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int open = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                open++;
            } else {
                // Check whether the next character is ')'
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++;
                } else {
                    // Insert one ')' to form '))'
                    insertions++;
                }

                if (open > 0) {
                    open--;
                } else {
                    // Insert '(' to match this '))'
                    insertions++;
                }
            }
        }

        // Each unmatched '(' requires two ')'
        insertions += open * 2;

        return insertions;
    }
}
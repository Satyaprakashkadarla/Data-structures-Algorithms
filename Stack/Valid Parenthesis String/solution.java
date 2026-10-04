class Solution {
    public boolean checkValidString(String s) {
        int minOpen = 0;
        int maxOpen = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                minOpen++;
                maxOpen++;
            } else if (c == ')') {
                minOpen--;
                maxOpen--;
            } else { // '*'
                minOpen--; // '*' as ')'
                maxOpen++; // '*' as '('
            }

            // Too many ')' in every possible interpretation
            if (maxOpen < 0) {
                return false;
            }

            // minOpen cannot be negative
            minOpen = Math.max(0, minOpen);
        }

        return minOpen == 0;
    }
}

class Solution {
    public int minParentheses(String s) {
        int res = 0, st = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                st++;
            } else if (st > 0) {
                st--;
            } else {
                res++;
            }
        }
        return res + st;
    }
}

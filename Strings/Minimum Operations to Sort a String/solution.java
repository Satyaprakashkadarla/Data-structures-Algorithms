class Solution {
    public int minOperations(String s) {
        int n = s.length();
        boolean isSorted = true;
        char mn = s.charAt(0);
        char mx = s.charAt(0);

        // Check if the string is already sorted and find min/max characters
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (c < s.charAt(i - 1)) {
                isSorted = false;
            }
            mn = (char) Math.min(mn, c);
            mx = (char) Math.max(mx, c);
        }

        if (isSorted) {
            return 0;
        }
        if (n == 2) {
            return -1;
        }

        // If min is at the start or max is at the end (and length > 2)
        if (s.charAt(0) == mn || s.charAt(n - 1) == mx) {
            return 1;
        }

        // Check if min or max lies in any proper interior substring position
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == mn || s.charAt(i) == mx) {
                return 2;
            }
        }

        return 3;
    }
}
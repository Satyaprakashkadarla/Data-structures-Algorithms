class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] last = new int[m];
        java.util.Arrays.fill(last, -1);

        int i = n - 1;
        int j = m - 1;

        // Find rightmost positions for an exact suffix match.
        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }

        int[] ans = new int[m];

        i = 0;
        j = 0;
        boolean mismatchUsed = false;

        while (i < n && j < m) {
            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j++] = i;
            } else if (!mismatchUsed &&
                       (j == m - 1 || i < last[j + 1])) {
                ans[j++] = i;
                mismatchUsed = true;
            }

            i++;
        }

        return j == m ? ans : new int[0];
    }
}
class Solution {
    public String minWindow(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        
        // next[i][ch] = next index of char ch in s1 starting from i
        int[][] next = new int[n + 1][26];
        for (int ch = 0; ch < 26; ch++) {
            next[n][ch] = -1;
        }
        
        // Fill next table from right to left
        for (int i = n - 1; i >= 0; i--) {
            for (int ch = 0; ch < 26; ch++) {
                next[i][ch] = next[i + 1][ch];
            }
            next[i][s1.charAt(i) - 'a'] = i;
        }
        
        int minLen = Integer.MAX_VALUE;
        int startIdx = -1;
        
        // Try each start position
        for (int i = 0; i < n; i++) {
            int pos = i;
            boolean found = true;
            for (int j = 0; j < m; j++) {
                char c = s2.charAt(j);
                if (next[pos][c - 'a'] == -1) {
                    found = false;
                    break;
                }
                pos = next[pos][c - 'a'] + 1;
                if (pos == 0) {
                    found = false;
                    break;
                }
            }
            if (found) {
                int len = pos - i;
                if (len < minLen) {
                    minLen = len;
                    startIdx = i;
                }
            }
        }
        
        if (startIdx == -1) return "";
        return s1.substring(startIdx, startIdx + minLen);
    }
}
class Solution {
    public int longestBalanced(String s) {
        char[] array = s.toCharArray();
        int[] freq = null;
        int n = array.length;
        int ans = 1;
        for (int i = 0; i < n - 1 & (n - i > ans); i++) {
            freq = new int[128];
            int distinct = 0;
            int maxFreq = 1;
            for (int j = i; j < n; j++) {
                freq[array[j]]++;
                if (freq[array[j]] == 1) {
                    distinct++;
                }
                maxFreq = Math.max(maxFreq, freq[array[j]]);
                if ((j - i + 1) == maxFreq * distinct) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }
        return ans;
    }
}
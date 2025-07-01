class Solution {
    public int substrCount(String s, int k) {
        int n      = s.length();
        int result = 0;

        for (int i = 0; i <= n - k; i++) {
            int[] freq = new int[26]; // reset frequency array for each substring

            for (int j = i; j < i + k; j++) {
                freq[s.charAt(j) - 'a']++; // count frequency of characters in current substring
            }

            int count = 0;
            for (int l = 0; l < 26; l++) {
                if (freq[l] > 0) {
                    count++; // count distinct characters
                }
            }

            if (count == k - 1) {
                result++; // valid substring
            }
        }

        return result;
    }
}

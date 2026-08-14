class Solution {
    public int maximumLengthSubstring(String s) {
        int[] count = new int[26];
        int left = 0, maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            int idx = s.charAt(right) - 'a';
            count[idx]++;

            // Restore the condition: each character appears at most twice
            while (count[idx] > 2) {
                count[s.charAt(left) - 'a']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
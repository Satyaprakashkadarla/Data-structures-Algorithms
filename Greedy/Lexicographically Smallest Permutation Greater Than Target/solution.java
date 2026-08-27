class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }
        
        StringBuilder res = new StringBuilder();
        
        // Try to match the prefix of target as much as possible
        for (int i = 0; i < n; i++) {
            int targetChar = target.charAt(i) - 'a';
            
            // Case 1: Try placing the exact character target[i]
            if (cnt[targetChar] > 0) {
                cnt[targetChar]--;
                if (canFormGreater(cnt, target, i + 1)) {
                    res.append(target.charAt(i));
                    continue;
                }
                // Backtrack if we can't form a greater string down this path
                cnt[targetChar]++;
            }
            
            // Case 2: Find the smallest available character strictly greater than target[i]
            for (int j = targetChar + 1; j < 26; j++) {
                if (cnt[j] > 0) {
                    cnt[j]--;
                    res.append((char) ('a' + j));
                    res.append(getMinString(cnt));
                    return res.toString();
                }
            }
            
            return "";
        }
        
        return "";
    }
    
    // Checks if the remaining characters can form a string lexicographically greater than target[start...]
    private boolean canFormGreater(int[] cnt, String target, int start) {
        String maxStr = getMaxString(cnt);
        String suffix = target.substring(start);
        return maxStr.compareTo(suffix) > 0;
    }
    
    // Generates the lexicographically largest string (descending order) from remaining counts
    private String getMaxString(int[] cnt) {
        StringBuilder sb = new StringBuilder();
        for (int i = 25; i >= 0; i--) {
            for (int k = 0; k < cnt[i]; k++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }
    
    // Generates the lexicographically smallest string (ascending order) from remaining counts
    private String getMinString(int[] cnt) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            for (int k = 0; k < cnt[i]; k++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }
}
class Solution {
    public static String smallestWindow(String s, String t) {
        int n = s.length();
        int[] f = new int[26];
        int minLen = Integer.MAX_VALUE;
        int resStart = 0;

        for (char ch : t.toCharArray()) f[ch - 'a']++;

        int count = t.length(), l = 0;

        for (int r = 0; r < n; r++) {
            
            char ch = s.charAt(r);
            if (f[ch - 'a'] > 0) count--;
            f[ch - 'a']--;

            while (count == 0) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    resStart = l;
                }

                char ch2 = s.charAt(l);
                f[ch2 - 'a']++;
                if (f[ch2 - 'a'] > 0) count++;
                l++;
            }
            
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(resStart, resStart + minLen);
    }
}


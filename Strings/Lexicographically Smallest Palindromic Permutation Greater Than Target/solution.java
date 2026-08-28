import java.util.Arrays;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        int midKey = -1;
        for (int c = 0; c < 26; c++) {
            if (cnt[c] % 2 == 1) {
                if (n % 2 == 0 || midKey >= 0) {
                    return "";
                }
                midKey = c;
            }
        }

        StringBuilder ans = new StringBuilder();
        int halfLen = n / 2;

        for (int i = 0; i < halfLen; i++) {
            boolean placed = false;
            for (int c = 0; c < 26; c++) {
                if (cnt[c] >= 2) {
                    cnt[c] -= 2;
                    ans.append((char) ('a' + c));

                    if (isValid(ans, cnt, midKey, n, target)) {
                        placed = true;
                        break;
                    }

                    ans.deleteCharAt(ans.length() - 1);
                    cnt[c] += 2;
                }
            }
            if (!placed) {
                return "";
            }
        }

        StringBuilder res = new StringBuilder(ans);
        if (midKey >= 0) {
            res.append((char) ('a' + midKey));
        }
        res.append(ans.reverse());

        String finalStr = res.toString();
        return finalStr.compareTo(target) > 0 ? finalStr : "";
    }

    private boolean isValid(StringBuilder ans, int[] cnt, int midKey, int n, String target) {
        StringBuilder half = new StringBuilder(ans);
        for (int d = 25; d >= 0; d--) {
            for (int i = 0; i < cnt[d] / 2; i++) {
                half.append((char) ('a' + d));
            }
        }

        StringBuilder tmp = new StringBuilder(half);
        if (midKey >= 0) {
            tmp.append((char) ('a' + midKey));
        }
        StringBuilder reversedHalf = new StringBuilder(half).reverse();
        tmp.append(reversedHalf);

        return tmp.toString().compareTo(target) > 0;
    }
}
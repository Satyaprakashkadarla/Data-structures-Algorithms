class Solution {
    public String findLongestWord(String s, List<String> d) {
        String ans = "";

        for (String word : d) {
            if (word.length() < ans.length()) {
                continue;
            }

            if (word.length() == ans.length() && word.compareTo(ans) >= 0) {
                continue;
            }

            if (isSubsequence(s, word)) {
                ans = word;
            }
        }

        return ans;
    }

    private boolean isSubsequence(String s, String word) {
        int j = 0;

        for (int i = 0; i < s.length() && j < word.length(); i++) {
            if (s.charAt(i) == word.charAt(j)) {
                j++;
            }
        }

        return j == word.length();
    }
}

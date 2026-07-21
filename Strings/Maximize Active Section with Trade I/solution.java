class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        String t = "1" + s + "1";
        int m = t.length();

        int initialOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') initialOnes++;
        }

        ArrayList<Character> chars = new ArrayList<>();
        ArrayList<Integer> lens = new ArrayList<>();

        int i = 0;
        while (i < m) {
            char ch = t.charAt(i);
            int j = i;
            while (j < m && t.charAt(j) == ch) j++;
            chars.add(ch);
            lens.add(j - i);
            i = j;
        }

        int bestGain = 0;

        for (int k = 1; k + 1 < chars.size(); k++) {
            if (chars.get(k) == '1'
                    && chars.get(k - 1) == '0'
                    && chars.get(k + 1) == '0') {
                bestGain = Math.max(bestGain,
                        lens.get(k - 1) + lens.get(k + 1));
            }
        }

        return initialOnes + bestGain;
    }
}
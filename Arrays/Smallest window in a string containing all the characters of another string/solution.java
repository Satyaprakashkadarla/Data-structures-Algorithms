import java.util.HashMap;

class Solution {
    public String smallestWindow(String s, String p) {
        HashMap<Character, Integer> dp = new HashMap<>();
        for (char ch : p.toCharArray()) {
            dp.put(ch, dp.getOrDefault(ch, 0) + 1);
        }

        HashMap<Character, Integer> dp1 = new HashMap<>();
        int j = 0, ans = Integer.MAX_VALUE;
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            dp1.put(s.charAt(i), dp1.getOrDefault(s.charAt(i), 0) + 1);

            while (j < i && dp1.getOrDefault(s.charAt(j), 0) > dp.getOrDefault(s.charAt(j), 0)) {
                dp1.put(s.charAt(j), dp1.get(s.charAt(j)) - 1);
                if (dp1.get(s.charAt(j)) == 0) {
                    dp1.remove(s.charAt(j));
                }
                j++;
            }

            boolean anss = true;
            for (char ch : dp.keySet()) {
                if (dp.get(ch) > dp1.getOrDefault(ch, 0)) {
                    anss = false;
                    break;
                }
            }

            if (anss && i - j + 1 < ans) {
                res = s.substring(j, i + 1);
                ans = i - j + 1;
            }
        }

        return ans < Integer.MAX_VALUE ? res : "-1";
    }
}

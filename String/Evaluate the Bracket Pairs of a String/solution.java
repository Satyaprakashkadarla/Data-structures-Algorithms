class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>(knowledge.size() * 2);

        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder(s.length());

        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) != '(') {
                ans.append(s.charAt(i++));
                continue;
            }

            // Find closing bracket
            int j = i + 1;
            while (s.charAt(j) != ')') {
                j++;
            }

            String key = s.substring(i + 1, j);
            ans.append(map.getOrDefault(key, "?"));

            i = j + 1;
        }

        return ans.toString();
    }
}

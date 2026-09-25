class Solution {
    private String s;
    private int i;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        i = 0;

        Set<String> result = parseExpression();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    // Parses a sequence of terms until ',' or '}'.
    private Set<String> parseExpression() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (i < s.length() && s.charAt(i) != '}' && s.charAt(i) != ',') {
            Set<String> part = parseTerm();
            result = combine(result, part);
        }

        return result;
    }

    // Parses either a letter or a {...} expression.
    private Set<String> parseTerm() {
        if (s.charAt(i) == '{') {
            i++; // skip '{'

            Set<String> result = new HashSet<>();

            while (true) {
                result.addAll(parseExpression());

                if (s.charAt(i) == ',') {
                    i++; // skip ','
                } else {
                    break;
                }
            }

            i++; // skip '}'
            return result;
        }

        // Single lowercase letter
        Set<String> result = new HashSet<>();
        result.add(String.valueOf(s.charAt(i++)));
        return result;
    }

    // Cartesian product + concatenation.
    private Set<String> combine(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}

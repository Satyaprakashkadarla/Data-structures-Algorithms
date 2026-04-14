class Solution {
    String removeSpaces(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (ch != ' ') {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
class Solution {
    public String removeKdig(String s, int k) {
        if (k == s.length()) return "0";
        
        StringBuilder stack = new StringBuilder();
        
        for (char ch : s.toCharArray()) {
            while (k > 0 && stack.length() > 0 && stack.charAt(stack.length() - 1) > ch) {
                stack.deleteCharAt(stack.length() - 1);
                k--;
            }
            stack.append(ch);
        }
        
        // If k still > 0, remove from end
        while (k > 0) {
            stack.deleteCharAt(stack.length() - 1);
            k--;
        }
        
        // Remove leading zeros
        int start = 0;
        while (start < stack.length() && stack.charAt(start) == '0') {
            start++;
        }
        
        String result = stack.substring(start);
        return result.isEmpty() ? "0" : result;
    }
}
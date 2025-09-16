class Solution {
    public String maxSubseq(String s, int k) {
        int n = s.length();
        int toRemove = k;                   
        StringBuilder result   = new StringBuilder(); 

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            while (result.length() > 0 && toRemove > 0 && c > result.charAt(result.length() - 1)) {
                result.deleteCharAt(result.length() - 1);
                toRemove--;
            }
            result.append(c); 
        }

        result.setLength(n - k);
        return result.toString();
    }
}
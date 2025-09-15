class Solution {
    public boolean stringStack(String pat, String tar) {
        int i = pat.length() - 1, j = tar.length() - 1;
        if(j > i) return false;
        
        while (i >= 0 && j >= 0) {
            
            if (pat.charAt(i) != tar.charAt(j)) i -= 2;
            else {
                i--;
                j--;
            }
        }
        
        return j < 0;
    }
}
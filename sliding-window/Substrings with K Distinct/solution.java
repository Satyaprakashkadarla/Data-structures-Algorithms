import java.util.*;

class Solution {
    public int countSubstr(String s, int k) {
        return atMostK(s, k) - atMostK(s, k - 1);
    }
    
    private int atMostK(String s, int k) {
        if (k < 0) return 0;
        
        int[] freq = new int[26];
        int left = 0, distinct = 0;
        int total = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (freq[c - 'a'] == 0) distinct++;
            freq[c - 'a']++;
            
            while (distinct > k) {
                char leftChar = s.charAt(left);
                freq[leftChar - 'a']--;
                if (freq[leftChar - 'a'] == 0) distinct--;
                left++;
            }
            
            total += (right - left + 1);
        }
        
        return total;
    }
}
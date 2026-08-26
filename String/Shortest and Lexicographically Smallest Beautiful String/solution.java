class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String result = "";
        int minLen = Integer.MAX_VALUE;
        
        // Sliding window to find all substrings with exactly k ones
        int left = 0, ones = 0;
        
        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') ones++;
            
            // Shrink window while we have more than k ones
            while (ones > k) {
                if (s.charAt(left) == '1') ones--;
                left++;
            }
            
            // When exactly k ones, check all substrings ending at right
            if (ones == k) {
                // Try all possible starting positions that maintain k ones
                int tempLeft = left;
                int tempOnes = ones;
                
                // Move left pointer while keeping exactly k ones
                while (tempLeft <= right) {
                    if (tempOnes == k) {
                        int len = right - tempLeft + 1;
                        String sub = s.substring(tempLeft, right + 1);
                        
                        if (len < minLen) {
                            minLen = len;
                            result = sub;
                        } else if (len == minLen && sub.compareTo(result) < 0) {
                            result = sub;
                        }
                    }
                    
                    if (s.charAt(tempLeft) == '1') tempOnes--;
                    tempLeft++;
                }
            }
        }
        
        return result;
    }
}
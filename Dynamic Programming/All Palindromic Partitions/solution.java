class Solution {
    // Function to check if a substring s[l..r] is a palindrome
    boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false; // Return false if characters at both ends don't match
            }
            l++;              // Move left pointer forward
            r--;              // Move right pointer backward
        }
        return true;          // All characters matched, hence it's a palindrome
    }

    // Recursive backtracking to explore all palindromic partitions
    void backtrack(int idx,
                   String s,
                   ArrayList<String> curr,
                   ArrayList<ArrayList<String> > result) {
        if (idx == s.length()) {
            result.add(new ArrayList<>(curr)); // Add current partition to result if end of string is reached
            return;
        }

        for (int i = idx; i < s.length(); i++) {
            // Check if s[idx..i] is a palindrome
            if (isPalindrome(s, idx, i)) {
                // If yes, add it to the current path
                curr.add(s.substring(idx, i + 1));

                // Recurse for the remaining substring
                backtrack(i + 1, s, curr, result);

                // Backtrack: remove last added substring and try next possibility
                curr.remove(curr.size() - 1);
            }
        }
    }
    
    // Entry function to initiate backtracking
    public ArrayList<ArrayList<String> > palinParts(String s) {
        ArrayList<ArrayList<String> > result = new ArrayList<>(); // Final result of all partitions
        ArrayList<String>             curr   = new ArrayList<>(); // Current partition path

        backtrack(0, s, curr, result);                            // Start from index 0
        return result;
    }
}
import java.util.*;

class Solution {
    public String firstNonRepeating(String s) {
        int[] freq = new int[26];
        Queue<Character> queue = new LinkedList<>();
        StringBuilder result = new StringBuilder();
        
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
            if (freq[ch - 'a'] == 1) {
                queue.add(ch);
            }
            while (!queue.isEmpty() && freq[queue.peek() - 'a'] > 1) {
                queue.poll();
            }
            result.append(queue.isEmpty() ? '#' : queue.peek());
        }
        
        return result.toString();
    }
}
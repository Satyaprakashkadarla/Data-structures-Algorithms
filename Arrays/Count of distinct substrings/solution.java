class Solution {
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
    }
    
    public static int countSubs(String s) {
        TrieNode root = new TrieNode();
        int count = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            TrieNode current = root;
            for (int j = i; j < n; j++) {
                int idx = s.charAt(j) - 'a';
                if (current.children[idx] == null) {
                    current.children[idx] = new TrieNode();
                    count++;
                }
                current = current.children[idx];
            }
        }
        
        return count;
    }
}
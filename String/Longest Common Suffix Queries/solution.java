class Solution {

    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int bestIndex = -1;
    }

    TrieNode root = new TrieNode();
    String[] words;

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        words = wordsContainer;

        // Build trie using reversed words
        for (int i = 0; i < wordsContainer.length; i++) {
            insert(wordsContainer[i], i);
        }

        int[] ans = new int[wordsQuery.length];

        for (int i = 0; i < wordsQuery.length; i++) {
            ans[i] = search(wordsQuery[i]);
        }

        return ans;
    }

    private void insert(String word, int index) {
        TrieNode node = root;

        // Update root best candidate
        updateBest(node, index);

        for (int i = word.length() - 1; i >= 0; i--) {
            int c = word.charAt(i) - 'a';

            if (node.child[c] == null) {
                node.child[c] = new TrieNode();
            }

            node = node.child[c];

            updateBest(node, index);
        }
    }

    private void updateBest(TrieNode node, int index) {
        if (node.bestIndex == -1) {
            node.bestIndex = index;
            return;
        }

        int currLen = words[node.bestIndex].length();
        int newLen = words[index].length();

        if (newLen < currLen ||
           (newLen == currLen && index < node.bestIndex)) {
            node.bestIndex = index;
        }
    }

    private int search(String query) {
        TrieNode node = root;

        for (int i = query.length() - 1; i >= 0; i--) {
            int c = query.charAt(i) - 'a';

            if (node.child[c] == null) {
                break;
            }

            node = node.child[c];
        }

        return node.bestIndex;
    }
}
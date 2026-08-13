import java.util.*;

class Solution {
    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;

        char[] a = s.toCharArray();

        // start -> end of the maximal run [start, end]
        TreeMap<Integer, Integer> runs = new TreeMap<>();

        // length -> number of runs having this length
        TreeMap<Integer, Integer> lengths = new TreeMap<>();

        // Build initial runs
        int start = 0;
        for (int i = 1; i <= n; i++) {
            if (i == n || a[i] != a[start]) {
                addRun(runs, lengths, start, i - 1);
                start = i;
            }
        }

        int[] ans = new int[k];

        for (int q = 0; q < k; q++) {
            int idx = queryIndices[q];
            char newChar = queryCharacters.charAt(q);

            if (a[idx] == newChar) {
                // Nothing changes
                ans[q] = lengths.lastKey();
                continue;
            }

            /*
             * Find the run containing idx.
             */
            Map.Entry<Integer, Integer> entry = runs.floorEntry(idx);
            int l = entry.getKey();
            int r = entry.getValue();
            char oldChar = a[idx];

            removeRun(runs, lengths, l, r);

            /*
             * Split the old run around idx.
             */
            if (l < idx) {
                addRun(runs, lengths, l, idx - 1);
            }
            if (idx < r) {
                addRun(runs, lengths, idx + 1, r);
            }

            a[idx] = newChar;

            /*
             * Create the new one-character run, then merge it
             * with equal-character neighbors.
             */
            int newL = idx;
            int newR = idx;

            Map.Entry<Integer, Integer> left = runs.lowerEntry(idx);
            if (left != null) {
                int ll = left.getKey();
                int lr = left.getValue();

                if (a[lr] == newChar) {
                    removeRun(runs, lengths, ll, lr);
                    newL = ll;
                }
            }

            Map.Entry<Integer, Integer> right = runs.higherEntry(idx);
            if (right != null) {
                int rl = right.getKey();
                int rr = right.getValue();

                if (a[rl] == newChar) {
                    removeRun(runs, lengths, rl, rr);
                    newR = rr;
                }
            }

            addRun(runs, lengths, newL, newR);

            ans[q] = lengths.lastKey();
        }

        return ans;
    }

    private void addRun(
            TreeMap<Integer, Integer> runs,
            TreeMap<Integer, Integer> lengths,
            int l,
            int r) {

        runs.put(l, r);
        lengths.merge(r - l + 1, 1, Integer::sum);
    }

    private void removeRun(
            TreeMap<Integer, Integer> runs,
            TreeMap<Integer, Integer> lengths,
            int l,
            int r) {

        runs.remove(l);

        int len = r - l + 1;
        int count = lengths.get(len);

        if (count == 1) {
            lengths.remove(len);
        } else {
            lengths.put(len, count - 1);
        }
    }
}
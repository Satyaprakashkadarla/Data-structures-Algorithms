class Solution {
    public int countSubstring(String s) {
        int n = s.length();
        int[] pref = new int[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + (s.charAt(i) == '1' ? 1 : -1);
        }
        
        // Coordinate compression
        int[] sorted = pref.clone();
        Arrays.sort(sorted);
        
        FenwickTree bit = new FenwickTree(n + 1);
        long count = 0;
        
        for (int i = 0; i <= n; i++) {
            int idx = Arrays.binarySearch(sorted, pref[i]) + 1;
            count += bit.query(idx - 1);
            bit.update(idx, 1);
        }
        return (int) count;
    }
    
    class FenwickTree {
        int[] tree;
        int n;
        FenwickTree(int n) {
            this.n = n;
            tree = new int[n + 1];
        }
        void update(int i, int val) {
            while (i <= n) {
                tree[i] += val;
                i += i & -i;
            }
        }
        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }
}
class Solution {
    public int kBitFlips(int[] arr, int k) {
        // code here
    Queue<Integer> q = new LinkedList<>();
    int res = 0;
    int n = arr.length;

    for (int i = 0; i < n; i++) {
        while (!q.isEmpty() && i > q.peek() + k - 1) {
            q.poll();
        }
        
        if ((arr[i] + q.size()) % 2 == 0) {
            if (i + k > n) return -1; 
            res++;
            q.add(i); 
        }
    }
    return res;
    }
}
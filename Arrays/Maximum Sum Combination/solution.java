class Solution {
    public void reverse(int[] arr) {
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }

    public ArrayList<Integer> topKSumPairs(int[] a, int[] b, int k) {
        int n = a.length;
        Arrays.sort(a);
        Arrays.sort(b);
        reverse(a);
        reverse(b);

        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y) -> y[0] - x[0]);
        HashSet<String> vis = new HashSet<>();

        pq.offer(new int[] {a[0] + b[0], 0, 0});
        vis.add("0#0");

        ArrayList<Integer> res = new ArrayList<>();

        while (res.size() < k && !pq.isEmpty()) {
            int[] top = pq.poll();
            int sum = top[0], i = top[1], j = top[2];

            res.add(sum);

            if (i + 1 < n && !vis.contains((i + 1) + "#" + j)) {
                pq.offer(new int[] {a[i + 1] + b[j], i + 1, j});
                vis.add((i + 1) + "#" + j);
            }

            if (j + 1 < n && !vis.contains(i + "#" + (j + 1))) {
                pq.offer(new int[] {a[i] + b[j + 1], i, j + 1});
                vis.add(i + "#" + (j + 1));
            }
        }

        return res;
    }
}
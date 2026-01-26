class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {

        Arrays.sort(arr);
        int len = arr.length;
        int minDiff = 1_000_000;
        List<List<Integer>> rez = new ArrayList<>(len);

        for (int i = 1; i < len; i++) {
            int prev = arr[i - 1];
            int curr = arr[i];
            int diff = curr - prev;

            if (minDiff > diff) {
                minDiff = diff;
                rez.clear();
                rez.add(List.of(prev, curr));
            } else if (diff == minDiff){
                rez.add(List.of(prev, curr));
            }
        }

        return rez;
    }
}
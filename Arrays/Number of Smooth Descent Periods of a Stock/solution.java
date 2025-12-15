class Solution {
    public long getDescentPeriods(int[] prices) {
        long res = 0;
        long length = 1;
        long prev = Long.MAX_VALUE;
        for (int i : prices){
            if (prev - i == 1){
                length++;
            } else {
                res += length * (length + 1) / 2;
                length = 1;
            }
            prev = i;
        }
        res += length * (length + 1) / 2;
        return res - 1;
    }
}
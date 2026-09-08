class Solution {
    public int countCommas(int n) {
        int total = 0;
        int power = 1000;

        while (power <= n) {
            total += n - power + 1;
            power *= 1000;
        }

        return total;
    }
}

class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];
        for (int stone : stones) {
            count[stone % 3]++;
        }
        
        // If count[0] is even, Alice wins if both remainder 1 and remainder 2 stones exist
        if (count[0] % 2 == 0) {
            return Math.min(count[1], count[2]) > 0;
        }
        
        // If count[0] is odd, Alice wins if the count difference is greater than 2
        return Math.abs(count[1] - count[2]) > 2;
    }
}
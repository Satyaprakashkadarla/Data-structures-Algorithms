class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        // Find the smallest value you can get by turning off one of the 'rightmost' set bits of x.
        
        /*
        Why?
        Because, 
        Take a number say 79 => 1001111
        Remove this bit    x => 100(1)111
        Then x becomes     x => 1000111
        Then (x + 1) becomes => 1001000

        The OR becomes 1001111 - If we remove the maximum set bit in the trailing set bits of the number
        The +1 converts all the left ones to 0

        What if there are no trailing set bits of the number?
        Then there are no possible answers - (even number)
        */
        
        int n = nums.size();
        int result[] = new int[n];
        for(int i = 0; i < n; i++){
            int num = nums.get(i);

            int x = -1; // Initialise with -1, works when nothing is found
            int d = 1;

            // Check for the leftmost set bit in trailing ones
            while((num & d) != 0){
                x = num - d;
                d <<= 1;
            }

            result[i] = x;
        }
        return result;
    }
}
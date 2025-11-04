class Solution {
    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] freqMap = new int[50];
        int[] ans = new int[n - k + 1];
        int tempMax = 0;
        int max = 0;
        boolean dup = false;

        for (int i = 0; i < n - k + 1; i++)
        {
            freqMap = new int[50]; // Frequency Map
            max = 0;

            // Map each frequency of elements and store the maximum element
            for (int j = i; j < i + k; j++)
            {
                freqMap[nums[j] - 1]++; 
                if (nums[j] > max) max = nums[j];
            }

            // Loop 'x' times
            for (int z = x; z > 0; z--) 
            {
                tempMax = max - 1;
                dup = false;

                // Check for duplicates (Greater than 1 frequency)
                for (int f : freqMap) 
                {
                    if (f > 1)
                    {
                        dup = true;
                        break;
                    }
                }

                // Iterate through frequency map starting from the end for easier access to highest indeces
                for (int j = freqMap.length - 1; j >= 0; j--) 
                {
                    if (freqMap[j] == 0) continue; // Skip all 0 frequency

                    if (dup)
                    {
                        // If flagged duplicate, get the highest frequency index
                        if (freqMap[j] > freqMap[tempMax]) tempMax = j;
                    }
                    else 
                    {
                        // If not flagged duplicate, get the highest index with frequency
                        tempMax = j;
                        break;
                    }
                }
                
                // Add the total to the answers array
                // (element * index + 1)
                ans[i] += (freqMap[tempMax] * (tempMax + 1));
                freqMap[tempMax] = 0; // Remove used frequency
            }
        }

        return ans;
    }
}
class Solution {
    static int findPlatform(int arr[], int dep[]) {
        int n = arr.length;
        Arrays.sort(arr);
        Arrays.sort(dep);
        int platformsNeeded = 0, maxPlatforms = 0;
        int i = 0, j = 0;
        while (i < n && j < n) {
            if (arr[i] <= dep[j]) {
                platformsNeeded++; 
                i++;
            } else { 
                platformsNeeded--;
                j++;
            }
            maxPlatforms = Math.max(maxPlatforms, platformsNeeded);
        }
        return maxPlatforms;
    }
}
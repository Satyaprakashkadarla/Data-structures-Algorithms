class Solution {

    public ArrayList<Integer> longestSubarray(int[] arr, int x) {
        ArrayList<Integer> result = new ArrayList<>();
        int maxLength = 0;
        int startIndex = 0;
        int n = arr.length;
        int i =0 , j = 0;
        while ( j < n) {
            int maxVal = arr[i];
            int minVal = arr[i];
            for(int k = i ; k <= j ; k++) {
                maxVal = Math.max(maxVal , arr[k]);
                minVal = Math.min(minVal , arr[k]);
            }
            if(maxVal - minVal <= x) {
                if(j - i + 1 > maxLength) {
                    maxLength = j - i + 1;
                    startIndex = i;
                }
                j++;
            } else {
                i++;
            }
        }
        for(int k=startIndex;k<startIndex+maxLength;k++) {
            result.add(arr[k]);
        }
        return result;
    }
}
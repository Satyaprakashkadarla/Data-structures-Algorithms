class Solution {
    public int visibleBuildings(int arr[]) {
        if (arr == null || arr.length == 0) return 0;
        
        int count = 1;          // First building always sees the sun
        int maxHeight = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= maxHeight) {  // ✅ >= instead of >
                count++;
                maxHeight = arr[i];
            }
        }
        
        return count;
    }
}
import java.util.HashMap;

class Solution {
    public int maxDistance(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], i);
            } else {
                ans = Math.max(ans, i - map.get(arr[i]));
            }
        }
        
        return ans;
    }
}

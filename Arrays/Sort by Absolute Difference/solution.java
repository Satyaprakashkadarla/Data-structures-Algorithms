import java.util.Arrays;

class Solution {
    public void rearrange(int[] arr, int x) {
        Integer[] arrObj = new Integer[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            arrObj[i] = arr[i];
        }

        Arrays.sort(arrObj, (a, b) -> {
            int diffA = Math.abs(a - x);
            int diffB = Math.abs(b - x);
            return Integer.compare(diffA, diffB);
        });
        
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrObj[i];
        }
    }
}

import java.util.Arrays;
import java.util.Collections;
class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        reverse(citations);
        
        int h = 0;
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= i + 1) {
                h = i + 1;
            } else {
                break;
            }
        }
        return h;
    }
    private void reverse(int[] array) {
        int left = 0;
        int right = array.length - 1;
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
}
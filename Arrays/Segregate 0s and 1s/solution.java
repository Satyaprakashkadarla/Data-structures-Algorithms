class Solution {
    void segregate0and1(int[] arr) {
        int left = 0;
        for (int right = 0; right < arr.length; right++) {
            if (arr[right] == 0) {
                // swap
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
            }
        }
    }
}
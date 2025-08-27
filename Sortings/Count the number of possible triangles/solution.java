import java.util.Arrays;
class Solution {
    public int countTriangles(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int ans = 0;

        for (int i = n - 1; i > 1; i--) {
            int left = 0, right = i - 1;

            while (left < right) {
                if (arr[left] + arr[right] > arr[i]) {
                    ans += (right - left);
                    right--;
                } else {
                    left++;
                }
            }
        }

        return ans;
    }
}

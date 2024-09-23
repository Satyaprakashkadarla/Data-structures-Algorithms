import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] findTwoElement(int[] arr) {
        int missing = 0, repeated = 0;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            int k = (arr[i] - 1) % (n + 1);
            arr[k] += (n + 1);
        }

        for (int i = 0; i < n; i++) {
            if (arr[i] / (n + 1) < 1) {
                missing = i + 1;
            }
            if (arr[i] / (n + 1) == 2) {
                repeated = i + 1;
            }
        }

        return new int[]{repeated, missing};
    }
}

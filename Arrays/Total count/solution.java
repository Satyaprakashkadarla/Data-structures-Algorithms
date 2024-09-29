import java.util.List;

class Solution {
    public int totalCount(int k, List<Integer> arr) {
        int ans = 0;
        for (int it : arr) {
            int quotient = it / k;
            int remainder = it % k;
            ans += quotient + (remainder != 0 ? 1 : 0);
        }
        return ans;
    }
}

import java.util.List;

public class Solution {
    public int startStation(List<Integer> gas, List<Integer> cost) {
        int n = gas.size();
        int start = 0, curr = 0;
        for (int i = 0; i < n; i++) {
            curr += gas.get(i) - cost.get(i);
            if (curr < 0) {
                start = i + 1;
                curr = 0;
            }
        }
        curr = 0;
        for (int i = 0; i < n; i++) {
            int idx = (i + start) % n;
            curr += gas.get(idx) - cost.get(idx);
            if (curr < 0) return -1;
        }
        return start;
    }
}

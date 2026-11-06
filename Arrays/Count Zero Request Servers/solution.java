
import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {

        // Sort logs by time
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));

        // Store queries with their original indices
        int[][] sortedQueries = new int[queries.length][2];

        for (int i = 0; i < queries.length; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }

        Arrays.sort(sortedQueries, (a, b) -> 
            Integer.compare(a[0], b[0]));

        int[] ans = new int[queries.length];

        // Server ID -> number of requests in current window
        Map<Integer, Integer> map = new HashMap<>();

        int left = 0, right = 0;
        int m = logs.length;

        for (int[] query : sortedQueries) {

            int t = query[0];
            int idx = query[1];

            // Add logs with time <= t
            while (right < m && logs[right][1] <= t) {
                int id = logs[right][0];
                map.put(id, map.getOrDefault(id, 0) + 1);
                right++;
            }

            // Remove logs with time < t - x
            while (left < right && logs[left][1] < t - x) {
                int id = logs[left][0];

                map.put(id, map.get(id) - 1);

                if (map.get(id) == 0) {
                    map.remove(id);
                }

                left++;
            }

            // Servers without requests
            ans[idx] = n - map.size();
        }

        return ans;
    }
}

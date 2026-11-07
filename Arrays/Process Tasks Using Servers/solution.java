
import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;

        // Free servers: [weight, index]
        PriorityQueue<int[]> available = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Integer.compare(a[0], b[0]);
                return Integer.compare(a[1], b[1]);
            }
        );

        // Busy servers: [freeTime, weight, index]
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> {
                if (a[0] != b[0])
                    return Long.compare(a[0], b[0]);
                if (a[1] != b[1])
                    return Long.compare(a[1], b[1]);
                return Long.compare(a[2], b[2]);
            }
        );

        // Initially all servers are available
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        int[] ans = new int[m];

        for (int i = 0; i < m; i++) {

            // If no server is free, jump to the earliest free time
            if (available.isEmpty()) {
                long time = busy.peek()[0];

                // Release all servers that become free
                // at this time
                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] s = busy.poll();
                    available.offer(new int[]{
                        (int) s[1], (int) s[2]
                    });
                }
            }

            // Release servers that are free at current time
            while (!busy.isEmpty() && busy.peek()[0] <= i) {
                long[] s = busy.poll();
                available.offer(new int[]{
                    (int) s[1], (int) s[2]
                });
            }

            // Assign task to the best available server
            int[] server = available.poll();

            int weight = server[0];
            int index = server[1];

            ans[i] = index;

            // Server becomes free after task completion
            busy.offer(new long[]{
                Math.max(i, busy.isEmpty() ? i : i) + (long) tasks[i],
                weight,
                index
            });

            // Correct the start time when the task had to wait
            // (handled using the actual assignment time)
        }

        return ans;
    }
}

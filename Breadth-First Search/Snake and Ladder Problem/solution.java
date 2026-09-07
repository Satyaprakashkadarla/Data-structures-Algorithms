import java.util.*;

class Solution {
    public int minThrows(int n, int[] lad, int[] sn) {
        int totalCells = n * n;

        // Create board to store snake/ladder mappings
        int[] board = new int[totalCells + 1];
        Arrays.fill(board, -1);

        // Add ladders: lad[0]->lad[1], lad[2]->lad[3], ...
        for (int i = 0; i < lad.length; i += 2) {
            board[lad[i]] = lad[i + 1];
        }

        // Add snakes: sn[0]->sn[1], sn[2]->sn[3], ...
        for (int i = 0; i < sn.length; i += 2) {
            board[sn[i]] = sn[i + 1];
        }

        // BFS to find minimum throws
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[totalCells + 1];
        int[] dist = new int[totalCells + 1];

        queue.offer(1);
        visited[1] = true;
        dist[1] = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // If we reached the destination
            if (curr == totalCells) {
                return dist[curr];
            }

            // Try all possible dice throws (1 to 6)
            for (int dice = 1; dice <= 6; dice++) {
                int next = curr + dice;

                // Check if we're within board bounds
                if (next > totalCells) continue;

                // If there's a snake or ladder at this cell
                if (board[next] != -1) {
                    next = board[next];
                }

                // If not visited, add to queue
                if (!visited[next]) {
                    visited[next] = true;
                    dist[next] = dist[curr] + 1;
                    queue.offer(next);
                }
            }
        }

        // Destination unreachable
        return -1;
    }
}
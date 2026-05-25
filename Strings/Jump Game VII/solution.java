class Solution {
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] reachable = new boolean[n];
        reachable[0] = true;

        int farthest = 0;

        for (int i = 0; i < n; i++) {
            if (!reachable[i]) continue;

            int start = Math.max(i + minJump, farthest + 1);
            int end = Math.min(i + maxJump, n - 1);

            for (int j = start; j <= end; j++) {
                if (s.charAt(j) == '0') {
                    reachable[j] = true;
                }
            }

            farthest = end;
        }

        return reachable[n - 1];
    }
}
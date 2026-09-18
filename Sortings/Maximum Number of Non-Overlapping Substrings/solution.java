class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // Find first and last occurrence of every character.
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            first[c] = Math.min(first[c], i);
            last[c] = i;
        }

        // Each valid interval is [start, end].
        List<int[]> intervals = new ArrayList<>();

        for (int c = 0; c < 26; c++) {
            if (last[c] == -1) continue;

            int start = first[c];
            int end = last[c];
            boolean valid = true;

            for (int i = start; i <= end; i++) {
                int x = s.charAt(i) - 'a';

                // This character occurs before our interval.
                if (first[x] < start) {
                    valid = false;
                    break;
                }

                // Must include all occurrences of this character.
                end = Math.max(end, last[x]);
            }

            if (valid) {
                intervals.add(new int[]{start, end});
            }
        }

        // Choose intervals greedily by earliest ending position.
        intervals.sort((a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }

            // For equal end, shorter interval first.
            return Integer.compare(
                a[1] - a[0],
                b[1] - b[0]
            );
        });

        List<String> ans = new ArrayList<>();
        int prevEnd = -1;

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (start > prevEnd) {
                ans.add(s.substring(start, end + 1));
                prevEnd = end;
            }
        }

        return ans;
    }
}

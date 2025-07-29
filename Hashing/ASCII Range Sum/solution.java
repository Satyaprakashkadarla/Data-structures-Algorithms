class Solution {
    public ArrayList<Integer> asciirange(String s) {
        int[] first = new int[26], last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);
        ArrayList<Integer> result = new ArrayList<>();
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            if (first[idx] == -1) first[idx] = i;
            last[idx] = i;
        }
        
        for (int c = 0; c < 26; c++) {
            if (first[c] != -1 && first[c] < last[c]) {
                int sum = 0;
                for (int i = first[c] + 1; i < last[c]; i++)
                    sum += s.charAt(i);
                if (sum != 0) result.add(sum);
            }
        }
        
        return result;
    }
}
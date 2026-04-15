class Solution {
    public int closestTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int direct = Math.abs(i - startIndex);
                int circular = n - direct;
                int distance = Math.min(direct, circular);
                
                minDistance = Math.min(minDistance, distance);
            }
        }
        
        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}
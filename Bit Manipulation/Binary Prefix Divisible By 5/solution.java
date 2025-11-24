class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> res = new ArrayList<>();
        int r = 0;

        for (int b : nums) {
            r = (r * 2 + b) % 5;
            res.add(r == 0);
        }

        return res;
    }
}
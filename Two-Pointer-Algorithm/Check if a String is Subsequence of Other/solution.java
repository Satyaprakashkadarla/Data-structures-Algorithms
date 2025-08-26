class Solution {
    public boolean isSubSeq(String s1, String s2) {
        int i1 = 0, i2 = 0;
        int n1 = s1.length(), n2 = s2.length();

        while (i1 < n1 && i2 < n2) {
            if (s1.charAt(i1) == s2.charAt(i2)) {
                i1++;
                i2++;
            } else {
                i2++;
            }
        }

        return i1 == n1;
    }
}

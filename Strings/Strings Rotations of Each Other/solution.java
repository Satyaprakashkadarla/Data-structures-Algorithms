class Solution {
    // Function to check if two strings are rotations of each other
    public static boolean areRotations(String s1, String s2) {
        // Check if the strings have the same length, otherwise they can't be rotations
        if (s1.length() != s2.length()) {
            return false;
        }
        
        // Concatenate s1 with itself and check if s2 is a substring of s1+s1
        s1 = s1 + s1;
        return s1.contains(s2);
    }
}

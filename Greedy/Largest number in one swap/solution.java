class Solution {
    public String largestSwap(String s) {
        int n = s.length();
        if (n <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int[] maxDigit = new int[n];
        int[] rightIndex = new int[n];
        maxDigit[n-1] = chars[n-1];
        rightIndex[n-1] = n-1;
        for (int i = n-2; i >= 0; i--) {
            if (chars[i] > maxDigit[i+1]) {
                maxDigit[i] = chars[i];
                rightIndex[i] = i;
            } else {
                maxDigit[i] = maxDigit[i+1];
                rightIndex[i] = rightIndex[i+1];
            }
        }
        for (int i = 0; i < n-1; i++) {
            if (maxDigit[i+1] > chars[i]) {
                int j = rightIndex[i+1];
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                return new String(chars);
            }
        }
        
        return s;
    }
}
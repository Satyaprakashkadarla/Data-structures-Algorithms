import java.math.BigInteger;

class Solution {
    public boolean isAdditiveSequence(String n) {
        int len = n.length();
        for (int i = 1; i <= len / 2; i++) {
            for (int j = 1; j <= (len - i) / 2; j++) {
                if (check(n, i, j)) return true;
            }
        }
        return false;
    }
    
    private boolean check(String s, int i, int j) {
        String num1Str = s.substring(0, i);
        String num2Str = s.substring(i, i + j);
        
        if (num1Str.length() > 1 && num1Str.charAt(0) == '0') return false;
        if (num2Str.length() > 1 && num2Str.charAt(0) == '0') return false;
        
        BigInteger num1 = new BigInteger(num1Str);
        BigInteger num2 = new BigInteger(num2Str);
        
        int idx = i + j;
        while (idx < s.length()) {
            BigInteger sum = num1.add(num2);
            String sumStr = sum.toString();
            if (!s.startsWith(sumStr, idx)) return false;
            idx += sumStr.length();
            num1 = num2;
            num2 = sum;
        }
        return true;
    }
}
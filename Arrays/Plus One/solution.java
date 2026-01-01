class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        
        for (int i = digits.length - 1; i >= 0; i--) {
            int total = digits[i] + carry;
            digits[i] = total % 10;
            carry = total / 10;
        }
        
        if (carry == 1) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            System.arraycopy(digits, 0, result, 1, digits.length);
            return result;
        }
        
        return digits;
    }
}
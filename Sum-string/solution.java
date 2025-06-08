class Solution {
    // Adds two numeric strings and returns the sum as string
    String addStrings(String num1, String num2) {
        // Ensure num1 is the longer string to simplify remaining digit addition
        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }

        int           len1  = num1.length();
        int           len2  = num2.length();
        StringBuilder sum   = new StringBuilder();
        int           carry = 0;

        // Add digits from right to left (least significant to most)
        for (int i = 0; i < len2; i++) {
            int d1    = num1.charAt(len1 - 1 - i) - '0'; // digit from num1
            int d2    = num2.charAt(len2 - 1 - i) - '0'; // digit from num2
            int digit = (d1 + d2 + carry) % 10;          // resulting digit
            carry = (d1 + d2 + carry) / 10;              // carry for next addition
            sum.insert(0, (char)(digit + '0'));          // prepend digit to sum
        }

        // Add remaining digits from num1 (if any)
        for (int i = len2; i < len1; i++) {
            int d     = num1.charAt(len1 - 1 - i) - '0';
            int digit = (d + carry) % 10;
            carry = (d + carry) / 10;
            sum.insert(0, (char)(digit + '0'));
        }

        // If carry is left, add it to front
        if (carry != 0) {
            sum.insert(0, (char)(carry + '0'));
        }

        return sum.toString();
    }

    // Recursively checks if the string from index `start` is a valid sum-sequence
    boolean checkSequence(String s, int start, int len1, int len2) {
        String part1 = s.substring(start, start + len1);               // First number
        String part2 = s.substring(start + len1, start + len1 + len2); // Second number
        String part3 = addStrings(part1, part2);                       // Their sum

        int len3 = part3.length();

        // If the sum segment exceeds remaining length, return false
        if (start + len1 + len2 + len3 > s.length()) {
            return false;
        }

        // If the calculated sum matches the next substring in s
        if (part3.equals(s.substring(start + len1 + len2, start + len1 + len2 + len3))) {
            // If entire string is matched successfully
            if (start + len1 + len2 + len3 == s.length()) {
                return true;
            }

            // Recurse with new start, shifting by part1 length
            return checkSequence(s, start + len1, len2, len3);
        }

        // If sum doesn't match, return false
        return false;
    }
    
    // Main function to check if string is a valid sum-string
    public boolean isSumString(String s) {
        int n = s.length();

        // Try all possible combinations of lengths for first and second parts
        for (int len1 = 1; len1 < n; len1++) {
            for (int len2 = 1; len1 + len2 < n; len2++) {
                if (checkSequence(s, 0, len1, len2)) {
                    return true;
                }
            }
        }

        return false;
    }
}
Pseudocode:
FUNCTION totalWaviness(num1, num2):
    ans ← 0
    FOR x FROM num1 TO num2:
        ans ← ans + waviness(x)
    RETURN ans
FUNCTION waviness(x):
    digits ← empty list
    WHILE x > 0:
        append (x mod 10) to digits
        x ← x div 10
    m ← length(digits)
    IF m < 3:
        RETURN 0
    count ← 0
    FOR i FROM 1 TO m - 2:
        IF digits[i] > digits[i - 1]
           AND digits[i] > digits[i + 1]:
               count ← count + 1
        ELSE IF digits[i] < digits[i - 1]
                AND digits[i] < digits[i + 1]:
               count ← count + 1
    RETURN count

----------------------------------------------------------------

How it works

For example, 4848:

Digits: 4 8 4 8

Position 2: 8 > 4 and 8 > 4  → Peak
Position 3: 4 < 8 and 4 < 8  → Valley

Waviness = 2
----------------------------------------------------------------

Time Complexity
Let:
N = num2 - num1 + 1
D = number of digits (at most 6 since num2 ≤ 10^5)
-----------------------------------------------------------------
For each number we inspect all digits:
Time: O(N × D)
---------------------------------------------------------------
Since D ≤ 6:

Time: O(num2 - num1 + 1)
Space: O(D) = O(1)

-------------------------------------------------------
Code:
class Solution {
    public int totalWaviness(int num1, int num2) {
        int ans = 0;
        for (int x = num1; x <= num2; x++) {
            ans += f(x);
        }
        return ans;
    }

    private int f(int x) {
        int[] nums = new int[20];
        int m = 0;
        while (x > 0) {
            nums[m++] = x % 10;
            x /= 10;
        }
        if (m < 3) {
            return 0;
        }
        int s = 0;
        for (int i = 1; i < m - 1;s i++) {
            if ((nums[i] > nums[i - 1] && nums[i] > nums[i + 1])
                || (nums[i] < nums[i - 1] && nums[i] < nums[i + 1])) {
                s++;
            }
        }
        return s;
    }
}
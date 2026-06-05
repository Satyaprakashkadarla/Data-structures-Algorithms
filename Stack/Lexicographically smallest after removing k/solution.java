Pseudocode:
lexicographicallySmallest(s, k)

n = length of s

// Correct k
if n is power of 2
    k = k / 2
else
    k = k * 2

// Cannot remove exactly k characters
if k >= n
    return "-1"

stack = empty
removals = k

for each character ch in s

    while stack not empty
          and removals > 0
          and stack.top > ch

          pop stack
          removals--

    push ch into stack

// Remove remaining characters from end
while removals > 0
    pop stack
    removals--

answer = characters present in stack

if answer is empty
    return "-1"

return answer

-------------------------------------------------------------------------------

Time Complexity
Each character is pushed into the stack once.
Each character is popped at most once. O(n)
	​

where n = s.length().

Space Complexity
Stack stores at most n characters. O(n)
-----------------------------------------------------------------------------------

Java Code:
import java.util.*;

class Solution {

    private boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public String lexicographicallySmallest(String s, int k) {

        int n = s.length();

        // Correct k
        if (isPowerOfTwo(n)) {
            k /= 2;
        } else {
            k *= 2;
        }

        // Not possible to remove exactly k characters
        // or resulting string becomes empty
        if (k >= n) {
            return "-1";
        }

        Deque<Character> stack = new ArrayDeque<>();
        int removals = k;

        for (char ch : s.toCharArray()) {

            while (!stack.isEmpty()
                    && removals > 0
                    && stack.peekLast() > ch) {

                stack.pollLast();
                removals--;
            }

            stack.offerLast(ch);
        }

        // Remove remaining characters from the end
        while (removals > 0) {
            stack.pollLast();
            removals--;
        }

        StringBuilder ans = new StringBuilder();

        while (!stack.isEmpty()) {
            ans.append(stack.pollFirst());
        }

        return ans.length() == 0 ? "-1" : ans.toString();
    }
}
	​

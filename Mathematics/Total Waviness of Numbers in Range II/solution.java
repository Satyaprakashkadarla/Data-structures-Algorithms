Pseudocode
function solve(N):
    digits = digits of N

    dfs(pos, tight, started, lenState, a, b):

        if pos == digits.length:
            return (1, 0)

        ansCount = 0
        ansWave = 0

        limit = tight ? digits[pos] : 9

        for d in [0..limit]:

            ntight = tight && (d == limitDigit)

            if !started and d == 0:
                child = dfs(pos+1, ntight, false, 0, 0, 0)

            else if !started:
                child = dfs(pos+1, ntight, true, 1, 0, d)

            else if lenState == 1:
                child = dfs(pos+1, ntight, true, 2, b, d)

            else:
                add =
                    (b > a && b > d) ||
                    (b < a && b < d)

                child = dfs(pos+1, ntight, true, 2, b, d)

                ansWave += child.count * add

            ansCount += child.count
            ansWave += child.wave

        return (ansCount, ansWave)

    return dfs(...).wave

----------------------------------------------------------------

Let:

D = number of digits in N
Here N ≤ 10^15, so D ≤ 16.
Number of DP states

The memoized state is:

(pos, tight, started, lenState, a, b)

Possible values:

pos → D (≤ 16)
tight → 2
started → 2
lenState → 3
a → 11 values (0–9 plus sentinel 10)
b → 11 values

Total states:

O(D×2×2×3×11×11)
=O(D×1452)

For D=16:

16×1452=23232

states.

Transition cost

Each state tries digits:

for (d = 0; d <= 9; d++)

at most 10 transitions.

Therefore:

Time=O(states×10)
=O(D×2×2×3×11×11×10)

Since all dimensions except D are constants:

O(D)
	​


More concretely:

23232×10≈2.3×10
5

operations.

Space complexity

Memo table:

O(D×2×2×3×11×11)
=O(D)

For D=16:

23232

stored states.

Each state stores a Node(count, wave).

Thus:

Space=O(D)
	​


(or about 23k DP states in practice).

Final Complexity
Time: 
O(D)
	​

 (≈ 2.3×10
5
 operations for D=16)
Space: 
O(D)
	​

 (≈ 23k memoized states)

Since D≤16, the solution is effectively constant time and constant space for the given constraints.

--------------------------------------------------------------------------------------
Java Code
class Solution {

    private char[] digits;
    private Node[][][][][][] memo;

    static class Node {
        long count;
        long wave;

        Node(long c, long w) {
            count = c;
            wave = w;
        }
    }

    public long totalWaviness(long num1, long num2) {
        return calc(num2) - calc(num1 - 1);
    }

    private long calc(long n) {
        if (n <= 0) return 0;

        digits = String.valueOf(n).toCharArray();

        memo = new Node[digits.length][2][2][3][11][11];

        return dfs(0, 1, 0, 0, 10, 10).wave;
    }

    private Node dfs(int pos,
                     int tight,
                     int started,
                     int lenState,
                     int a,
                     int b) {

        if (pos == digits.length) {
            return new Node(1, 0);
        }

        if (tight == 0 && memo[pos][0][started][lenState][a][b] != null) {
            return memo[pos][0][started][lenState][a][b];
        }

        int limit = (tight == 1) ? digits[pos] - '0' : 9;

        long totalCount = 0;
        long totalWave = 0;

        for (int d = 0; d <= limit; d++) {

            int ntight = (tight == 1 && d == limit) ? 1 : 0;

            Node child;

            if (started == 0 && d == 0) {

                child = dfs(pos + 1, ntight, 0, 0, 10, 10);

                totalCount += child.count;
                totalWave += child.wave;

            } else if (started == 0) {

                child = dfs(pos + 1, ntight, 1, 1, 10, d);

                totalCount += child.count;
                totalWave += child.wave;

            } else if (lenState == 1) {

                child = dfs(pos + 1, ntight, 1, 2, b, d);

                totalCount += child.count;
                totalWave += child.wave;

            } else {

                int add =
                        ((b > a && b > d) ||
                         (b < a && b < d)) ? 1 : 0;

                child = dfs(pos + 1, ntight, 1, 2, b, d);

                totalCount += child.count;
                totalWave += child.wave + child.count * add;
            }
        }

        Node res = new Node(totalCount, totalWave);

        if (tight == 0) {
            memo[pos][0][started][lenState][a][b] = res;
        }

        return res;
    }
}
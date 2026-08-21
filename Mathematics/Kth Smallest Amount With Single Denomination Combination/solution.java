import java.util.*;

class Solution {
    public long findKthSmallest(int[] coins, int k) {
        // Remove coins that are multiples of other coins (optimization)
        List<Integer> filtered = new ArrayList<>();
        Arrays.sort(coins);
        
        for (int i = 0; i < coins.length; i++) {
            boolean isMultiple = false;
            for (int j = 0; j < i; j++) {
                if (coins[i] % coins[j] == 0) {
                    isMultiple = true;
                    break;
                }
            }
            if (!isMultiple) {
                filtered.add(coins[i]);
            }
        }
        
        // Convert to array for easier use
        int[] uniqueCoins = filtered.stream().mapToInt(i -> i).toArray();
        int n = uniqueCoins.length;
        
        // Binary search for the kth smallest amount
        long left = 1, right = (long) 1e18;
        
        while (left < right) {
            long mid = left + (right - left) / 2;
            long count = countValidNumbers(mid, uniqueCoins, n);
            
            if (count >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        return left;
    }
    
    private long countValidNumbers(long x, int[] coins, int n) {
        // Count numbers ≤ x that are divisible by at least one coin
        // Using inclusion-exclusion principle
        
        long result = 0;
        
        // Iterate through all subsets of coins
        for (int mask = 1; mask < (1 << n); mask++) {
            long lcm = 1;
            int bits = 0;
            
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;
                    lcm = lcm(lcm, coins[i]);
                    if (lcm > x) break;
                }
            }
            
            if (lcm <= x) {
                if (bits % 2 == 1) {
                    result += x / lcm;
                } else {
                    result -= x / lcm;
                }
            }
        }
        
        return result;
    }
    
    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
    
    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
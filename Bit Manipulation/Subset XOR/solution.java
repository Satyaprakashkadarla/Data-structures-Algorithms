import java.util.ArrayList;

class Solution {
    public static ArrayList<Integer> subsetXOR(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        
        if (n % 4 == 0) {
            // Take all numbers 1 to n
            for (int i = 1; i <= n; i++) {
                result.add(i);
            }
        } else if (n % 4 == 3) {
            // Take numbers 1 to n-1
            for (int i = 1; i < n; i++) {
                result.add(i);
            }
        } else if (n % 4 == 1) {
            // Take all except n-1
            for (int i = 1; i <= n; i++) {
                if (i != n - 1) {
                    result.add(i);
                }
            }
        } else { // n % 4 == 2
            // Take all except 1
            for (int i = 1; i <= n; i++) {
                if (i != 1) {
                    result.add(i);
                }
            }
        }
        
        return result;
    }
}
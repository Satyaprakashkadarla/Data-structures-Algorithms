import java.util.*;

class Solution {
    public void sortIt(int[] arr) {
        List<Integer> odds = new ArrayList<>();
        List<Integer> evens = new ArrayList<>();
        
        for (int num : arr) {
            if (num % 2 == 1) {
                odds.add(num);
            } else {
                evens.add(num);
            }
        }
        
        // Sort odds descending
        Collections.sort(odds, Collections.reverseOrder());
        // Sort evens ascending
        Collections.sort(evens);
        
        int idx = 0;
        for (int odd : odds) {
            arr[idx++] = odd;
        }
        for (int even : evens) {
            arr[idx++] = even;
        }
    }
}
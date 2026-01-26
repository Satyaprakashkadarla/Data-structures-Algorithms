import java.util.*;

class Solution {
    public static ArrayList<ArrayList<Integer>> permuteDist(int[] arr) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        backtrack(arr, 0, result);
        return result;
    }

    private static void backtrack(int[] arr, int start, ArrayList<ArrayList<Integer>> result) {
        // Base case: If we've reached the end of the array, add the current arrangement
        if (start == arr.length) {
            ArrayList<Integer> currentPerm = new ArrayList<>();
            for (int num : arr) {
                currentPerm.add(num);
            }
            result.add(currentPerm);
            return;
        }

        for (int i = start; i < arr.length; i++) {
            // 1. Swap the current element with the 'start' element
            swap(arr, start, i);
            
            // 2. Recurse for the next position
            backtrack(arr, start + 1, result);
            
            // 3. Backtrack: Swap them back to original state
            swap(arr, start, i);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
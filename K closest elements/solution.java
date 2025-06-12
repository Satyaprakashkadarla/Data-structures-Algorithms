class Solution {
    int[] printKClosest(int[] arr, int k, int x) {
        int n   = arr.length;
        int pos = -1;

        // Step 1: Binary Search to find last element < x
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] < x) {
                pos = mid; // update pos if arr[mid] is less than x
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Step 2: Two pointers: Pick k closest using distance comparison
        int left = pos, right = pos + 1;

        if (right < n && arr[right] == x) {
            right++;
        }

        ArrayList<Integer> resultList = new ArrayList<>();

        while (left >= 0 && right < n && resultList.size() < k) {
            int leftDiff  = Math.abs(arr[left] - x);
            int rightDiff = Math.abs(arr[right] - x);

            // Prefer the smaller difference; if tie, prefer the larger element
            if (leftDiff < rightDiff) {
                resultList.add(arr[left]);
                left--;
            } else {
                resultList.add(arr[right]);
                right++;
            }
        }

        // Fill remaining if any
        while (left >= 0 && resultList.size() < k) {
            resultList.add(arr[left]);
            left--;
        }

        while (right < n && resultList.size() < k) {
            resultList.add(arr[right]);
            right++;
        }

        // Convert ArrayList to array for return
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }

        return result;
    }
}
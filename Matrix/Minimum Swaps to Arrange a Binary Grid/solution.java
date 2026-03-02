	class Solution {

	    public int minSwaps(int[][] grid) {

	        int n = grid.length;

	        int[] trailingZeros = new int[n];

	        for (int i = 0; i < n; ++i) {

	            int cnt = 0;

	            for (int j = n - 1; j >= 0; --j) {

	                if (grid[i][j] == 0)

	                    cnt++;

	                else

	                    break;

	            }

	            trailingZeros[i] = cnt;

	        }

	        int swaps = 0;

	        for (int i = 0; i < n; ++i) {

	            int required = n - i - 1;

	            int j = i;

	            while (j < n && trailingZeros[j] < required)

	                j++;

	            if (j == n)

	                return -1;

	            while (j > i) {

	                int tmp = trailingZeros[j];

	                trailingZeros[j] = trailingZeros[j - 1];

	                trailingZeros[j - 1] = tmp;

	                swaps++;

	                j--;

	            }

	        }

	        return swaps;

	    }

	}
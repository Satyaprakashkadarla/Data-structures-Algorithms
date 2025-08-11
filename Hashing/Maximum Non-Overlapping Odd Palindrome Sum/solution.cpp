import java.util.*;

class Solution {
    
    private int[] manacherOdd(String s) {
        int n = s.length();
        int[] d1 = new int[n];
        int l = 0, r = -1;
        for (int i = 0; i < n; ++i) {
            int k = (i > r) ? 1 : Math.min(d1[l + r - i], r - i + 1);
            while (i - k >= 0 && i + k < n && s.charAt(i - k) == s.charAt(i + k)) {
                ++k;
            }
            d1[i] = k;
            if (i + k - 1 > r) {
                l = i - k + 1;
                r = i + k - 1;
            }
        }
        return d1;
    }
    
    private int[] computeEndLen(String s, int[] d1) {
        int n = s.length();
        int[] endLen = new int[n];
        int[] R = new int[n];
        for (int i = 0; i < n; ++i) {
            R[i] = i + d1[i] - 1;
        }
        
        Deque<Integer> q = new LinkedList<>();
        for (int p = 0; p < n; ++p) {
            q.add(p);
            while (!q.isEmpty() && R[q.peekFirst()] < p) {
                q.pollFirst();
            }
            if (!q.isEmpty()) {
                endLen[p] = 2 * (p - q.peekFirst()) + 1;
            } else {
                endLen[p] = 0;
            }
        }
        return endLen;
    }
    
    public int maxSum(String s) {
        int n = s.length();
        if (n < 2) return 0;
        
        int[] d1 = manacherOdd(s);
        int[] endLen = computeEndLen(s, d1);
        
        StringBuilder rs = new StringBuilder(s).reverse();
        int[] d1r = manacherOdd(rs.toString());
        int[] endLenR = computeEndLen(rs.toString(), d1r);
        
        int[] startLen = new int[n];
        for (int i = 0; i < n; ++i) {
            startLen[n - 1 - i] = endLenR[i];
        }
        
        int[] bestPref = new int[n];
        int[] bestSuf = new int[n];
        
        bestPref[0] = endLen[0];
        for (int i = 1; i < n; ++i) {
            bestPref[i] = Math.max(bestPref[i - 1], endLen[i]);
        }
        
        bestSuf[n - 1] = startLen[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            bestSuf[i] = Math.max(bestSuf[i + 1], startLen[i]);
        }
        
        int ans = 0;
        for (int i = 0; i + 1 < n; ++i) {
            ans = Math.max(ans, bestPref[i] + bestSuf[i + 1]);
        }
        
        return ans;
    }
}

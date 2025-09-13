
class Solution {
    public static int minCost(int n, int m, int[] x, int[] y) {
        Arrays.sort(x);
        Arrays.sort(y);
        
        int i=n-2, j=m-2;
        int xcount = 1, ycount = 1;
        int res = 0;
        
        while(i>=0 && j>=0){
            if(y[i] > x[j]){
                res += y[i] * xcount;
                ycount++;
                i--;
            }else {
                res += x[j] * ycount;
                xcount++;
                j--;
            }
        }
        
        while(i >= 0){
            res += y[i] * xcount;
            ycount++;
            i--;
        }
        
        while(j >= 0){
            res += x[j] * ycount;
            xcount++;
            j--;
        }
        
        return res;
    }
}

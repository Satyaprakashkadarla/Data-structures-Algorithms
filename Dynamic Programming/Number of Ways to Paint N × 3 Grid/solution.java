class Solution {
    public int numOfWays(int n) {
        if(n==1) return 12;
        int MOD=1_000_000_007;
        Long[][] mem=new Long[2][n];
        return (int)((((dfs(0,MOD,n-1,mem)+dfs(1,MOD,n-1,mem))%MOD)*6)%MOD);
    }
    private long dfs(int c,int MOD,int n,Long[][] mem){
        if(n==1) return c==0?5:4;
        if(mem[c][n]!=null) return mem[c][n];
        if(c==0) return mem[c][n]=(((dfs(0,MOD,n-1,mem)*3)%MOD)+((dfs(1,MOD,n-1,mem)*2)%MOD))%MOD;
        else return mem[c][n]=(((dfs(0,MOD,n-1,mem)*2)%MOD)+((dfs(1,MOD,n-1,mem)*2)%MOD))%MOD;
    }
}
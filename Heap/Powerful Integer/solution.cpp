class Solution {
  public:
    int powerfulInteger(vector<vector<int>>& intervals, int k) {
         int cnt=0,temp=0;
        int res =-1;
        int size=intervals.size();
        map<int,int> mp;
        for(int i=0;i<size;i++){
            mp[intervals[i][0]]++;
            mp[intervals[i][1]+1]--;
        }
        for(auto it : mp){
            cnt+=it.second;
            if(cnt>=k) res=it.first;
            else if(temp>=k) res=it.first-1;
            temp=cnt;
        }
        return res;
    }
};
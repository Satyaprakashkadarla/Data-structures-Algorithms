class Solution {
public:
    typedef long long int lli;
    long long putMarbles(vector<int>& weights, int k) {
        vector<lli>r;
        for(int i=0;i<weights.size();i++){
            if(i+1<weights.size())
            r.push_back((lli)weights[i]+weights[i+1]);
        }
      
        sort(r.begin(),r.end());
        lli sum=0;
        for(int i=0;i<k-1;i++){
            sum+=r[i];
        }
        sum+=weights[0]+weights[weights.size()-1];
        sort(r.begin(),r.end(),greater<lli>());
        lli sum2=0;
        for(int i=0;i<k-1;i++){
            sum2+=r[i];
        }
        sum2+=weights[0]+weights[weights.size()-1];
        return sum2-sum;
    }
};
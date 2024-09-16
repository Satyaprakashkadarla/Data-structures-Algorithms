class Solution {
  public:
    int maxLength(string str) {
        int ans = 0 ; 
        stack<pair<char,int>> st ;
        st.push({'$',-1}) ;
        for(int i = 0 ; i < str.size() ; i++ ){
            if(str[i]=='(')
             st.push({'(',i}) ;
            else {
                if(st.top().first=='('){
                    st.pop() ;}
                else {
                    st.push({'$',i}) ; }
                int  in = st.top().second ;
                ans = max( ans , i - in ) ;
            }
        }
        return ans ;
    }
};
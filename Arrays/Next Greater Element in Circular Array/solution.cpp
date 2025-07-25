class Solution {
  public:
    vector<int> nextLargerElement(vector<int> &arr) {
        vector<int> newArr;
        int n = arr.size();
        for(int i=0;i<n;i++){
            newArr.push_back(arr[i]);
        }
        for(int i=0;i<n;i++){
            newArr.push_back(arr[i]);
        }
        n = newArr.size();
        stack<int> st;
        for(int i=n-1;i>=0;i--){
            while(!st.empty() && st.top()<=newArr[i]){
                st.pop();
            }
            int t = newArr[i];
            if(st.empty())
              newArr[i] = -1;
            else
              newArr[i] = st.top();
            st.push(t);
        }
        n = arr.size();
        while(newArr.size()!=n){
            newArr.pop_back();
        }
        return newArr;
    }
};
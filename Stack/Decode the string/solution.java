
class Solution {
    static String decodeString(String s) {
        Stack<Integer> count = new Stack<>();
        Stack<String> result = new Stack<>();
        char[] arr =s.toCharArray();
        int i = 0 , num = 0;
        String curr = "";
        while ( i < s.length()) {
            if(Character.isDigit(arr[i])) {
                num = num*10+(arr[i]-'0');
                i++;
            } else if(arr[i] == '[') {
                count.push(num);
                num = 0;
                result.add(curr);
                curr = "";
                i++;
            } else if (arr[i] == ']') {
                int n = count.pop();
                StringBuilder sb = new StringBuilder(result.pop());
                for(int j =0;j<n;j++) {
                    sb.append(curr);
                }
                curr = sb.toString();
                i++;
            } else {
                curr +=arr[i];
                i++;
            }
        }
        return curr;

    }
}
import java.util.*;

class Solution {
    public ArrayList<String> graycode(int n) {
        ArrayList<String> result = new ArrayList<>();
        if (n == 1) {
            result.add("0");
            result.add("1");
            return result;
        }
        
        ArrayList<String> prev = graycode(n - 1);
        for (String s : prev) {
            result.add("0" + s);
        }
        for (int i = prev.size() - 1; i >= 0; i--) {
            result.add("1" + prev.get(i));
        }
        return result;
    }
}
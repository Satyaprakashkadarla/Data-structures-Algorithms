import java.util.ArrayList;

class Solution {
    public ArrayList<String> findExpr(String s, int target) {
        ArrayList<String> result = new ArrayList<>();
        backtrack(s, target, 0, 0, 0, "", result);
        return result;
    }
    
    private void backtrack(String s, int target, int index, long eval, long prev, String path, ArrayList<String> result) {
        if (index == s.length()) {
            if (eval == target) {
                result.add(path);
            }
            return;
        }
        
        for (int i = index; i < s.length(); i++) {
            if (i != index && s.charAt(index) == '0') {
                break;
            }
            
            long current = Long.parseLong(s.substring(index, i + 1));
            
            if (index == 0) {
                backtrack(s, target, i + 1, current, current, path + current, result);
            } else {
                // Try addition
                backtrack(s, target, i + 1, eval + current, current, path + "+" + current, result);
                
                // Try subtraction
                backtrack(s, target, i + 1, eval - current, -current, path + "-" + current, result);
                
                // Try multiplication - need to handle precedence
                // For multiplication, we subtract the previous value and add (prev * current)
                // because multiplication has higher precedence
                backtrack(s, target, i + 1, eval - prev + (prev * current), prev * current, path + "*" + current, result);
            }
        }
    }
}
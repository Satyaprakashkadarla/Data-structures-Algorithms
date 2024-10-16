import java.util.List;

class Solution {
    public boolean checkSorted(List<Integer> arr) {
        if (arr.size() < 4) {
            return true;
        }
        int error = 0;
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i) > arr.get(i + 1)) {
                error++;
            }
        }
        return error == 3;
    }
}
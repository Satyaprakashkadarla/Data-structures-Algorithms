class Solution {
    public boolean canServe(int[] arr) {
        int count5 = 0, count10 = 0;
        
        for (int bill : arr) {
            if (bill == 5) {
                count5++;
            } else if (bill == 10) {
                if (count5 == 0) return false;
                count5--;
                count10++;
            } else { // bill == 20
                if (count10 >= 1 && count5 >= 1) {
                    count10--;
                    count5--;
                } else if (count5 >= 3) {
                    count5 -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
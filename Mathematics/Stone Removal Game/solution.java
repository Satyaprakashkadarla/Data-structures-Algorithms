
class Solution {
    public boolean canAliceWin(int n) {
        int stones = 10;
        boolean alice = true;

        while (n >= stones) {
            n -= stones;
            stones--;

            if (stones == 0) break;

            alice = !alice;
        }

        return !alice;
    }
}

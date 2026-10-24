class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int startAlice = getDay(arriveAlice, daysInMonth);
        int endAlice = getDay(leaveAlice, daysInMonth);
        int startBob = getDay(arriveBob, daysInMonth);
        int endBob = getDay(leaveBob, daysInMonth);

        int start = Math.max(startAlice, startBob);
        int end = Math.min(endAlice, endBob);

        return Math.max(0, end - start + 1);
    }

    private int getDay(String date, int[] daysInMonth) {
        int month = Integer.parseInt(date.substring(0, 2));
        int day = Integer.parseInt(date.substring(3, 5));

        for (int i = 0; i < month - 1; i++) {
            day += daysInMonth[i];
        }

        return day;
    }
}
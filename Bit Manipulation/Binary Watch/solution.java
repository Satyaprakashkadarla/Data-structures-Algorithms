class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();
      
        for (int hour = 0; hour < 12; hour++) {
            for (int minute = 0; minute < 60; minute++) {
                
                int totalBitsSet = Integer.bitCount(hour) + Integer.bitCount(minute);
              
               
                if (totalBitsSet == turnedOn) {
                   
                    String timeString = String.format("%d:%02d", hour, minute);
                    result.add(timeString);
                }
            }
        }
      
        return result;
    }
}
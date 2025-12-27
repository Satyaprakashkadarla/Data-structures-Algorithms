class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
      
        PriorityQueue<int[]> busyRooms = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];  // Sort by room number if end times are equal
            }
            return a[0] - b[0];  // Sort by end time
        });
      
        // Priority queue to track available rooms (sorted by room number)
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
      
        // Initialize all rooms as available
        for (int roomId = 0; roomId < n; roomId++) {
            availableRooms.offer(roomId);
        }
      
        // Track the count of meetings held in each room
        int[] meetingCount = new int[n];
      
        // Process each meeting
        for (int[] meeting : meetings) {
            int startTime = meeting[0];
            int endTime = meeting[1];
          
            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= startTime) {
                int[] finishedRoom = busyRooms.poll();
                availableRooms.offer(finishedRoom[1]);  
            }
          
            int assignedRoom;
          
            if (!availableRooms.isEmpty()) {
                
                assignedRoom = availableRooms.poll();
                busyRooms.offer(new int[] {endTime, assignedRoom});
            } else {
                
                int[] earliestRoom = busyRooms.poll();
                assignedRoom = earliestRoom[1];
                int delayedEndTime = earliestRoom[0] + (endTime - startTime);
                busyRooms.offer(new int[] {delayedEndTime, assignedRoom});
            }
          
            meetingCount[assignedRoom]++;
        }
      
        
        int mostUsedRoom = 0;
        for (int roomId = 0; roomId < n; roomId++) {
            if (meetingCount[roomId] > meetingCount[mostUsedRoom]) {
                mostUsedRoom = roomId;
            }
        }
      
        return mostUsedRoom;
    }
}

class Solution {
    
    public boolean judgeCircle(String moves) {
        // Initialize coordinates at origin
        int xCoordinate = 0;
        int yCoordinate = 0;
      
        // Process each move character
        for (char move : moves.toCharArray()) {
            switch (move) {
                case 'U':  // Move up
                    yCoordinate++;
                    break;
                case 'D':  // Move down
                    yCoordinate--;
                    break;
                case 'L':  // Move left
                    xCoordinate--;
                    break;
                case 'R':  // Move right
                    xCoordinate++;
                    break;
                default:
                    // Invalid move character, ignore
                    break;
            }
        }
      
        // Check if returned to origin
        return xCoordinate == 0 && yCoordinate == 0;
    }
}
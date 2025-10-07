class Solution {
    public ArrayList<Integer> bottomView(Node root) {
        ArrayList<Integer> result = new ArrayList<>();
        if (root == null) return result;
        
        // TreeMap to store horizontal distance -> node value
        // Automatically sorts by key (horizontal distance)
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        // Queue for BFS, storing node and its horizontal distance
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));
        
        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            Node node = current.node;
            int hd = current.horizontalDistance;
            
            // Always update the map with current node for this horizontal distance
            // This ensures we get the last node at each horizontal distance
            map.put(hd, node.data);
            
            // Add left child to queue
            if (node.left != null) {
                queue.add(new Pair(node.left, hd - 1));
            }
            
            // Add right child to queue
            if (node.right != null) {
                queue.add(new Pair(node.right, hd + 1));
            }
        }
        
        // Add values from map to result list (already sorted by horizontal distance)
        for (int value : map.values()) {
            result.add(value);
        }
        
        return result;
    }
    
    // Helper class to store node with its horizontal distance
    class Pair {
        Node node;
        int horizontalDistance;
        
        Pair(Node node, int horizontalDistance) {
            this.node = node;
            this.horizontalDistance = horizontalDistance;
        }
    }
}
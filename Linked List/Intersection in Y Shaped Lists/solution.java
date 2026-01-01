class Solution {
    public Node intersectPoint(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        
        // Get lengths and last nodes
        int len1 = getLength(head1);
        int len2 = getLength(head2);
        
        // Adjust longer list
        if (len1 > len2) {
            head1 = moveForward(head1, len1 - len2);
        } else {
            head2 = moveForward(head2, len2 - len1);
        }
        
        // Move together until intersection
        while (head1 != null && head2 != null) {
            if (head1 == head2) return head1;
            head1 = head1.next;
            head2 = head2.next;
        }
        
        return null;
    }
    
    private int getLength(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
    
    private Node moveForward(Node head, int steps) {
        for (int i = 0; i < steps; i++) {
            head = head.next;
        }
        return head;
    }
}
class Solution {
    public Node addTwoLists(Node head1, Node head2) {
        // Reverse both lists
        Node rev1 = reverse(head1);
        Node rev2 = reverse(head2);
        
        Node dummy = new Node(0);
        Node curr = dummy;
        int carry = 0;
        
        while (rev1 != null || rev2 != null) {
            int sum = carry;
            if (rev1 != null) {
                sum += rev1.data;
                rev1 = rev1.next;
            }
            if (rev2 != null) {
                sum += rev2.data;
                rev2 = rev2.next;
            }
            carry = sum / 10;
            curr.next = new Node(sum % 10);
            curr = curr.next;
        }
        
        if (carry > 0) {
            curr.next = new Node(carry);
        }
        
        Node result = reverse(dummy.next);
        
        // Remove leading zeros
        while (result != null && result.data == 0 && result.next != null) {
            result = result.next;
        }
        
        return result;
    }
    
    private Node reverse(Node head) {
        Node prev = null;
        Node curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
class Solution {
    public boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;
        
        // Find middle
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half
        Node secondHalf = reverse(slow);
        Node firstHalf = head;
        
        // Compare
        Node temp = secondHalf;
        boolean res = true;
        while (temp != null) {
            if (firstHalf.data != temp.data) {
                res = false;
                break;
            }
            firstHalf = firstHalf.next;
            temp = temp.next;
        }
        
        // Restore list (optional)
        reverse(secondHalf);
        
        return res;
    }
    
    private Node reverse(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
class Solution {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    public int countNodesinLoop(Node head) {
        if (head == null || head.next == null)
            return 0;

        Node slow = head;
        Node fast = head;

        slow = slow.next;
        fast = fast.next.next;

        // Detect loop using Floyd's cycle detection algorithm
        while (fast != null && fast.next != null) {
            if (slow == fast)
                break;
            slow = slow.next;
            fast = fast.next.next;
        }

        // If no loop found
        if (slow != fast)
            return 0;

        // Count nodes in the loop
        int count = 1;
        slow = slow.next;
        while (slow != fast) {
            count++;
            slow = slow.next;
        }

        return count;
    }
}

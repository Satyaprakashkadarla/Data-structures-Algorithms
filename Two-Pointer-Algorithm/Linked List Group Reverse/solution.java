class ListNode {
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;

        ListNode prev = null;
        ListNode curr = head;
        int count = 0;
        ListNode nextNode = null;

        while (curr != null && count < k) {
            nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
            count++;
        }

        if (nextNode != null) {
            head.next = reverseKGroup(nextNode, k);
        }

        return prev;
    }
}

// Example Usage:
public class Main {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int k = 3;
        Solution solution = new Solution();
        ListNode newHead = solution.reverseKGroup(head, k);

        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }
}

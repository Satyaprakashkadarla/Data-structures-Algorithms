/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int first = -1;
        int prev = -1;
        int minDist = Integer.MAX_VALUE;

        ListNode prevNode = head;
        ListNode curr = head.next;
        int index = 1;

        while (curr != null && curr.next != null) {
            int prevVal = prevNode.val;
            int currVal = curr.val;
            int nextVal = curr.next.val;

            // Check if current node is a critical point
            if ((currVal > prevVal && currVal > nextVal) ||
                (currVal < prevVal && currVal < nextVal)) {

                if (first == -1) {
                    first = index;
                }

                if (prev != -1) {
                    minDist = Math.min(minDist, index - prev);
                }

                prev = index;
            }

            prevNode = curr;
            curr = curr.next;
            index++;
        }

        // Fewer than 2 critical points
        if (first == -1 || prev == first) {
            return new int[]{-1, -1};
        }

        // Maximum distance is between first and last critical points
        int maxDist = prev - first;

        return new int[]{minDist, maxDist};
    }
}

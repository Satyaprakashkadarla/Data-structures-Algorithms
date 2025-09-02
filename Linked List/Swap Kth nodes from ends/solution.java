class Solution {
    public Node swapKth(Node head, int k) {
        if (head == null) return head;

        // Step 1: Find length
        int n = 0;
        Node curr = head;
        while (curr != null) {
            n++;
            curr = curr.next;
        }

        // Step 2: If k is invalid
        if (k > n) return head;

        // Step 3: If kth from start and end are same, do nothing
        if (2 * k - 1 == n) return head;

        // Step 4: Find kth node from start
        Node x = head;
        for (int i = 1; i < k; i++) {
            x = x.next;
        }

        // Step 5: Find kth node from end (n - k + 1)
        Node y = head;
        for (int i = 1; i < n - k + 1; i++) {
            y = y.next;
        }

        // Step 6: Swap data
        int temp = x.data;
        x.data = y.data;
        y.data = temp;

        return head;
    }
}

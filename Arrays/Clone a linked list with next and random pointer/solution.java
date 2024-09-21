class Node {
    int data;
    Node next;
    Node random;

    Node(int data) {
        this.data = data;
        this.next = null;
        this.random = null;
    }
}

class Solution {
    public Node copyList(Node head) {
        // Step 1: Create new nodes and interleave them with original nodes
        if (head == null) return null;
        
        Node temp = head;
        while (temp != null) {
            Node newNode = new Node(temp.data);
            newNode.next = temp.next;
            temp.next = newNode;
            temp = newNode.next;
        }

        // Step 2: Assign random pointers to the cloned nodes
        temp = head;
        while (temp != null) {
            if (temp.random != null) {
                temp.next.random = temp.random.next;
            } else {
                temp.next.random = null;
            }
            temp = temp.next.next;
        }

        // Step 3: Separate the original list and the cloned list
        Node original = head;
        Node clonedHead = head.next;
        Node clonedTemp = clonedHead;

        while (original != null) {
            original.next = original.next.next;
            if (clonedTemp.next != null) {
                clonedTemp.next = clonedTemp.next.next;
            }
            original = original.next;
            clonedTemp = clonedTemp.next;
        }

        return clonedHead;
    }
}

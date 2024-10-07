class Node {
    int data;
    Node npx; // XOR of previous and next node

    Node(int data) {
        this.data = data;
        this.npx = null;
    }
}

public class XORLinkedList {
    private Node head;

    public XORLinkedList() {
        this.head = null;
    }

    // XOR function
    private Node XOR(Node a, Node b) {
        return (Node) ((Object) a ^ (Object) b);
    }

    // Insert a new node at the beginning of the list
    public void insert(int data) {
        Node newNode = new Node(data);
        newNode.npx = head;

        if (head != null) {
            head.npx = XOR(head.npx, newNode);
        }
        head = newNode;
    }

    // Get the list as an array
    public int[] getList() {
        java.util.List<Integer> theList = new java.util.ArrayList<>();
        Node current = head;
        Node prev = null;

        while (current != null) {
            theList.add(current.data);
            Node next = XOR(prev, current.npx);

            prev = current;
            current = next;
        }

        // Convert List<Integer> to int[]
        return theList.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        XORLinkedList list = new XORLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);

        int[] result = list.getList();
        for (int value : result) {
            System.out.print(value + " ");
        }
    }
}

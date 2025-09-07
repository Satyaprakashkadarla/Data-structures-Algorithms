/*
class Node {
    int data;
    Node next;

    Node(int x) {
        data = x;
        next = null;
    }
}
*/

import java.util.PriorityQueue;
import java.util.Comparator;

class Solution {
    Node mergeKLists(Node[] arr) {
        if (arr == null || arr.length == 0) return null;
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return a.data - b.data;
            }
        });
        for (Node node : arr) {
            if (node != null) {
                minHeap.add(node);
            }
        }
        Node dummy = new Node(0);
        Node tail = dummy;
        while (!minHeap.isEmpty()) {
            Node minNode = minHeap.poll();
            tail.next = minNode;
            tail = tail.next;
            
            if (minNode.next != null) {
                minHeap.add(minNode.next);
            }
        }
        return dummy.next;
    }
}
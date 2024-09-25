import java.util.ArrayList;

class Node {
    int data;
    Node next;
    
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class Solution {
    public boolean isPalindrome(Node head) {
        ArrayList<Integer> nodeValues = new ArrayList<>();
        Node temp = head;

        // Collecting node values
        while (temp != null) {
            nodeValues.add(temp.data);
            temp = temp.next;
        }

        // Checking if the collected values form a palindrome
        int i = 0;
        int j = nodeValues.size() - 1;

        while (i < j) {
            if (nodeValues.get(i).equals(nodeValues.get(j))) {
                i++;
                j--;
            } else {
                return false; // Not a palindrome
            }
        }

        return true; // Is a palindrome
    }
}

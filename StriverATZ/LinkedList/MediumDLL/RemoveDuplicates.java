package StriverATZ.LinkedList.MediumDLL;

import StriverATZ.LinkedList.OneDLinkedList.LL.DNode;

public class RemoveDuplicates {
    DNode head = null;
    public DNode removeDuplicates() {
        // If the list is empty, return null
        if (head == null) return null;

        DNode current = head;

        // Traverse the list until the second last node
        while (current != null && current.next != null) {
            DNode nextDistinct = current.next;

            // Skip and unlink all nodes with the same value as current
            while (nextDistinct != null && nextDistinct.data == current.data) {
                nextDistinct = nextDistinct.next;
            }

            // Connect current node to the next distinct node
            current.next = nextDistinct;
            if (nextDistinct != null) {
                nextDistinct.back = current;
            }

            // Move to the next node
            current = current.next;
        }

        return head;
    }
}

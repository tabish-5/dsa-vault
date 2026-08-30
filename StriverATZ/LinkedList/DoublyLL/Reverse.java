package StriverATZ.LinkedList.DoublyLL;

import StriverATZ.LinkedList.OneDLinkedList.LL.DNode;

public class Reverse {
     public DNode reverseDLL(DNode head) {
        DNode current = head;
        DNode last = null;
        DNode temp;

        while (current != null) {

            // Swap next and back pointers of current node
            temp = current.next;
            current.next = current.back;
            current.back = temp;

            // Move last pointer to current (this will become new head)
            last = current;

            // Move to next node (originally current.next but now is back due to swap)
            current = temp;
        }

        // Return the new head (was the last node in original list)
        return last;
    }
}

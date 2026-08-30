package StriverATZ.LinkedList.MediumDLL;

import StriverATZ.LinkedList.OneDLinkedList.LL.DNode;

public class DeletealloccurrencesofakeyinDLL {

    public DNode deleteTargetNodes(DNode head, int target) {
        DNode current = head;

        while (current != null) {
            DNode nextNode = current.next;

            if (current.data == target) {
                if (current.back != null)
                    current.back.next = current.next;
                else
                    head = current.next; 

                if (current.next != null)
                    current.next.back = current.back;
            }

            current = nextNode;
        }

        return head;
    }
}

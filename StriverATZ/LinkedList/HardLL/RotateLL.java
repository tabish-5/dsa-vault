package StriverATZ.LinkedList.HardLL;

import StriverATZ.LinkedList.OneDLinkedList.LL.ListNode;

public class RotateLL {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1. Find the length and tail node
        ListNode tail = head;
        int len = 1;
        while (tail.next != null) {
            tail = tail.next;
            len++;
        }

        // 2. Connect the tail to the head to form a circle
        tail.next = head;

        // 3. Find the effective number of rotations needed
        k = k % len;
        int stepsToNewTail = len - k;

        // 4. Move to the new tail
        ListNode newTail = tail;
        while (stepsToNewTail > 0) {
            newTail = newTail.next;
            stepsToNewTail--;
        }

        // 5. Break the circle and set the new head
        ListNode newHead = newTail.next;
        newTail.next = null;

        return newHead;
    }
}

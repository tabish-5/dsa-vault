package StriverATZ.LinkedList.MediumLL;

import StriverATZ.LinkedList.OneDLinkedList.LL.ListNode;

public class ReverseLL {
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }


    public ListNode reverseListII(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode newHead = reverseListII(head.next);
        ListNode front = head.next;

        front.next = head;

        head.next = null;

        return newHead;
    }
}

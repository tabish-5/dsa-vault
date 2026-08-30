package StriverATZ.LinkedList.MediumLL;

import StriverATZ.LinkedList.OneDLinkedList.LL.ListNode;

public class RemoveNthNodefromtheEndofLL {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode left = head, right = head;
        int i = 0;
        while (i<n) {
            right = right.next;
            i++;
        }
        if(right == null){
            return head.next;
        }
        while (right.next != null) {
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;

        return head;
    }
}

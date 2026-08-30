package StriverATZ.LinkedList.MediumLL;

import StriverATZ.LinkedList.OneDLinkedList.LL.ListNode;

public class OddEvenLL {
    public ListNode oddEvenList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode odd = head , even = head.next , evenhead = even;
        while(even!=null && even.next!=null){
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenhead;
        return head;
    }
}

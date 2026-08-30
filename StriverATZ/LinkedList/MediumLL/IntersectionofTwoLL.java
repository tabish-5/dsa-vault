package StriverATZ.LinkedList.MediumLL;

import StriverATZ.LinkedList.OneDLinkedList.LL.ListNode;

public class IntersectionofTwoLL {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int sizea = 0 , sizeb = 0;
        ListNode curra = headA, currb = headB;
        while (curra != null) {
            sizea++;
            curra = curra.next;
        }
        while (currb != null) {
            sizeb++;
            currb = currb.next;
        }
        curra = headA; currb = headB;
        if(sizea > sizeb){
            while (sizea != sizeb) {
                curra = curra.next;
                sizea--;
            }
        } 
        else if (sizeb > sizea) {
            while (sizea != sizeb) {
                currb = currb.next;
                sizeb--;
            }
        } 
        while (sizea > 0) {
            if (curra == currb) {
                return curra;
            }
            curra = curra.next;
            currb = currb.next;
        }  
        return null;
    }
}

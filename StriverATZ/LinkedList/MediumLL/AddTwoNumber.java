package StriverATZ.LinkedList.MediumLL;

import StriverATZ.LinkedList.OneDLinkedList.LL.ListNode;

public class AddTwoNumber {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(-1);
        ListNode itr = ans;
        ListNode itr1 = l1;
        ListNode itr2 = l2;
        int carry=0, sum;

        while (itr1 != null && itr2 != null) {
            sum = carry + itr1.val + itr2.val;
            // int temp = sum %10;
            itr.next = new ListNode(sum%10);
            itr = itr.next;
            carry = sum/10;
            itr1= itr1.next;itr2= itr2.next;
        }
        
        while (itr1!= null) {
            sum = carry + itr1.val;
            itr.next = new ListNode(sum%10);
            itr = itr.next;
            carry = sum/10;
            itr1 = itr1.next;
        }
        
        while (itr2!= null) {
            sum = carry + itr2.val;
            itr.next = new ListNode(sum%10);
            itr = itr.next;
            carry = sum/10;
            itr2 = itr2.next;
        }
        
        while (carry!=0) {
            itr.next = new ListNode(carry%10);
            itr = itr.next;
            carry /=10;
        }
        return ans.next;   
    }
}

package StriverATZ.LinkedList.MediumLL;

import StriverATZ.LinkedList.OneDLinkedList.LL.ListNode;

public class PalindromeLL {
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode slow = head, fast = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        // print(slow);
        ListNode prev = null;
        ListNode curr = head;
        ListNode next;

        while(curr != slow){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        
        boolean left = check(prev, slow);
        boolean right = check(prev, slow.next);

        // print(prev);
        // print(slow);
        return (left == true || right == true)? true: false; 
    }

    boolean check(ListNode left, ListNode right){
        while(left != null && right != null){
            if(left.val != right.val) return false;
            left = left.next;
            right = right.next;
        }

        return (left == null && left == null)? true :false;
    }

    void print(ListNode head){
        while(head != null){
            System.out.print(head.val+" ");
            head = head.next;
        }
        System.out.println();
    }
}

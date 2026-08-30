package StriverATZ.LinkedList.MediumLL;

import StriverATZ.LinkedList.OneDLinkedList.LL.ListNode;

public class SortLL {
    public ListNode sortList(ListNode head) {

        // 0 or 1 node
        if (head == null || head.next == null) {
            return head;
        }

        // Find middle
        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split the list
        ListNode right = slow.next;
        slow.next = null;

        // Sort both halves
        ListNode left = sortList(head);
        right = sortList(right);

        // Merge sorted lists
        return merge(left, right);
    }

    public ListNode merge(ListNode left, ListNode right) {

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (left != null && right != null) {

            if (left.val <= right.val) {
                curr.next = left;
                left = left.next;
            } else {
                curr.next = right;
                right = right.next;
            }

            curr = curr.next;
        }

        if (left != null) {
            curr.next = left;
        }

        if (right != null) {
            curr.next = right;
        }

        return dummy.next;
    }
}

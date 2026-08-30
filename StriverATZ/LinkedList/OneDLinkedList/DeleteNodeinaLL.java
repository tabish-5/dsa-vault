package StriverATZ.LinkedList.OneDLinkedList;
import StriverATZ.LinkedList.OneDLinkedList.LL.ListNode;


public class DeleteNodeinaLL {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}

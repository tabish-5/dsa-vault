package StriverATZ.LinkedList.OneDLinkedList;


public class LL {
    public static class Node {
        public int data;      // Data value
        public Node next;     // Pointer to next node

        // Constructor with data and next
        Node(int data1, Node next1) {
            data = data1;
            next = next1;
        }

        // Constructor with only data
        Node(int data1) {
            data = data1;
            next = null;
        }
    }

    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int x) { val = x; }
    }

    public static class DNode {
        public int data;
        public DNode next;
        public DNode back;

        // Constructor to initialize data only
        DNode(int data) {
            this.data = data;
            this.next = null;
            this.back = null;
        }

        // Constructor to initialize data, next, and back
        DNode(int data, DNode next, DNode back) {
            this.data = data;
            this.next = next;
            this.back = back;
        }
    }
}

package StriverATZ.LinkedList.HardLL;

public class CopyLLwithRandomPointer {
    class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
    

    public Node copyRandomList(Node head) {
        copyNode(head);
        assignRandomNode(head);
        return separateNode(head);
    }

    public void copyNode(Node head){

        Node curr=head;

        while(curr!=null){
            Node copyNode=new Node(curr.val);
            copyNode.next=curr.next;
            curr.next=copyNode;
            curr=curr.next.next;
        }
    }

    public void assignRandomNode(Node head){

        Node curr=head;

        while(curr!=null){

            if(curr.random!=null){
                curr.next.random=curr.random.next;
            }
            curr=curr.next.next;
        }
    }

    public Node separateNode(Node head){

        Node dummy=new Node(0);
        Node curr=head;
        Node res=dummy;

        while(curr!=null){
            res.next=curr.next;
            curr.next=curr.next.next;
            res=res.next;
            curr=curr.next;
        }
        return dummy.next;
    }
}

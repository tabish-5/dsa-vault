package StriverATZ.Recursion.StrongHold;

import java.util.*;
public class ReverseaStack {

    public static void insertAtBottom(Stack<Integer> st, int val) {
        if (st.isEmpty()) {
            st.push(val);
            return;
        }

        int topVal = st.pop();

        insertAtBottom(st, val);

        st.push(topVal);
    }

    public static void reverseStack(Stack<Integer> st) {
        if (st.isEmpty()) return;

        int topVal = st.pop();

        reverseStack(st);

        insertAtBottom(st, topVal);
    }

    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(4);
        st.push(1);
        st.push(3);
        st.push(2);

        ReverseaStack.reverseStack(st);

        // Print the reversed stack
        System.out.print("Reversed Stack: ");
        while (!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
        System.out.println();
    }
}

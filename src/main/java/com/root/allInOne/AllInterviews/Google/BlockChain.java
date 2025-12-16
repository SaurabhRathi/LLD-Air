package com.root.allInOne.AllInterviews.Google;
/*
* You’re given a secured linked list where each node contains:
data: a string
next: pointer to the next node
hashValue: an integer
There’s a given hash(String input) function that returns an integer hash. You can use this function but cannot modify it.
*
* */

import java.util.Stack;

public class BlockChain {
    //this function is give to us
    int getHash(String text) {
        return 0;
    }
    class Node {
        String data; Node next; int hashVal;
        Node(String data, Node next, int hashVal) {
            this.data = data; this.next = next; this.hashVal = hashVal;
        }
    }

    // []->[]->[]
    // we can use stack here
    /*
        for this question, I have two approaches in mind
            1. use stack, since we need to keep track of previous
            2. we can reverse the LinkedList and reverse it back again

            for now i'll use stack
    * */

    Stack<Node> stack = new Stack<>();
    Node insertAtPosition(String data, int pos, Node head) {
        Node dummy = new Node("", head, -1);
        Node ptr = dummy;
        for(int i=0; i<=pos && ptr!=null; i++) {
            stack.add(ptr);
            ptr = ptr.next;
        }
        Node next = stack.peek().next;
        int new_node_hash = getHash(data+""+(next==null? "" : ""+next.hashVal));
        stack.peek().next = new Node(data, next, new_node_hash);
        while(stack.size() > 0) {
            Node stack_peek = stack.peek();
            stack_peek.hashVal = getHash(stack_peek.data + stack_peek.next.hashVal);
            stack.pop();
        }
        return dummy.next;
    }

    boolean isChainValid(Node head) {
        return true;
    }
}

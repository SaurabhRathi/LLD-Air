package com.root.allInOne.AllInterviews;

//Koko loves to eat bananas. There are N piles of bananas, the i-th pile has piles[i] bananas. The guards have gone and will come back in H hours.
//
//Koko can decide her bananas-per-hour eating speed of K. Each hour, she chooses some pile of bananas, and eats K bananas from that pile.
//
//        If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.
//
//        Koko likes to eat slowly but still wants to finish eating all the bananas before the guards come back.
//
//        Return the minimum integer K such that she can eat all the bananas within H hours.
//
////

//
//         [30,11,23,4,20]
//
//        [7,11,23,4,20] 1st hour
//        [0,11,23,4,20] 2nd hour
//        [0,0,23,4,20] 3rd hour
//        [0,0,0,4,20] 4th hour
//        [0,0,0,0,20] 5th hour
//
//        [0,0,0,0,0] 6th hour
//
//
//        Output: 23

// pile[i] - 10^6
// piles length - 10^6

// 100,1000,200,10  -> 1000 bananas hour
//  2,2,2  h=10   k=1

// starting from max value -> 1 (log(max value)*n)
//  1000 999 998 997 .... .. .. .. . 1


//H is very less



/*
Given a singly linked list, the task is to remove all the nodes with any node on their right whose value is greater and return the head of the modified linked list.
Input: head: 12->15->10->11->5->6->2->3
Output: 15->11->6->3
Explanation: Node with value 12 , 10, 5, and 2 will be deleted as the greater value is present on right side of nodes

head -> null

5 10
10 5

12->15->10->11->5->6->2->3 <-[-1]

*/



class Node {
    public int val;
    public Node next;

    Node(int val) {
        this.val = val;
    }
}
public class ZetaInterview {
    public static void main(String[] args) {
        //System.out.println(minimumSpeedToEatAllBananas(new int[]{30,11,23,4,20,10}, 10));
        Node head = createLinkedList(new int[]{12, 15, 10, 11, 5, 6, 2, 3, 7});
        print(removeNodesWithGreaterOnRight(head));
    }

    static Node createLinkedList(int[] arr) {
        int len = arr.length;
        Node head = new Node(-1);
        Node itr = head;
        for (int i = 0; i < len; i++) {
            Node temp = new Node(arr[i]);
            itr.next = temp;
            itr = itr.next;
        }
        return head.next;
    }

    static void print(Node head) {
        System.out.println();
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    static Node removeNodesWithGreaterOnRight(Node head) {
        if (head == null) return head;
        if (head.next == null) return head;
        Node headOfReverseLL = reverseLinkedList(head);
        Node itrHead = headOfReverseLL;
        Node nonRemovedHead = headOfReverseLL;
        int maxTillNow = itrHead.val;
        itrHead = itrHead.next;
        while (itrHead != null) {

            if (itrHead.val >= maxTillNow) {
                maxTillNow = itrHead.val;
                nonRemovedHead.next = itrHead;
                nonRemovedHead = nonRemovedHead.next;
            }
            itrHead = itrHead.next;
        }
        nonRemovedHead.next = null;
        return reverseLinkedList(headOfReverseLL);
    }

    static Node reverseLinkedList(Node head) {
        //we have atleast 2 nodes
        Node prev = head, curr = head.next;
        prev.next = null;

        while (curr != null) {
            Node ahead = curr.next;
            curr.next = prev;
            prev = curr;
            curr = ahead;
        }
        return prev; //new head
    }

}

//    //Input: piles = [30,11,23,4,20], H = 6
//    static int minimumSpeedToEatAllBananas(int[] piles, int h) {
//        int len = piles.length;
//        if(h<len) return -1;
//
//        int max = 0;
//        for(int i=0; i<len; i++) {
//            max = Math.max(max, piles[i]);
//        }
//        return binarySearch(1, max, piles, h);
//    }
//
//    static int binarySearch(int low, int high, int[] piles, int h) {
//        int len = piles.length;
//        int minSpeed = high;
//        while(low<=high) {
//            int speed = (low+high)/2;
//            int hoursTaken = 0;
//            for(int i=0; i<len; i++) {
//                if (piles[i] <= speed) {
//                    hoursTaken += 1;
//                } else {
//                    hoursTaken += Math.ceil(piles[i] * 1.0 / speed);
//                }
//            }
//            if(hoursTaken>h) {
//                //increase the speed
//                low = speed+1;
//            } else {
//                //look for lesser speed
//                minSpeed = speed;
//                high = speed - 1;
//            }
//        }
//        return minSpeed;
//    }
//}

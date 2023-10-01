import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

//Code written by me but referenced from https://www.geeksforgeeks.org/merge-k-sorted-linked-lists/
class LinkedNode {
    int key;
    LinkedNode next;

    public LinkedNode(int key) {
        this.key = key;
        next = null;
    }
}
class LinkedList2 {
    public LinkedNode mergeKLists(LinkedNode[] lists) {
        PriorityQueue<LinkedNode> minHeap = new PriorityQueue<>((a,b) -> a.key - b.key);

        for (LinkedNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }

        LinkedNode head = new LinkedNode(-1);
        LinkedNode temp = head;

        while (!minHeap.isEmpty()) {
            LinkedNode min = minHeap.poll(); //remove the smallest node from heap
            temp.next = min;
            temp = temp.next;

            if (min.next != null) {
                minHeap.add(min.next);
            }
        }
        return head.next;

    }

    public static void main(String[] args) {
        LinkedList2 merge = new LinkedList2();

        LinkedNode[] lists = new LinkedNode[3];
        lists[0] = new LinkedNode(1);
        lists[0].next = new LinkedNode(4);
        lists[0].next.next = new LinkedNode(7);

        lists[1] = new LinkedNode(1);
        lists[1].next = new LinkedNode(3);
        lists[1].next.next = new LinkedNode(4);

        lists[2] = new LinkedNode(2);
        lists[2].next = new LinkedNode(6);

        LinkedNode merged = merge.mergeKLists(lists);

        LinkedNode temp = merged;

        while (temp != null) {
            System.out.print(temp.key + " ");
            temp = temp.next;
        }

    }
}

//The time complexity and space complexity for MinHeap method is O(N log (k))
// and space O(k) respectively.
// For the time complexity, inserting an element into a min-heap
// takes log k time and removal of an element also takes k time. This is because when we remove an item
// from the min heap, we replace it with the bottom left item.
//For the space complexity, we hold at maximum only k items which is the smallest items in each list one at a time, i.e
//3 because we are given 3 sorted lists.


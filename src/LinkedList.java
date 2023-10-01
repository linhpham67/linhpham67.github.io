import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Node {
    int key;
    Node next;

    public Node(int key) {
        this.key = key;
        next = null;
    }
}
public class LinkedList {
    public static Node n (int key) {
        return new Node(key);
    }

    public static void main(String[] args) {
        Node a = new Node(1); //reference
        a.next = new Node(4);
        a.next.next = new Node(7);

        Node b = new Node(1);
        b.next = new Node(3);
        b.next.next = new Node(4);

        Node c = new Node(2);
        c.next = new Node(6);

        List<Integer> sorted = new ArrayList<>();
        while (a != null) {
            sorted.add(a.key);
            a = a.next;
        }

        while (b != null) {
            sorted.add(b.key);
            b = b.next;
        }

        while (c != null) {
            sorted.add(c.key);
            c = c.next;
        }

        Collections.sort(sorted);
        System.out.println(sorted);

        Node result = new Node(0);
        Node temp = result;

        //link the elements in the list
        for (int i = 0; i < sorted.size(); i++) {
            result.next = new Node(sorted.get(i));
            result = result.next;
        }

        temp = temp.next;
        while (temp != null) {
            System.out.print(temp.key + " ");
            temp = temp.next;
        }
    }
}

//The time complexity for this method is O(NlogN) because the Collection.sort takes
//O(NlogN) time while the time traversing down each linked list depends on the number of elements in each list
//The space complexity takes O(N) time because it stores all the elements from each linked list.
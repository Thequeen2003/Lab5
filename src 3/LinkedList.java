/**********************************************************************
 * @file LinkedList.java
 * @brief This is a program about linkedlists
 * @author Alantis Green
 * @date: 3/24/2023
 * @acknowledgement:
 */
import java.util.Random;
// linked list class for a deck of cards
public class LinkedList {

    public Node head;
    public Node tail;
    public int size = 0;

    LinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    public boolean is_empty() {
        return head == null;
    }

    public void shuffle(int shuffle_count) {

        Random rand = new Random();
        for (int i = 0; i < shuffle_count; i++) {
            // pick two random integers
            int r1 = rand.nextInt(52);
            int r2 = rand.nextInt(52);

            swap(r1, r2); // swap nodes at these indices
        }
    }

    // remove a card from a specific index
    public Card remove_from_index(int index) {
        if (head == null || index < 0 || index >= size) {
            return null;
        }

        if (index == 0) {
            Card removedCard = head.data;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
            size--;
            return removedCard;
        }

        Node current = head;
        int currentIndex = 0;
        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }

        if (current == null) {
            return null;
        }

        Card removedCard = current.data;
        current.prev.next = current.next;
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }
        size--;
        return removedCard;
    }

    // insert a card at a specific index
    public void insert_at_index(Card x, int index) {
        if (index < 0 || index > size) {
            return;
        }

        Node newNode = new Node(x);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node current = head;
            int currentIndex = 0;
            while (current != null && currentIndex < index - 1) {
                current = current.next;
                currentIndex++;
            }
            if (current != null) {
                newNode.next = current.next;
                newNode.prev = current;
                current.next.prev = newNode;
                current.next = newNode;
            }
        }
        size++;
    }

    // swap two cards in the deck at the specific indices
    public void swap(int index1, int index2) {
        // remove the cards at the two indices
        Card card1 = remove_from_index(index1);
        Card card2 = remove_from_index(index2);

        // insert the cards back in the reversed order
        insert_at_index(card2, index1);
        insert_at_index(card1, index2);
    }

    // add card at the end of the list
    public void add_at_tail(Card data) {
        Node new_node = new Node(data);
        if (tail == null) {
            head = new_node;
            tail = new_node;
        } else {
            new_node.prev = tail;
            tail.next = new_node;
            tail = new_node;
        }
        size++;
    }

    // remove a card from the beginning of the list
    public Card remove_from_head() {
        if (head == null) {
            return null;
        }

        Card removedCard = head.data;
        head = head.next;
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
        size--;
        return removedCard;
    }

    public int size() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }


    public Card get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = head;
        int currentIndex = 0;
        while (current != null && currentIndex < index) {
            current = current.next;
            currentIndex++;
        }
        if (current == null) {
            return null;
        }
        return current.data;
    }
    public void sanity_check() {
        int count = 0;
        Node current = head;
        Node last = null;
        while (current != null) {
            // check if node is accidentally still attached
            if (current.prev != last) {
                System.out.println("Error: Node " + current.data + " has incorrect previous node.");
            }
            if (current.next != null && current.next.prev != current) {
                System.out.println("Error: Node " + current.data + " has incorrect next node.");
            }
            // move to next node
            last = current;
            current = current.next;
            count++;
        }
        // check size variable
        if (count != size) {
            System.out.println("Error: Size variable is incorrect.");
        }
        // check head and tail pointers
        if (head == null && tail != null) {
            System.out.println("Error: Head pointer is null but tail pointer is not null.");
        }
        if (tail == null && head != null) {
            System.out.println("Error: Tail pointer is null but head pointer is not null.");
        }
        if (head != null && head.prev != null) {
            System.out.println("Error: Head node has non-null previous node.");
        }
        if (tail != null && tail.next != null) {
            System.out.println("Error: Tail node has non-null next node.");
        }
    }
    public void print() {
        Node curr = head;
        int i = 1;
        while(curr != null) {
            if(curr.data != null) {
                curr.data.print_card();
            }
            if(curr.next != null) {
                System.out.print(" --> ");
            } else {
                System.out.println(" X");
            }
            if (i % 7 == 0) {
                System.out.println("");
            }
            i++;
            curr = curr.next;
        }
        System.out.println("");
    }

}



/**********************************************************************
 * @file Node.java
 * @brief This is a program node
 * @author Alantis Green
 * @date: 3/24/2023
 * @acknowledgement:
 */
public class Node {

    public Card data;
    public Node next;
    public Node prev;

    public Node(){
        this.data = new Card();
        next = null;
        prev = null;
    }

    public Node(Card data){
        this.data = data;
        next = null;
        prev = null;
    }
}
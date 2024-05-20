/*
Irene Avezzu' - 20142 - IAvezzu@unibz.it
Worked with: Massimo Marcon, Ivana Nworah Bortot 
*/
public class HTList {

    protected Node head;
    protected Node tail;

    public HTList(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }

    public void addAsHead(int i) {
        Node newNode = new Node(i, head.next);
        head = newNode;
    }

    public void addAsTail(int i) {
        Node newNode = new Node(i, null);
        tail.next = newNode;
        tail = newNode;
    }

    public void addAll(HTList i) {
        tail.next = i.head;
        tail = i.tail;
    }

    public HTList quickSort() {
        return null;
    }

    public HTList quickSortOpt() {
        return null;
    }

}

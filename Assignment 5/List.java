public class List {

    protected Node head;

    public List(Node head) {
        this.head = head;
    }

    public void addAsHead(int value){
        head = new Node(value, head);
    }

    void print() {
        printRec(head);
    }

    private void printRec(Node head) {
        if (head != null) {
            System.out.println(head.val);
            printRec(head.next);
        }
    }

    public boolean isEmpty(){
        if (head == null) {
            return true;
        }
        return false;
    }
    
    static class Node {

        protected int val;
        protected Node next;

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}

/*
Irene Avezzu' - 20142 - IAvezzu@unibz.it
Worked with: Massimo Marcon, Ivana Nworah Bortot 
*/
public class List {

    protected Node head;

    public List(Node head) {
        this.head = head;
    }

    public void addAsHead(int i) {
        Node newNode = new Node(i, head.next);
        head = newNode;
    }

    public boolean isEmpty() {
        return (head == null) ?
            true : false;
    }

    public int length() {
        int c = 0;
        Node n = head;
        while (n != null) {
            n = n.next;
            c++;
        }
        return c;
    }

    public int floor(int i) {
        int floor = Integer.MIN_VALUE;
        Node n = head;
        while (n != null) {
            if (n.val <= i && n.val > floor)
                floor = n.val;
            n = n.next;
        }
        return floor;
    }

    public void addAll(List l) {
        if (head == null){
            head = l.head;
        }
        else{
            Node n = head;
            while (n.next != null){
                n = n.next;
            }
            n.next = l.head;
        }
    }

    public int maxAscent() {
        if (head == null) return 0;
        int max = 1;
        Node n = head;
        int c = 1;
        while (n.next != null)
            if (n.next.val > n.val)
                c += 1;
            else {
                max = (max>c)?max:c;
                c = 1;
            }
            n = n.next;
        return max;
    }

    public boolean sorted() {
        if (head == null) return true;
        Node n = head;
        while (n.next != null) { //scan the whole list
            if (n.next.val > n.val)
                return false;
            n = n.next;
        }
        return false;
    }

    public int extractMax() {
        int maxValue;
        if (head == null) //empty list
            throw new RuntimeException("Empty list");
        else if (head.next == null) { //1 element list
            maxValue = head.val;
            head = null;
            return maxValue;
        }
        else{
            maxValue = head.val;
            Node n = head;
            Node beforeMaxNode = null;
            while (n.next != null) { //scan from first to last but one to find the max value and save the node before it
                if (n.next.val > maxValue){
                    beforeMaxNode = n; 
                    maxValue = n.next.val;
                }
            }

            if (beforeMaxNode == null) //if first element is the biggest
                head = head.next;
            else{
                if (beforeMaxNode.next.next == null) //if last element is the biggest
                    beforeMaxNode.next = null;
            else
                beforeMaxNode.next = beforeMaxNode.next.next; //if max value is in the middle of the array
            }
            return maxValue;
        }     
    }  

    public List selectionSort() {
        if (head == null || head.next == null) //empty list or list with 1 element
            return this;
        else {
            List l = new List(null);
            //since the extractMax method removes the max value from the list, if we need to not change the original list, we can create a copy it
            Node n = head;
            int maxValue = 0;
            while (l.head != null){
                maxValue = l.extractMax();
                l.addAsHead(maxValue);
            }
            return l;
        }
    }

    public void addSorted(int i) {
        Node nN = new Node(i, null);
        Node n = head;
        //empty list
        if (head == null){
            head = nN;
            return;
        }
        //i<head
        if (head.val >= i){ 
        nN.next = head;
        head = nN;
        return;
        }
        //i needs to be inserted in the middle or at the end
        while (n != null){
            if (n.next == null || n.next.val >= i){ //either last element or next element >= i 
                nN.next = n.next;
                n.next = nN;
                break;
            }
            n = n.next;
        }
    }

    public List insertionSort() {
        if (head == null || head.next == null) //empty list or list with 1 element
            return this;
        else {
            List l = new List(null);
            Node n = head;
            while (n != null){
                l.addSorted(n.val);
                n = n.next;
            }
            return l;
        }
    }

    public List mergeSort() {
        return mergeSort(this); 
    }

    public List mergeSort(List l) {
        if (head == null || head.next == null) //Base case: empty list or list with 1 element
            return this;
        else {
            //divide
            List a = new List(head);
            List b = new List(head.next);
            Node n = head.next.next;
            Boolean insertInA = true; //alternates between true and false
            while (n != null){ //divide the list in two by alternating copying the node into the two lists
                if (insertInA){
                    a.addAsHead(n.val);
                    insertInA = false;
                }
                else{
                    b.addAsHead(n.val);
                    insertInA = true;
                }
                n = n.next ;  
            }
            //conquer
            a = mergeSort(a);
            b = mergeSort(b);
            //combine
            List combinedList = new List(null);
            Node aNode = a.head; //next element to be inserted from a list
            Node bNode = b.head; //next element to be  from b list
            Node combinedNode; //last inserted element in the combined list
            //define the head of the combined list
            if (aNode.val <= bNode.val){
                combinedList.head = new Node(aNode.val, null);
                aNode = aNode.next;
            }
            else{
                combinedList.head = new Node(bNode.val, null);
                bNode = bNode.next;
            }
            combinedNode = combinedList.head; 
            //combine the rest of the two list
            while (aNode != null || bNode != null){
                if (aNode == null){
                    combinedNode.next = bNode; //insert the rest of the list
                    break;
                }
                if (bNode == null){
                    combinedNode.next = aNode; //insert the rest of the list
                    break;
                }
                if (aNode.val <= bNode.val){
                    combinedNode.next = new Node(aNode.val, null);
                    aNode = aNode.next;
                }
                else{
                    combinedNode.next = new Node(bNode.val, null);
                    bNode = bNode.next;
                }
                combinedNode = combinedNode.next; //increase the "index"
            }
            return combinedList;
        }
    }
}

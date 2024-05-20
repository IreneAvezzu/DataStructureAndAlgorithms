public class PQueue {

    PNode root;

    public PQueue(PNode root) {
        
        this.root = root;
    }

    //2.1)
    boolean isEmpty() {
        if (root == null)
            return true;
        return false;
    }

    //2.2) inserts a value into the queue.
    public void insert(int value) {
        PNode newNode = new PNode(value, null, null, null, 0, 0);
        if (isEmpty()) 
            root = newNode;
        else 
        insertRec(root, newNode);
    }
    private void insertRec(PNode current, PNode newNode) {
        if (current.left == null) { //left subtree is empty
            current.left = newNode;
            newNode.parent = current;
            refreshCount(newNode);
            heapifyUpward(current);
        }
        else {
            if (current.right == null) { //right subtree is empty
                current.right = newNode;
                newNode.parent = current;
                refreshCount(newNode);
                heapifyUpward(current);
            }
            else {
                boolean cIAtRS = Math.abs( (current.lcount+1)-current.rcount ) <= 1; //can I add to left subtree? 
                if (cIAtRS) //current.lcount <= current.rcount) 
                    insertRec(current.left, newNode);
                else 
                    insertRec(current.right, newNode);
            }   
        }
    }
    //support methods for 2.2
    private void heapifyUpward(PNode root) {
        if (root == null) 
            return;
        if (root.left != null && root.left.key > root.key) 
            swap(root, root.left);
        if (root.right != null && root.right.key > root.key) 
            swap(root, root.right);
        heapifyUpward(root.parent);
    } 
    private void refreshCount(PNode current) {
        if (current.parent == null) 
            return;
        if (current.parent.left == current) //if current is a left child 
            current.parent.lcount++;
        if (current.parent.right == current) //if current is a right child 
            current.parent.rcount++;
        refreshCount(current.parent);
    }    

    //2.3) returns the maximum value in the queue and deletes it. If the queue is empty, throws a RuntimeException.
    public int extractMax() {
        if (isEmpty()) 
            throw new RuntimeException("Queue is empty");
        int result = root.key;
        int min = extractLeaf(root);
        if (root != null) {
            root.key = min;
            heapifyDownward(root);
        }
        return result;
    }
    //support methods for 2.3
    private int extractLeaf(PNode current) {
        if (current.left == null && current.right == null) {
            PNode parent = current.parent;
            current.parent = null;

            if (parent == null) 
                root = null;
            else  {
                if (parent.left == current) 
                    parent.left = null;
                else 
                    if (parent.right == current) 
                        parent.right = null;
                refreshCount(parent);
            }
            return current.key;

        }else {
            if (current.lcount >= current.rcount) 
                return extractLeaf(current.left);
            else 
                return extractLeaf(current.right);
        }
    }
    private void heapifyDownward(PNode current) {
        if (current == null) return;

        PNode nextNode = null;
        if (current.left != null && current.left.key > current.key) 
            nextNode = current.left;
        if (current.right != null && current.right.key > current.key)
            if (nextNode != null && current.right.key > nextNode.key) 
                nextNode = current.right;
        if (nextNode == null) 
            return;
        
        swap(current, nextNode);
        heapifyDownward(current);
    }    
    private void swap(PNode node1, PNode node2) {
        if (node1.parent == null) //if node1 is root
            root = node2;
        else 
            if (node2.parent == null) //if node2 is root
                root = node1;
        PNode temp = new PNode(0, null, null, null, 0, 0);
        replace(node1, temp);
        replace(node2, node1);
        replace(temp, node2);
    }

    // Places the from-Node at the toNode's position, maintains counts and links.
    private void replace(PNode fromNode, PNode toNode) {
        // Copying attributes
        toNode.parent = fromNode.parent;
        toNode.lcount = fromNode.lcount;
        toNode.rcount = fromNode.rcount;
        toNode.left = fromNode.left;
        toNode.right = fromNode.right;

        // Updating children
        if (toNode.left != null) 
            toNode.left.parent = toNode;
        if (toNode.right != null) 
            toNode.right.parent = toNode;

        // Changing parent pointer
        if (fromNode.parent != null) {
            if (fromNode.parent.left == fromNode) 
                fromNode.parent.left = toNode;
            else 
                if (fromNode.parent.right == fromNode) 
                    fromNode.parent.right = toNode;
        }

        fromNode.parent = null;
        fromNode.left = null;
        fromNode.right = null;
        fromNode.lcount = 0;
        fromNode.rcount = 0;
    }
    
    //support method for tests
    public void print() {
        print(root, 0);
    }
    private void print(PNode n, int depth) {
        if (n != null) {
            print(n.right, depth + 1);
            System.out.print(blanks(depth * 5));
            // print as integer of 6 digits,
            // then newline
            System.out.printf("%6d%n", n.key);
            print(n.left, depth + 1);
        }
    }
    private String blanks(int length) {
        StringBuilder outputBuffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            outputBuffer.append(" ");
        }
        return outputBuffer.toString();
    }

    //Node class
    public static class PNode {
        int key;
        PNode left, right, parent;
        int lcount, rcount;

        public PNode(int key, PNode left, PNode right, PNode parent, int lcount, int rcount) {
            this.key = key;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.lcount = lcount;
            this.rcount = rcount;
        }
    }
}

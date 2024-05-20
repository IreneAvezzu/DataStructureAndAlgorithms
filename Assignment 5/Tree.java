public class Tree {
    Node root;

    public Tree(Node root) {
        this.root = root;
    }

    //1.1) returns the number of nodes in the tree.
    public int size(){
        return sizeRec (root);
    }
    public int sizeRec(Node current){
        if (current == null) return 0;
        int left = sizeRec (current.left);
        int right = sizeRec (current.right);
        return left + right + 1;
    }

    //1.2) returns a tree that is a copy of the original tree, with new nodes, but the same structure and the same keys.
    public Tree copy(){
        if (root == null) return new Tree (null);
        Node newRoot = new Node(root.key, null, null);
        Tree newTree = new Tree (newRoot);
        newTree.copyRec(newTree.root, root);
        return newTree;
    }
    public void copyRec (Node current, Node original){
        if (original.left != null){
            current.left = new Node (original.left.key, null, null);
            copyRec(current.left, original.left);
        }
        if (original.right != null){
            current.right = new Node (original.right.key, null, null);
            copyRec(current.right, original.right);
        }
    }

    //1.3) returns a simple linked list containing all keys (also duplicates) occurring in the tree, in the order of inorder traversal.
    public List keys(){
        List keys = new List(null);
        keysRec(keys, root);
        return keys;
    }
    public void keysRec (List l, Node current){
        if (current != null) {
            keysRec(l, current.right);
            l.addAsHead(current.key);
            keysRec(l, current.left);
        }
    }

    //1.4) returns a simple linked list with the integers that occur at depth d in the tree, ordered as they appear from left to right in the tree; if there are no nodes at depth d, returns an empty list.
    public List keysAtDepth(int d){
        List keys = new List(null);
        keysAtDepthRec(d, 0, keys, root);
        return keys;
    }
    public void keysAtDepthRec(int d, int depth, List l, Node current){
        if (current == null) //we are at the end of the tree
            return;
        if (depth == d){ //we are at the right depth
            l.addAsHead(current.key);
            return;
        }
        keysAtDepthRec(d, depth+1, l, current.right);
        keysAtDepthRec(d, depth+1, l, current.left);
    }

    //1.5) returns the height of the tree
    public int height(){
        return heightRec(root);
    }
    public int heightRec(Node current){
        if (current == null) return -1;

        int left = heightRec(current.left);
        int right = heightRec(current.right);
        if (left>right)
            return left + 1;
        return right + 1;
    }

    //1.6) returns true if the tree is height-balanced, and false otherwise
    public boolean isHeightBalanced(){
        return isHeightBalancedRec(root);
    }
    public boolean isHeightBalancedRec(Node n){
        if (n == null) return true;

        int leftHeight = heightRec(n.left);
        int rightHeight = heightRec(n.right);

        int diff = Math.abs(leftHeight - rightHeight);
        
        if ( (isHeightBalancedRec(n.left) && isHeightBalancedRec(n.right)) && diff <= 1)
            return true;

        return false;
    }

    //1.7) eturns true if the tree is a binary search tree (BST), that is, if for each node n in the tree, it is the case that all keys in the left subtree of n are less or equal n.key, while all keys in the right subtree are greater or equal n.key
	boolean isBST(){
		return isBSTRec(root).isBST;
	}
	ResultValue isBSTRec (Node n){
		if (n == null){
			return new ResultValue (Integer.MAX_VALUE, Integer.MIN_VALUE, true); 
        }
        else{
            ResultValue left = isBSTRec(n.left);
            ResultValue right = isBSTRec(n.right);

            int min = left.min;
            int max = right.max;

            boolean isBST = (left.isBST && right.isBST) &&
                                left.max <= n.key && right.min >= n.key;

            if (n.key < min)
                min = n.key;
            if (n.key > max)
                max = n.key;

            ResultValue result = new ResultValue (min, max, isBST);
            
            return result;
		}
	}

    //support class
    class ResultValue {
        int min;
        int max;
        boolean isBST;

        ResultValue(int min, int max, boolean isBST){
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }
    }

    //1.8) it returns an integer that is maximal among all keys of nodes n with n.key <= x; if there is no such node, it returns the minimal integer that exists in Java (that is Integer.MIN VALUE)
    public int floor(int x){
        if (isBST())
            return floorRec(x, root);
        return -7; //if the tree is not a search tree any result is acceptable
    }
    public int floorRec(int x, Node current){
        //base case
        if (current == null) {
            return Integer.MIN_VALUE;
        }

        int max;
        if (current.key <= x){
            max = Math.max(current.key, floorRec(x, current.right));
        }
        else{
            max = floorRec(x, current.left);
        }
        return max;
    }


    //1.9) iterative version of 1.8
    public int floorIter(int x){
        Node current = root;
        int max = Integer.MIN_VALUE;
        while (current !=null){
            if (current.key <= x){
                max = Math.max(current.key, max);
                current = current.right;
            }
            else{
                current = current.left;
            }
        }

        return max;
    }

    //support method for tests
    public void print() {
        print(root, 0);
    }
    private void print(Node n, int depth) {
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
    public static class Node {
        int key;
        Node left, right;

        public Node(int key, Node left, Node right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }
}

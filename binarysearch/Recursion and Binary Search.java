import java.util.ArrayList;

/**
 * If you need a good balanced BST class, use {@code TreeSet<T>} in the
 * package {@code java.util}. The purpose of this class is just to demonstrate
 * recursion and binary search tree algorithms. Each object of this class is
 * an individual node of a binary search tree, and represents as the root node
 * the entire subtree that is hanging from it. Keys are hardcoded to be of 
 * type {@code int}.
 */

public class BST {

    private int key;
    private BST left, right;
    
    /**
     * Return the key stored in this node.
     * @return The key stored in this node.
     */
    public int getKey() { return key; }
    
    /**
     * The constructor to store the given key to this node.
     * @param key The key to store in this node.
     */
    public BST(int key) {
        this.key = key;
    }
    
    // For each BST method, we shall write a static and nonstatic version.
    
    /**
     * Check whether the given tree contains the given key.
     * @param root The root node of the tree.
     * @param key The key to search for in the tree.
     * @return {@code true} if the tree contains that key, {@code false} otherwise.
     */
    public static BST contains(BST root, int key) {
        if(root == null || root.key == key) { return root; }
        if(root.key < key) {
            return contains(root.right, key);
        }
        else {
            return contains(root.left, key);
        }
    }
    
    /**
     * Check whether this tree contains the given key.
     * @param key The key to search for in the tree.
     * @return {@code true} if this tree contains that key, {@code false} otherwise.
     */
    public BST contains(int key) {
        return contains(this, key);
    }
    
    /**
     * Find all keys in the given tree that lie within the given range, and collect
     * them into the given {@code ArrayList<Integer>} collection.
     * @param root The root node of the tree.
     * @param result The {@code ArrayList<Integer>} to store the keys in.
     * @param min The lower bound of the range.
     * @param max The upper bound of the range.
     */
    public static void rangeSearch(BST root, ArrayList<Integer> result, int min, int max) {
        if(root == null) { return; }
        if(root.key > min) {
            rangeSearch(root.left, result, min, max);
        }
        if(min <= root.key && root.key <= max) {
            result.add(root.key);
        }
        if(root.key < max) {
            rangeSearch(root.right, result, min, max);
        }
    }
    
    /**
     * Find all keys in this tree that lie within the given range, and collect
     * them into the given {@code ArrayList<Integer>} collection.
     * @param result The {@code ArrayList<Integer>} to store the keys in.
     * @param min The lower bound of the range.
     * @param max The upper bound of the range.
     */
    public void rangeSearch(ArrayList<Integer> result, int min, int max) {
        rangeSearch(this, result, min, max);
    }
    
    /**
     * Add the key to the given tree. If the key is already somewhere in the tree,
     * the method should not change anything.
     * @param root The root of the tree to add the key in.
     * @param key The key to be added.
     * @return Reference to the node that contains the added key, or {@code null}
     * if the key was already in the tree and nothing was added.
     */
    public static BST add(BST root, int key) {
        if(root == null) { return new BST(key); }
        if(root.key == key) { return null; }
        if(root.key < key) {
            if(root.right == null) {
                return root.right = new BST(key);
            }
            else { return add(root.right, key); }
        }
        else {
            if(root.left == null) {
                return root.left = new BST(key);
            }
            else { return add(root.left, key); }
        }
    }
    
    /**
     * Add the key to this tree. If the key is already somewhere in the tree,
     * the method should not change anything.
     * @param key The key to be added.
     * @return Reference to the node that contains the added key, or {@code null}
     * if the key was already in the tree and nothing was added.
     */
    public BST add(int key) {
        return add(this, key);
    }
    
    /**
     * Output the keys of the given tree in sorted order with a recursive tree walk.
     * @param root The root of the tree to output.
     */
    public static void treeWalk(BST root) {
        if(root == null) { return; }
        treeWalk(root.left);
        System.out.print(root.key + " ");
        treeWalk(root.right);
    }
    
    /**
     * Output the keys of this tree in sorted order with a recursive tree walk.
     */
    public void treeWalk() {
        treeWalk(this);
    }
    
    /**
     * Construct a BST that contains the elements of the given sorted array.
     * @param a The array of elements. Should be sorted.
     * @return The root node of the constructed binary search tree.
     */
    public static BST createTree(int[] a) {
        return createTree(a, 0, a.length - 1);
    }
    
    /**
     * Construct a BST that contains the elements of the given subarray of
     * the given sorted array.
     * @param a The array of elements. Should be sorted.
     * @param start The start index of the subarray.
     * @param end The end index of the subarray.
     * @return The root node of the constructed binary search tree.
     */
    public static BST createTree(int[] a, int start, int end) {
        if(start > end) { return null; }
        int mid = (start + end) / 2;
        BST root = new BST(a[mid]);
        root.left = createTree(a, start, mid-1);
        root.right = createTree(a, mid+1, end);
        return root;
    }
    
    // For demonstration and entertainment purposes.
    public static void main(String[] args) {
        int[] data = {1, 4, 5, 10, 12, 17, 19, 21, 29, 33, 37 };
        BST root = createTree(data);
        System.out.println("The tree constructed from array: ");
        root.treeWalk();
        System.out.println("\nThe same tree after adding some elements:");
        root.add(3);
        root.add(18);
        root.add(31);
        root.treeWalk();
        System.out.println("\nChecking the contains method: ");
        for(int i = 0; i < 50; i++) {
            if(root.contains(i) != null) { System.out.print(i + " "); }
        }
        ArrayList<Integer> al = new ArrayList<Integer>();
        System.out.println("\nChecking the rangeSearch method: ");
        root.rangeSearch(root, al, 11, 30);
        System.out.println(al);
        System.out.println("And we are all done!");
    }
}

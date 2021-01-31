package serie3;

public class NodeHandler {
    private static int count;

    // Inserts the nodes onto the BinaryTree
    public static <E extends Comparable<E>> Node<E> insert(Node<E> root, E e) {
        if (root == null) { // not found, insert
            root = new Node<E>();
            root.value = e;
        } else if (e.compareTo(root.value) <= 0)
            root.left = insert(root.left, e);
        else root.right = insert(root.right, e);
        return root;
    }



    public static Integer kSmallest(Node<Integer> root, int k){
        // Counts the nth smallest position its currently in
        count = 0;

        // Iterative calls the tree to see the kth smallest element(if it exists)
        return kSmallestRecursive(root, k);
    }
    public static Integer kSmallestRecursive(Node<Integer> root, int k) {
        Integer checker;

        if (root == null)
            return null;

        // Stores the value of the current node its in
        checker = kSmallestRecursive(root.left, k);

        // Found the kth smallest element
        if (checker != null)
            return checker;

        // Counts the elements its in
        count++;

        // Return the value when it has found the kth element
        if (count == k)
            return root.value;


        // Checks the right side of the root
        return kSmallestRecursive(root.right, k);

    }

    public static int countMultiple(Node<Integer> root, Integer k){
        // Counts the multiples of k
        int count = 0;
        if(root == null)
            return count;

        // Counts how many it exists on the left side
        count = countMultiple(root.left, k);
        // adds the right side to the count of the left side
        count += countMultiple(root.right, k);

        // Increments the count when it finds a multiple of k
        if(root.value % k == 0)
            count++;

        return count;

    }


    public static <E> boolean isBalanced(Node<E> root){
        if(root == null)
            return true;

        // Checks the Height of booth side
        int leftHeight = height(root.left), rightHeight = height(root.right);

        // Checks if booth sides are the same side (difference is 0) and then calls recursively
        //then rest of the nodes in the tree
        if(Math.abs(leftHeight - rightHeight) == 0 && isBalanced(root.left) && isBalanced(root.right))
            return true;

        return false;
    }

    private static <E> int height(Node<E> root){
        if(root == null)
            return 0;

        // Counts the root and the highest side of that root
        return 1 + Math.max(height(root.left), height(root.right));
    }
}

package serie3;

import java.util.concurrent.atomic.AtomicInteger;

public class NodeHandler {

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
        // Iterative calls the tree to see the kth smallest element(if it exists)
        AtomicInteger counter = new AtomicInteger(k);

        return kSmallestRecursive(root, counter);
    }

    public static Integer kSmallestRecursive(Node<Integer> root, AtomicInteger k) {
        if (root == null)
            return null;
        // Stores the value of the current node its in
        Integer ret = kSmallestRecursive(root.left, k);

        // Found the kth smallest element
        if (ret != null)
            return ret;

        // Counts the elements its in

        // Return the value when it has found the kth element
        if (k.decrementAndGet() == 0)
            return root.value;

        ret = kSmallestRecursive(root.right, k);
        // Checks the right side of the root
        return ret;

    }

    public static int countMultiple(Node<Integer> root, Integer k){
        // Counts the multiples of k
        int count = 0;
        if (root == null)
            return 0;

        // Counts how many it exists on the left side
        count = countMultiple(root.left, k);
        // adds the right side to the count of the left side
        count += countMultiple(root.right, k);

        // Increments the count when it finds a multiple of k
        if(root.value % k == 0)
            count++;

        return count;

    }


    public static <E> boolean isBalanced(Node<E> root) {
        if (root == null)
            return true;

        // Checks the Height of booth side
        int leftHeight = height(root.left), rightHeight = height(root.right);

        // Checks if booth sides are the same side (difference is 0) and then calls recursively
        //then rest of the nodes in the tree
        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right))
            return true;

        return false;

    }

    private static <E> int height(Node<E> root) {
        if (root == null)
            return 0;

        // Counts the root and the highest side of that root
        return 1 + Math.max(height(root.left), height(root.right));
    }


    public static <E extends Comparable<E>> Node<E> minimum(Node<E> root) {
        if (root.left == null) return root;
        else return minimum(root.left);
    }


    public static <E extends Comparable<E>> Node<E> delete(Node<E> root, E e) {
        if (root == null) return root;
        else {
            if (e.compareTo(root.value) < 0)
                root.left = delete(root.left, e);
            else if (e.compareTo(root.value) > 0)
                root.right = delete(root.right, e);
            else { // found, delete it
                if (root.left == null) root = root.right;
                else if (root.right == null) root = root.left;
                else {
                    Node<E> y = minimum(root.right);
                    root.value = y.value;
                    root.right = delete(root.right, y.value);
                }
            }
            return root;
        }
    }
}

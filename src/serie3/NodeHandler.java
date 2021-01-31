package serie3;

public class NodeHandler {
    private static int count;

    public static <E extends Comparable<E>> Node<E> insert(Node<E> root, E e) {
        if (root == null) { // not found, insert
            root = new Node<E>();
            root.value = e;
        } else if (e.compareTo(root.value) <= 0)
            root.left = insert(root.left, e);
        else root.right = insert(root.right, e);
        return root;
    }


    public static Integer kSmallest(Node<Integer> root, int k) {
        count = 0;
        return kSmallestRecursive(root, k);
    }

    public static Integer kSmallestRecursive(Node<Integer> root, int k) {
        Integer checker;

        if (root == null)
            return null;

        checker = kSmallestRecursive(root.left, k);

        if (checker != null)
            return checker;

        count++;

        if (count == k)
            return root.value;


        return kSmallestRecursive(root.right, k);

    }

    public static int countMultiple(Node<Integer> root, Integer k) {
        int count = 0;
        if (root == null)
            return count;

        count = countMultiple(root.left, k);
        count += countMultiple(root.right, k);

        if (root.value % k == 0)
            count++;

        return count;

    }


    public static <E> boolean isBalanced(Node<E> root) {
        if (root == null)
            return true;

        int leftHeight = height(root.left), rightHeight = height(root.right);

        if (Math.abs(leftHeight - rightHeight) <= 1 && isBalanced(root.left) && isBalanced(root.right))
            return true;

        return false;

    }

    private static <E> int height(Node<E> root) {
        if (root == null)
            return 0;

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

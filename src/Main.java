public class Main {
    public static void main(String[] args) {
        Node nodeTree1 = createNode1();
        Node nodeTree2 = createNode2();
        int k = 2;

        System.out.println("O nodeTree1 tem " + NodeHandler.countMultiple(nodeTree1, k) + " multiplos de "+ k);
        System.out.println("O nodeTree2 tem " + NodeHandler.countMultiple(nodeTree2, k) + " multiplos de "+ k);

        System.out.println("\nO nodeTree1 " + k + " numero mais pequeno é " + NodeHandler.kSmallest(nodeTree1, k));
        System.out.println("O nodeTree2 " + k + " numero mais pequeno é " + NodeHandler.kSmallest(nodeTree2, k));

        System.out.println("\nA a arvore nodeTree1 é: " + ((NodeHandler.isBalanced(nodeTree1)) ? "Balanceada" : "Nao Balanceada"));
        System.out.println("A a arvore nodeTree2 é: " + ((NodeHandler.isBalanced(nodeTree2)) ? "Balanceada" : "Nao Balanceada"));

    }


    public static Node createNode1(){
        Node nodeTree = null;

        nodeTree = NodeHandler.insert(nodeTree, 10);
        nodeTree = NodeHandler.insert(nodeTree, 6);
        nodeTree = NodeHandler.insert(nodeTree, 4);
        nodeTree = NodeHandler.insert(nodeTree, 3);
        nodeTree = NodeHandler.insert(nodeTree, 5);
        nodeTree = NodeHandler.insert(nodeTree, 8);
        nodeTree = NodeHandler.insert(nodeTree, 7);
        nodeTree = NodeHandler.insert(nodeTree, 9);
        nodeTree = NodeHandler.insert(nodeTree, 18);
        nodeTree = NodeHandler.insert(nodeTree, 14);
        nodeTree = NodeHandler.insert(nodeTree, 12);
        nodeTree = NodeHandler.insert(nodeTree, 15);
        nodeTree = NodeHandler.insert(nodeTree, 20);
        nodeTree = NodeHandler.insert(nodeTree, 19);
        nodeTree = NodeHandler.insert(nodeTree, 32);

        return nodeTree;
    }

    public static Node createNode2(){
        Node nodeTree = null;

        nodeTree = NodeHandler.insert(nodeTree, 9);
        nodeTree = NodeHandler.insert(nodeTree, 4);
        nodeTree = NodeHandler.insert(nodeTree, 1);
        nodeTree = NodeHandler.insert(nodeTree, 8);
        nodeTree = NodeHandler.insert(nodeTree, 5);
        nodeTree = NodeHandler.insert(nodeTree, 6);
        nodeTree = NodeHandler.insert(nodeTree, 7);
        nodeTree = NodeHandler.insert(nodeTree, 0);
        nodeTree = NodeHandler.insert(nodeTree, 2);
        nodeTree = NodeHandler.insert(nodeTree, 3);

        return nodeTree;
    }
}

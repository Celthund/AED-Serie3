package serie3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class NodeHandlerTest {
    Node<Integer> nodeTree1 = createNode1();
    Node<Integer> nodeTree2 = createNode2();

    @Test
    public void countMultiple_withNodeTreeWithK2(){
        assertEquals(9, NodeHandler.countMultiple(nodeTree1, 2));
    }

    @Test
    public void countMultiple_withNodeTreeWithK6(){
        assertEquals(3, NodeHandler.countMultiple(nodeTree1, 6));
    }

    @Test
    public void countMultiple_withNodeTreeWithK4(){
        assertEquals(3, NodeHandler.countMultiple(nodeTree2, 4));
    }

    @Test
    public void countMultiple_withNodeTreeWithK7(){
        assertEquals(2, NodeHandler.countMultiple(nodeTree2, 7));
    }

    @Test
    public void countMultiple_withEmptySequences(){
        assertEquals(0, NodeHandler.countMultiple(null, 2));
    }

    @Test
    public void kSmallest_withEmptySequences(){
        assertEquals(null, NodeHandler.kSmallest(null, 2));
    }

    @Test
    public void kSmallest_withNodeTreeWithK2(){
        assertEquals(4, NodeHandler.kSmallest(nodeTree1, 2));
    }

    @Test
    public void kSmallest_withNodeTreeWithK5(){
        assertEquals(7, NodeHandler.kSmallest(nodeTree1, 5));
    }

    @Test
    public void kSmallest_withNodeTreeWithKBiggerThanTreeSize(){
        assertEquals(null, NodeHandler.kSmallest(nodeTree2, 20));
    }

    @Test
    public void kSmallest_withNodeTreeWithK7(){
        assertEquals(6, NodeHandler.kSmallest(nodeTree2, 7));
    }

    @Test
    public void isBalanced_withEmptySequences(){
        assertEquals(true, NodeHandler.isBalanced(null));
    }

    @Test
    public void isBalanced_withBalancedTree(){
        assertEquals(true, NodeHandler.isBalanced(nodeTree1));
    }

    @Test
    public void isBalanced_withUnbalancedTree(){
        assertEquals(false, NodeHandler.isBalanced(nodeTree2));
    }


    public static Node<Integer> createNode2(){
        Node<Integer> nodeTree = null;

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

    public static Node<Integer> createNode1(){
        Node<Integer> nodeTree = null;

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
}

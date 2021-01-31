package serie3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class NodeHandlerTest {
    Node nodeTree1 = createNode1();
    Node nodeTree2 = createNode2();

    @Test
    public void filterByMap_withEmptySequences(){
        assertEquals(NodeHandler.countMultiple(nodeTree1, 2), 9);
    }
    @Test
    public void filterByMap_withRandomElements() {
        List<Integer> list = Arrays.asList(1, 18);
        Random rand = new Random();
        int randomElements = list.get(rand.nextInt(list.size()));
        assertEquals(NodeHandler.countMultiple(nodeTree1, randomElements), randomElements);
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
}

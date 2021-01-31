package serie3;
import java.util.HashMap;

public class Graph {
    public HashMap<Integer, Vertex> vertices = new HashMap<>();

    public void addVertex(Integer id, Vertex vertexA) {
        vertices.put(id, vertexA);
    }

    public Vertex getVertex(Integer id){
        return vertices.containsKey(id) ? vertices.get(id) : null;
    }
}

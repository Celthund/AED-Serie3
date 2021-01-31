package serie3;
import java.util.HashMap;

public class Graph {
    // Stores all the vertices of the ph via the id key
    public HashMap<Integer, Vertex> vertices = new HashMap<>();

    // Adds the vertices to the HashMap
    public void addVertex(Integer id, Vertex vertexA) {
        vertices.put(id, vertexA);
    }

    // Gets the vertices from the HashMap via the id
    public Vertex getVertex(Integer id){
        return vertices.containsKey(id) ? vertices.get(id) : null;
    }
}

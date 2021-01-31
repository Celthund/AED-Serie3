package serie3;

import java.util.Comparator;
import java.util.HashMap;

public class Vertex {
    static class VertexComparator implements Comparator<Vertex> {
        public int compare(Vertex s1, Vertex s2) {
            return s1.smallestDistance - s2.smallestDistance;
        }
    }
    public enum Colors {
        WHITE, // Not visited
        GRAY,  // Visited but all the adjacent vertex where not visited
        BLACK; // All adjacent vertex where visited
    }

    // Vertex with the smallest distance so far between two set vertices
    public Vertex predecessor;

    // Id is the vertex identification
    // smallestDistance has the smallestDistance from a set vertex to this vertex
    // x and y are the coordinates
    public int id, smallestDistance, x, y;

    // So we know if we treated or visited the vertex so far
    public Colors color;

    // Stores all the adjacent vertices
    public HashMap<Vertex, Integer> adjacentVertex = new HashMap<>();

    public Vertex(int id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    // All the vertices have an unique id so we use it has an hasCode
    @Override
    public int hashCode() {
        return id;
    }

    // If another vertex has the same Id it will be treated has the same vertex by the HashMap
    @Override
    public boolean equals(Object obj) {
        return ((Vertex)obj).id == this.id;
    }

    // Connects a vertex to this one by add it to the HashMap(The key of the HashMap is the distance
    //between the two vertices)
    public void addDestination(Vertex destination, int distance){
        if(adjacentVertex.containsKey(destination)) {
            int aux = adjacentVertex.get(destination);
            if (distance >= aux)
                return;
        }
        adjacentVertex.put(destination, distance);

    }


}

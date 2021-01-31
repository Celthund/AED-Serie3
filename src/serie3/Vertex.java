package serie3;

import java.util.HashMap;
import java.util.LinkedList;

public class Vertex {
    public enum Colors {
        WHITE, // Not visited
        GRAY,  // Visited but all the adjacent vertex where not visited
        BLACK; // All adjacent vertex where visited
    }

    public Vertex predecessor;
    public int id, smallestDistance = Integer.MAX_VALUE, x, y;
    public Colors color = Colors.WHITE;
    public HashMap<Vertex, Integer> adjacentVertex = new HashMap<>();

    public Vertex(int id){
        this.id = id;
    }

    public Vertex(int id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return ((Vertex)obj).id == this.id;
    }

    public void addDestination(Vertex destination, int distance){
        if(adjacentVertex.containsKey(destination)) {
            int aux = adjacentVertex.get(destination);
            if (distance >= aux)
                return;
        }

        adjacentVertex.put(destination, distance);

    }


}

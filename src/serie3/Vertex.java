package serie3;

import java.util.HashMap;

public class Vertex {
    Vertex predecessor;
    int id, distance, color, x, y;
    HashMap<Vertex, Integer> children;
}

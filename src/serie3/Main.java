package serie3;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static java.lang.System.nanoTime;

public class Main {
    private static int dim;

    public static void main(String[] args) {
        double start_time = nanoTime() * 1e-6;
        // If args.length is less than 1 it means that is empty so returns a warning and exits the program
        if (args.length < 1) {
            System.out.println("Missing operation.");
            System.exit(0);
        }

        // Checks the size of the args if is less than 3 it means all the instruction doesnt fit so it returns
        //an error and exits the program
        if (args.length < 2) {
            System.out.println("Missing input files.");
            System.exit(0);
        }

        // It will get the files names from the correspondent args position
        String file1 = args[0];
        String file2 = args[1];


        //Stores the file1 content
        Scanner scanner1 = null;
        try {
            //Passes the content of the file1 to the scanner1
            scanner1 = new Scanner(new FileReader(file1));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File missing: " + file1);
            System.exit(-1);
        }

        //Stores the file2 content
        Scanner scanner2 = null;

        try {
            //Passes the content of the file2 to the scanner1
            scanner2 = new Scanner(new FileReader(file2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File missing: " + file2);
            System.exit(-1);
        }

        Graph graph = createGraph(scanner2);
        connectVertices(scanner1, graph);

        while(true) {
            System.out.println("Please write the Operation you want (path, e): ");
            Scanner in = new Scanner(System.in);
            String operation = in.next();
            int id1 = 0, id2 = 0;
            if (operation.equals("path")) {
                boolean validInputs = false;
                while(!validInputs) {
                    System.out.println("Write id1 number: ");
                    id1 = in.nextInt();
                    System.out.println("Write id2 number: ");
                    id2 = in.nextInt();


                    if(id1 > dim || id2 > dim)
                        System.out.println("Invalid Inputs");
                    else
                        validInputs = true;
                }

                Vertex start = graph.getVertex(id1);
                Vertex curr = null;
                Vertex end = graph.getVertex(id2);

                QueueArray<Vertex> bfs = new QueueArray<>(dim);

                start.smallestDistance = 0;
                start.color = Vertex.Colors.GRAY;
                bfs.enqueue(start);

                while (!bfs.isEmpty() || end.color != Vertex.Colors.BLACK) {
                    curr = bfs.dequeue();

                    for (Map.Entry<Vertex, Integer> entry : curr.adjacentVertex.entrySet()) {
                        Vertex adj = entry.getKey();

                        if (adj.color == Vertex.Colors.WHITE) {
                            adj.color = Vertex.Colors.GRAY;
                            bfs.enqueue(adj);
                            adj.smallestDistance = curr.smallestDistance + entry.getValue();
                            adj.predecessor = curr;
                        } else if (adj.color == Vertex.Colors.GRAY) {
                            if (adj.smallestDistance >
                                    (curr.smallestDistance + entry.getValue())) {
                                adj.smallestDistance = curr.smallestDistance + entry.getValue();
                                adj.predecessor = curr;
                            }
                        }


                    }

                    curr.color = Vertex.Colors.BLACK;
                }

                if (end.predecessor == null)
                    System.out.println("No Paths!");
                else {
                    Vertex currentVertex = end;

                    String res = "";
                    LinkedList path = new LinkedList();

                    while (currentVertex != null) {
                        path.add(currentVertex);
                        currentVertex = currentVertex.predecessor;
                    }

                    Collections.reverse(path);
                    Iterator it =  path.iterator();
                    System.out.printf("Distancia total é: %-6s\n", Integer.toString(end.smallestDistance));
                    while(it.hasNext()) {
                        currentVertex = (Vertex)it.next();
                        System.out.printf("Id: %-6s\t", currentVertex.id);
                        System.out.printf("X: %-6s\t", currentVertex.x);
                        System.out.printf("Y: %-6s\t\t", currentVertex.y);
                        System.out.printf("Path total cost: %-6s", currentVertex.smallestDistance);
                        System.out.println();
                    }
                }
            }
            else if (operation.equals("e"))
                break;
            else
                System.out.println("Operacao Invalida");
        }

    }

    public static Graph createGraph(Scanner scanner) {
        String[] curr;
        Graph graph = new Graph();

        while (scanner.hasNextLine()) {

            // Separates the lines every time it encounters a space character
            curr = scanner.nextLine().split(" ");

            if(curr[0].equals("p")){
                dim = Integer.parseInt(curr[4]);
            }

            else if (!curr[0].equals("v")) {
                continue;
            }
            else {
                Vertex vertex = new Vertex(Integer.parseInt(curr[1]), Integer.parseInt(curr[2]), Integer.parseInt(curr[3]));
                graph.addVertex(vertex.id, vertex);
            }
        }
        return graph;
    }

    private static void connectVertices(Scanner scanner, Graph graph) {
        String[] curr;

        while (scanner.hasNextLine()) {

            // Separates the lines every time it encounters a space character
            curr = scanner.nextLine().split(" ");

            if (!curr[0].equals("a")) {
                continue;
            }

            Vertex start = graph.getVertex(Integer.parseInt(curr[1]));
            Vertex finish = graph.getVertex(Integer.parseInt(curr[2]));
            int distance = Integer.parseInt(curr[3]);
            start.addDestination(finish, distance);

        }
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

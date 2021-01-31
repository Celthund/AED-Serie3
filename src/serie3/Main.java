package serie3;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static java.lang.System.nanoTime;

public class Main {
    // Variable to store the vertex numbers
    private static int dim;

    public static void main(String[] args) {
        double start_time = nanoTime() * 1e-6;
        // If args.length is less than 1 it means that is empty so returns a warning and exits the program
        if (args.length < 1) {
            System.out.println("Missing operation.");
            System.exit(0);
        }

        // Checks the size of the args if is less than 2 it means all the instruction doesnt fit so it returns
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
            //Passes the content of the file2 to the scanner2
            scanner2 = new Scanner(new FileReader(file2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File missing: " + file2);
            System.exit(-1);
        }

        // Creates the graph by reading the '.co'
        Graph graph = createGraph(scanner2);

        // Connects the vertex of the graph previously created
        connectVertices(scanner1, graph);

        while(true) {
            // Set of intructions to ask the user what set of operations he intends to execute
            System.out.println("Please write the Operation you want (path, e): ");
            Scanner in = new Scanner(System.in);
            String operation = in.next();
            int id1 = 0, id2 = 0;
            if (operation.equals("path")) {
                // Leaves the while cycle when the user inputs valid id's
                boolean validInputs = false;

                // Checks if one of the ids is greater than id highest node id in the file that
                //is equal to the dimension of the nodes(vertices) in the file
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


                // Gets the starter vertex via the id the user inputted in the id1
                Vertex start = graph.getVertex(id1);

                // Variable that will run trough the graph via adjacent vertex and store
                //all the needed information
                Vertex curr = null;

                // Same for the end vertex
                Vertex end = graph.getVertex(id2);

                // FIFO queue to be used on the BFS search
                QueueArray<Vertex> bfs = new QueueArray<>(dim);

                // Cycle to reset all the vertex by putting all the colors white, the distances
                //smallestDistances to the Integer.MAX_VALUE (infinite), and the predecessor
                //to null
                for (Map.Entry<Integer, Vertex> entry : graph.vertices.entrySet()) {
                    Vertex cleaner = entry.getValue();
                    cleaner.color = Vertex.Colors.WHITE;
                    cleaner.smallestDistance = Integer.MAX_VALUE;
                    cleaner.predecessor = null;
                }

                // Begin of the Dijkstra
                start.smallestDistance = 0;
                start.color = Vertex.Colors.GRAY;

                // Puts the start variable in the FIFO queue
                bfs.enqueue(start);

                // Runs from the start variable to the end variable(if the path exists)
                while (!bfs.isEmpty() && end.color != Vertex.Colors.BLACK) {

                    // Gets the last Vertex inserted from the queue
                    curr = bfs.dequeue();

                    // Cycle that gets all the vertex adjacents to the current vertex
                    for (Map.Entry<Vertex, Integer> entry : curr.adjacentVertex.entrySet()) {
                        Vertex adj = entry.getKey();

                        // If the adjacent vertex wasnt visited yet (Color is white)
                        //it will mark its color as gray, the distance is just the distance
                        //cover at that point, and the predecessor is the 'curr' vertex
                        if (adj.color == Vertex.Colors.WHITE) {
                            adj.color = Vertex.Colors.GRAY;
                            bfs.enqueue(adj);
                            adj.smallestDistance = curr.smallestDistance + entry.getValue();
                            adj.predecessor = curr;
                        }

                        // If its Gray its already visited so it will compare the "old" path
                        //to the current one
                        else if (adj.color == Vertex.Colors.GRAY) {

                            // If the smallestDistance stored in the array is smaller than the current distance it has ran
                            //so far than it will store the new distance
                            if (adj.smallestDistance >
                                    (curr.smallestDistance + entry.getValue())) {
                                adj.smallestDistance = curr.smallestDistance + entry.getValue();
                                adj.predecessor = curr;
                            }
                        }


                    }

                    // Color is Black to warns that the curr vertex its already treated
                    curr.color = Vertex.Colors.BLACK;
                }

                // If the "end.predecessor" is null, it means that the start couldn't reach the end
                //vertex
                if (end.predecessor == null)
                    System.out.println("No Paths!");
                else {

                    Vertex currentVertex = end;

                    // List that stores the path created from the start vertex to the end variable
                    LinkedList path = new LinkedList();

                    // Adds the vertices that form the path to the list
                    while (currentVertex != null) {
                        path.add(currentVertex);
                        currentVertex = currentVertex.predecessor;
                    }

                    // Inverts so it can start from the "starting" vertex
                    Collections.reverse(path);
                    Iterator it =  path.iterator();

                    // Prints all the vertex by iterating the list
                    System.out.printf("Distancia total é: %-6s\n", Integer.toString(end.smallestDistance));
                    while(it.hasNext()) {
                        currentVertex = (Vertex)it.next();
                        System.out.printf("Id: %-6s --", currentVertex.id);
                        System.out.printf("X: %-6s --", currentVertex.x);
                        System.out.printf("Y: %-6s --", currentVertex.y);
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
        // String array that will store every word in the position of the array
        String[] curr;

        Graph graph = new Graph();

        while (scanner.hasNextLine()) {

            // Separates the lines every time it encounters a space character
            curr = scanner.nextLine().split(" ");

            // Checks for a p in the file, because the number of vertex is the fourth word of that line
            if(curr[0].equals("p")){
                dim = Integer.parseInt(curr[4]);
            }

            // When 'v' it's the first letter of the line it means that we have
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

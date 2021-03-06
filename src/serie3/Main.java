package serie3;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

import static java.lang.System.nanoTime;

public class Main {
    // Variable to store the vertex numbers
    private static int dim;

    public static void main(String[] args) {
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

        double start_time = nanoTime() * 1e-6;

        // Creates the graph by reading the '.co'
        Graph graph = createGraph(scanner2);
        // Connects the vertex of the graph previously created
        connectVertices(scanner1, graph);

        double end_time = nanoTime() * 1e-6;
        System.out.printf("Time loading the graph = %.2f\n", end_time - start_time);

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

                start_time = nanoTime() * 1e-6;
                // Gets the starter vertex via the id the user inputted in the id1
                Vertex start = graph.getVertex(id1);

                // Variable that will run trough the graph via adjacent vertex and store
                //all the needed information
                Vertex curr = null;

                // Same for the end vertex
                Vertex end = graph.getVertex(id2);



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

                // FIFO queue to be used on the BFS search
//                QueueArray<Vertex> bfs = new QueueArray<>(dim);
                Heap<Vertex> bfs = new Heap<>(new Vertex.VertexComparator());
                // Puts the start variable in the FIFO queue
                bfs.add(start);

                // Runs from the start variable to the end variable(if the path exists)
                while (!bfs.isEmpty() && !(curr == end)) {

                    // Gets the last Vertex inserted from the queue
                    curr = bfs.poll();

                    // Cycle that gets all the vertex adjacents to the current vertex
                    for (Map.Entry<Vertex, Integer> entry : curr.adjacentVertex.entrySet()) {
                        Vertex adj = entry.getKey();

                        // If the adjacent vertex wasnt visited yet (Color is white)
                        //it will mark its color as gray, the distance is just the distance
                        //cover at that point, and the predecessor is the 'curr' vertex
                        if (adj.color == Vertex.Colors.WHITE) {
                            adj.color = Vertex.Colors.GRAY;
                            adj.smallestDistance = curr.smallestDistance + entry.getValue();
                            adj.predecessor = curr;
                            bfs.add(adj);
                        }

                        // If its Gray its already visited so it will compare the "old" path
                        //to the current one
                        else if (adj.color == Vertex.Colors.GRAY) {

                            // If the smallestDistance stored in the array is smaller than the current distance it has ran
                            //so far than it will store the new distance
                            if (adj.smallestDistance > (curr.smallestDistance + entry.getValue())) {
                                adj.smallestDistance = curr.smallestDistance + entry.getValue();
                                adj.predecessor = curr;
                                bfs.heapify();
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
                    System.out.printf("Distancia total ??: %-6s\n", Integer.toString(end.smallestDistance));
                    while(it.hasNext()) {
                        currentVertex = (Vertex)it.next();
                        System.out.printf("Id: %-6s --", currentVertex.id);
                        System.out.printf("X: %-6s --", currentVertex.x);
                        System.out.printf("Y: %-6s --", currentVertex.y);
                        System.out.printf("Path total cost: %-6s", currentVertex.smallestDistance);
                        System.out.println();
                    }
                }

                end_time = nanoTime() * 1e-6;
                System.out.printf("Time calculating the shortest path = %.2f\n", end_time - start_time);
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

            // When 'v' it's the first letter of the line it means that we are in a line with vertex
            else if (!curr[0].equals("v")) {
                continue;
            }

            // We add that vertex to the graph, the id is always on the second position(1) the x on the third
            //position(2) and the y on the third position(4)
            else {
                Vertex vertex = new Vertex(Integer.parseInt(curr[1]), Integer.parseInt(curr[2]), Integer.parseInt(curr[3]));
                graph.addVertex(vertex.id, vertex);
            }
        }

        // Returns the graph to be used on the search
        return graph;
    }

    private static void connectVertices(Scanner scanner, Graph graph) {
        String[] curr;

        while (scanner.hasNextLine()) {

            // Separates the lines every time it encounters a space character
            curr = scanner.nextLine().split(" ");

            // While it didn't find the letter 'a' it means the line doesn't contain information
            //about the vertex
            if (!curr[0].equals("a")) {
                continue;
            }

            // Connects the start vertex (id is in the position 1 of the line)
            //to the end vertex (id is in the position 2 of the line)
            //the distance between the 2 vertice is in the position 3 of the line

            // (The Position is seperated by spaces as for the curr array)
            Vertex start = graph.getVertex(Integer.parseInt(curr[1]));
            Vertex finish = graph.getVertex(Integer.parseInt(curr[2]));
            int distance = Integer.parseInt(curr[3]);
            start.addDestination(finish, distance);
        }
    }
}

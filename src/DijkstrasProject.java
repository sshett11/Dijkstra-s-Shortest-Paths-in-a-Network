/**
 * SURAJ MAHESH SHETTY
 * 801021051
 * ALGORITHM AND DATA STRUCTURES
 * ITCS - 6114
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DijkstrasProject {

    public static void main(String[] args) {

        // Graph to be used for this project
        Graph graph = new Graph();

        try {

            // Create Graph using the File Passed
            createGraph(args[0], graph);

            // Start Looking for queries
            Scanner scanner = new Scanner(System.in);
            boolean quit = false;

            while (!quit) {
                switch (scanner.next()) {
                    case "addedge":
                        addEdge(scanner, graph);
                        break;
                    case "deleteedge":
                        deleteEdge(scanner, graph);
                        break;
                    case "edgedown":
                        markEdgeDown(scanner, graph);
                        break;
                    case "edgeup":
                        markEdgeUp(scanner, graph);
                        break;
                    case "vertexdown":
                        markVertexDown(scanner, graph);
                        break;
                    case "vertexup":
                        markVertexUp(scanner, graph);
                        break;
                    case "path":
                        printShortestPath(scanner, graph);
                        break;
                    case "print":
                        graph.printGraph();
                        break;
                    case "reachable":
                        graph.printReachableVertices();
                        break;
                    case "quit":
                        quit = true;
                        break;
                }
            }

            // Close Scanner
            scanner.close();

        } catch (IOException e) {
            System.err.println("File " + args[0] + " Not Found. End of Program");
        }
    }

    /**
     * Create graph by reading file
     *
     * @param fileName, File containing Network contents
     * @param graph, The graph to populate
     * @throws IOException
     */

    private static void createGraph(String fileName, Graph graph) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            String line = null, lineFragments[];
            while ((line = bufferedReader.readLine()) != null) {
                lineFragments = line.split(" ");
                graph.addEdge(lineFragments[0], lineFragments[1], Float.valueOf(lineFragments[2]));
                graph.addEdge(lineFragments[1], lineFragments[0], Float.valueOf(lineFragments[2]));
            }

            bufferedReader.close();
        }
        fileReader.close();
    }

    /**
     * @param scanner, Scanner for reading input stream
     * @param graph, The graph to which this edge should be added
     */

    private static void addEdge(Scanner scanner, Graph graph) {
        String from = scanner.next();
        String to = scanner.next();
        Float time = Float.valueOf(scanner.next());
        graph.addEdge(from, to, time);
    }

    /**
     * @param scanner, Scanner for reading input stream
     * @param graph, The graph from which this edge should be deleted
     */

    private static void deleteEdge(Scanner scanner, Graph graph) {
        String from = scanner.next();
        String to = scanner.next();
        graph.deleteEdge(from, to);
    }

    /**
     * @param scanner, Scanner for reading input stream
     * @param graph, The graph from which this edge should be marked Down
     */

    private static void markEdgeDown(Scanner scanner, Graph graph) {
        String from = scanner.next();
        String to = scanner.next();
        graph.markEdgeDown(from, to);
    }

    /**
     * @param scanner, Scanner for reading input stream
     * @param graph, The graph from which this edge should be marked Up
     */

    private static void markEdgeUp(Scanner scanner, Graph graph) {
        String from = scanner.next();
        String to = scanner.next();
        graph.markEdgeUp(from, to);
    }

    /**
     * @param scanner, Scanner for reading input stream
     * @param graph, The graph from which this vertex should be marked Down
     */

    private static void markVertexDown(Scanner scanner, Graph graph) {
        String vertexName = scanner.next();
        graph.markVertexDown(vertexName);
    }

    /**
     * @param scanner, Scanner for reading input stream
     * @param graph, The graph from which this vertex should be marked Up
     */

    private static void markVertexUp(Scanner scanner, Graph graph) {
        String vertexName = scanner.next();
        graph.markVertexUp(vertexName);
    }

    /**
     * @param scanner, Scanner for reading input stream
     * @param graph, The graph in which this shortest path should be found
     */

    private static void printShortestPath(Scanner scanner, Graph graph) {
        String from = scanner.next();
        String to = scanner.next();
        graph.printShortestPath(from, to);
    }
}

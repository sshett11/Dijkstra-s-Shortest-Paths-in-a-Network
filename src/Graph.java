/**
   * SURAJ MAHESH SHETTY
   * 801021051
   * ALGORITHM AND DATA STRUCTURES
   * ITCS - 6114
   */

import java.util.*;
import java.util.stream.Collectors;

public class Graph {

  // Creating a map for faster lookup using Vertex Name
  private Map<String, Vertex> nameToVerticesMap;

  // Maintain the result of Dijkstra's algorithm & Reachable algorithm, until graph changes
  private Map<Vertex, Map<Vertex, List<Vertex>>> dijkstrasResult;
  private Map<Vertex, SortedSet<Vertex>> reachableVerticesInOrder;

  public Graph() {
    nameToVerticesMap = new TreeMap<>();
    dijkstrasResult = new HashMap<>();
    reachableVerticesInOrder = new TreeMap<>();
  }

  private void setGraphModified() {
    dijkstrasResult.clear();
    reachableVerticesInOrder.clear();
  }

  private Vertex getVertexFromName(String name) {
    return nameToVerticesMap.get(name);
  }

  /**
   * @param from, Source Vertex of Edge
   * @param to, Target Vertex of Edge
   * @param time, Transmission time of Edge
   */

  public void addEdge(String from, String to, Float time) {
    if (!Objects.equals(from, to)) {
      if (!nameToVerticesMap.containsKey(from)) {
        nameToVerticesMap.put(from, new Vertex(from));
      }

      if (!nameToVerticesMap.containsKey(to)) {
        nameToVerticesMap.put(to, new Vertex(to));
      }

      Vertex fromVertex = nameToVerticesMap.get(from);
      Vertex toVertex = nameToVerticesMap.get(to);
      addEdge(fromVertex, toVertex, time);
    }
  }

  /**
   * @param from, Source Vertex of Edge
   * @param to, Target Vertex of Edge
   * @param time, Transmission time of Edge
   */

  private void addEdge(Vertex from, Vertex to, Float time) {
    if (from == null || to == null) {
      return;
    }
    from.addEdge(to, time);
    setGraphModified();
  }

  /**
   * @param from, Source Vertex of Edge
   * @param to, Target Vertex of Edge
   */

  public void deleteEdge(String from, String to) {
    deleteEdge(nameToVerticesMap.get(from), nameToVerticesMap.get(to));
  }

  /**
   * @param from, Source Vertex of Edge
   * @param to, Target Vertex of Edge
   */

  private void deleteEdge(Vertex from, Vertex to) {
    if (from == null || to == null) {
      return;
    }
    from.deleteEdge(to);
    setGraphModified();
  }

  /**
   * @param from, Source Vertex of Edge
   * @param to, Target Vertex of Edge
   */

  public void markEdgeDown(String from, String to) {
    markEdgeDown(nameToVerticesMap.get(from), nameToVerticesMap.get(to));
  }

  /**
   * @param from, Source Vertex of Edge
   * @param to, Target Vertex of Edge
   */

  private void markEdgeDown(Vertex from, Vertex to) {
    if (from == null || to == null) {
      return;
    }
    from.markEdgeDown(to);
    setGraphModified();
  }

  /**
   * @param from, Source Vertex of Edge
   * @param to, Target Vertex of Edge
   */

  public void markEdgeUp(String from, String to) {
    markEdgeUp(nameToVerticesMap.get(from), nameToVerticesMap.get(to));
  }

  /**
   * @param from, Source Vertex of Edge
   * @param to, Target Vertex of Edge
   */

  private void markEdgeUp(Vertex from, Vertex to) {
    if (from == null || to == null) {
      return;
    }
    from.markEdgeUp(to);
    setGraphModified();
  }

  /** @param vertexName, Vertex to be marked DOWN */
  public void markVertexDown(String vertexName) {
    markVertexDown(nameToVerticesMap.get(vertexName));
  }

  /** @param v, Vertex to be marked DOWN */
  private void markVertexDown(Vertex v) {
    if (v != null) {
      v.setIsUp(false);
      setGraphModified();
    }
  }

  /** @param vertexName, Vertex to be marked UP */
  public void markVertexUp(String vertexName) {
    markVertexUp(nameToVerticesMap.get(vertexName));
  }

  /** @param v, Vertex to be marked UP */
  private void markVertexUp(Vertex v) {
    if (v != null) {
      v.setIsUp(true);
      setGraphModified();
    }
  }

  /**
   * @param from, Source Vertex
   * @param to, Target Vertex
   */

  public void printShortestPath(String from, String to) {
    printShortestPath(nameToVerticesMap.get(from), nameToVerticesMap.get(to));
  }

  /**
   * This function uses Dijkstra's algorithm to find the shortest path, and prints it on console
   *
   * @param from, Source Vertex
   * @param to, Target Vertex
   */

  private void printShortestPath(Vertex from, Vertex to) {

    if (from == null || to == null) {
      return;
    }

    // If the current node has never been calculated, apply dijkstra's algorithm on it
    if (!dijkstrasResult.containsKey(from)) {
      calculateDijkstras(from);
    }

    // Print Shortest Path
    Float totalTime = 0.0f;
    Vertex v1 = from;
    Iterator<Vertex> pathIterator = dijkstrasResult.get(from).get(to).iterator();
    while (pathIterator.hasNext()) {
      System.out.print(v1.getName() + " ");
      Vertex v2 = pathIterator.next();
      totalTime += v1.getOutgoingEdge(v2).getTime();
      v1 = v2;
    }
    totalTime += v1.getOutgoingEdge(to).getTime();
    System.out.println(v1.getName() + " " + to.getName() + " " + totalTime);
  }

  /**
   * Function to apply Dijkstras Algorithm
   *
   * @param from, The source vertex for the algorithm
   */

  private void calculateDijkstras(Vertex from) {

    // Shortest Time Map, contains the shortest time to reach every vertex from source Vertex
    Map<Vertex, Float> minTimeMap = toInitialTimeMap(from);

    // Shortest Path Map, contains the shortest Path to reach every vertex from source Vertex
    Map<Vertex, List<Vertex>> shortestPathMap =
        minTimeMap.keySet().stream().collect(Collectors.toMap(e -> e, e -> new ArrayList<>()));

    // Construct Priority Queue, with the comparator being Shortest Time Map
    PriorityQueue<Vertex> minDistanceQueue =
        new PriorityQueue<>(minTimeMap.size(), Comparator.comparing(minTimeMap::get));
    minTimeMap.keySet().stream().filter(v -> !v.equals(from)).forEach(minDistanceQueue::add);

    // Construct Visited Vertices Set
    Set<Vertex> visitedVertices = new HashSet<>();
    visitedVertices.add(from);

    // Run Algorithm
    while (!minDistanceQueue.isEmpty()) {
      Vertex v1 = minDistanceQueue.poll();
      visitedVertices.add(v1);

      Map<Vertex, Edge> v1OutgoingEdgeMap = v1.getOutgoingEdgeMap();
      for (Map.Entry<Vertex, Edge> outgoingEdge : v1OutgoingEdgeMap.entrySet()) {
        Vertex v2 = outgoingEdge.getKey();
        Edge e = outgoingEdge.getValue();
        if (!visitedVertices.contains(v2) && v2.isUp() && e.isUp()) {
          Float timeToReachUsingThisVertex = minTimeMap.get(v1) + v1.getOutgoingEdge(v2).getTime();
          if (minTimeMap.get(v2) > timeToReachUsingThisVertex) {
            minDistanceQueue.remove(v2);
            minTimeMap.put(v2, timeToReachUsingThisVertex);
            List<Vertex> shortestPath = new ArrayList<>(shortestPathMap.get(v1));
            shortestPath.add(v1);
            shortestPathMap.put(v2, shortestPath);
            minDistanceQueue.add(v2);
          }
        }
      }
    }

    dijkstrasResult.put(from, shortestPathMap);
  }

  /**
   * This function constructs the initial Shortest Transmission time map
   *
   * @param from, Source Vertex for Dijkstra's algorithm
   * @return
   */

  private Map<Vertex, Float> toInitialTimeMap(Vertex from) {
    Map<Vertex, Edge> outgoingEdgeMap = from.getOutgoingEdgeMap();
    Map<Vertex, Float> map = new HashMap<>();
    for (Map.Entry<String, Vertex> entry : nameToVerticesMap.entrySet()) {
      Vertex vertex = entry.getValue();
      if (vertex.isUp()) {
        if (vertex.equals(from)) {
          map.put(vertex, 0.0f);
        } else if (outgoingEdgeMap.containsKey(vertex) && outgoingEdgeMap.get(vertex).isUp()) {
          map.put(vertex, outgoingEdgeMap.get(entry.getValue()).getTime());
        } else {
          map.put(vertex, Float.POSITIVE_INFINITY);
        }
      }
    }
    return map;
  }

  /**
   * Print all reachable nodes
   *
   * <p>complexity: O(V(V+E)
   */

  public void printReachableVertices() {
    for (Vertex sourceVertex : nameToVerticesMap.values()) {
      if (sourceVertex.isUp()) {
        System.out.println(sourceVertex.getName());
        SortedSet<Vertex> reachableVertices = new TreeSet<>(Comparator.comparing(Vertex::getName));
        findReachableVertices(sourceVertex, reachableVertices);
        reachableVertices.remove(sourceVertex);
        for (Vertex vertex : reachableVertices) {
          System.out.println("  " + vertex.getName());
        }
      }
    }
  }
  /**
   * Recursively find Reachable vertices
   *
   * @param v1
   * @param reachableVertices
   */

  private void findReachableVertices(Vertex v1, SortedSet<Vertex> reachableVertices) {
    if (!reachableVertices.contains(v1)) {
      reachableVertices.add(v1);
      for (Map.Entry<Vertex, Edge> outgoingEdgeMapEntry : v1.getOutgoingEdgeMap().entrySet()) {
        Vertex v2 = outgoingEdgeMapEntry.getKey();
        Edge edge = outgoingEdgeMapEntry.getValue();
        if (v2.isUp() && edge.isUp() && !reachableVertices.contains(v2)) {
          findReachableVertices(v2, reachableVertices);
        }
      }
    }
  }

  /** Print Graph */

  public void printGraph() {
    for (Vertex sourceVertex : nameToVerticesMap.values()) {

      System.out.println(sourceVertex.getName() + (sourceVertex.isUp() ? "" : " DOWN"));

      for (Map.Entry<Vertex, Edge> outgoingEdgeMapEntry :
          sourceVertex.getOutgoingEdgeMap().entrySet()) {

        Vertex targetVertex = outgoingEdgeMapEntry.getKey();
        Edge edge = outgoingEdgeMapEntry.getValue();

        System.out.println(
            "  " + targetVertex.getName() + " " + edge.getTime() + (edge.isUp() ? "" : " DOWN"));
      }
    }
  }
}
/**
   * SURAJ MAHESH SHETTY
   * 801021051
   * ALGORITHM AND DATA STRUCTURES
   * ITCS - 6114
   */

import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class Vertex {

  private String name;
  private Map<Vertex, Edge> outgoingEdgeMap; // Maintaining a map for faster lookup
  private boolean isUp;

  public Vertex(String name) {
    this.name = name;
    outgoingEdgeMap = new TreeMap<>(Comparator.comparing(Vertex::getName));
    isUp = true;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Map<Vertex, Edge> getOutgoingEdgeMap() {
    return outgoingEdgeMap;
  }

  public void setOutgoingEdgeMap(Map<Vertex, Edge> outgoingEdgeMap) {
    this.outgoingEdgeMap = outgoingEdgeMap;
  }

  public Edge getOutgoingEdge(Vertex to) {
    return outgoingEdgeMap.getOrDefault(to, Edge.selfEdge);
  }

  public Boolean isUp() {
    return isUp;
  }

  public void setIsUp(Boolean up) {
    isUp = up;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Vertex)) {
      return false;
    }
    Vertex vertex = (Vertex) o;
    return Objects.equals(getName(), vertex.getName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName());
  }

  /**
   * @param toVertex
   * @param time
   */

  public void addEdge(Vertex toVertex, Float time) {
    if (outgoingEdgeMap.containsKey(toVertex)) {
      outgoingEdgeMap.get(toVertex).setTime(time);
    } else if (!equals(toVertex)) {
      outgoingEdgeMap.put(toVertex, new Edge(time));
    }
  }

  /** @param toVertex */
  public void deleteEdge(Vertex toVertex) {
    if (outgoingEdgeMap.containsKey(toVertex)) {
      outgoingEdgeMap.remove(toVertex);
    }
  }

  /** @param toVertex */
  public void markEdgeDown(Vertex toVertex) {
    if (outgoingEdgeMap.containsKey(toVertex)) {
      outgoingEdgeMap.get(toVertex).setUp(false);
    }
  }

  /** @param toVertex */
  public void markEdgeUp(Vertex toVertex) {
    if (outgoingEdgeMap.containsKey(toVertex)) {
      outgoingEdgeMap.get(toVertex).setUp(true);
    }
  }
}

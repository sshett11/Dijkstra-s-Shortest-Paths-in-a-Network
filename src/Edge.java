/**
   * SURAJ MAHESH SHETTY
   * 801021051
   * ALGORITHM AND DATA STRUCTURES
   * ITCS - 6114
   */
  
  public class Edge {
  public static final Edge selfEdge = new Edge(0.0f);
  private Float time;
  private boolean isUp;

  public Edge(Float time) {
    this.time = time;
    isUp = true;
  }

  public Float getTime() {
    return time;
  }

  public void setTime(Float time) {
    this.time = time;
  }

  public boolean isUp() {
    return isUp;
  }

  public void setUp(boolean up) {
    isUp = up;
  }
}

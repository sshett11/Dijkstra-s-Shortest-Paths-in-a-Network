Infor - Consider a data communication network that must route data packets (email, MP3 files, or videofiles,  for example).  Such a network consists of routers connected by physical cables or links.  Arouter can act as a source, a destination, or a forwarder of data packets.  We can model a network as a graph with each router corresponding to a vertex and the link or physical connection between two routers corresponding to a pair of directed edges between the vertices.A  network  that  follows  the  OSPF  (Open  Shortest  Path  First)  protocol  routes  packets  using Dijkstra’s shortest path algorithm.  The criteria used to compute the weight corresponding to a link can include the time taken for data transmission, reliability of the link, transmission cost, and available  bandwidth.   Typically  each  router  has  a  complete  representation  of  the  network  graph and associated information available to it.For the purposes of this project, each link has associated with it the transmission time taken for data to get from the vertex at one end to the vertex at the other end.  You will compute the best path using the criterion of minimizing the total time taken for data to reach the destination.The shortest time path minimizes the sum of the transmission times of the links along the path.The network topology can change dynamically based on the state of the links and the routers.For example, a link may go down when the corresponding cable is cut, and a vertex may go down when the corresponding router crashes.  In addition to these transient changes, changes to a network occur when a link is added or removed.


LANGUAGE - JAVA
JDK USED - jdk1.8.0_151
IDE USED - IntelliJ IDEA Community Edition 2017.3.4 x64
TOTAL NUMBER OF FILES – 4
TO RUN IT ON TERMINAL, STEPS -

- javac *.java
- java DijkstrasProject network.txt <queries.txt> output.txt


PROGRAM DESIGN -

File 1 - (DijkstrasProject.java) – RUN FILE FROM THIS -
This file contains the Main function and all other function call backs (scanner objects which will fetch data) &
call other specific files to access it’s complete functions mainly Graph.java and this file even has all the
cases which are used to figure out what to be performed + it even creates a graph from the
input(network.txt) file.

File 2 - (Graph.java) -
This file is where all the logic and Djisktra's implementation has been performed using min binary heap
(priority queue). It has all the functions like (addEdge, deleteEdge, markEdgeDown, markEdgeUp,
markVertexDown, markVertexUp, printShortestPath, calculateDijkstras, reachables & print the graph).

File 3 & File 4 - (Vertex.java & Edge.java) -
These files have respective get/set methods & validations related to vertex (addition, deletion, edge
up/down).

About Reachability function – I have used a recursive function to calculate all the reachable nodes.
According to me, the time complexity for this would be O(V(V+E)).

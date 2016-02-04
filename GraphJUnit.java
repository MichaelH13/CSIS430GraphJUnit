import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;

/**
 * @author Michael
 * @param <T>
 * @version 1.3
 */
public class GraphJUnit<T>
{
   private final long       INVALID_PATH    = -1;
   private final int        INVALID_EDGE    = -1;
   private final long       MIN_EDGE_WEIGHT = 0;

   public Graph<String>     undirectedGraph;
   public Graph<String>     directedGraph;
   public ArrayList<String> vertices;

   private void setupGraph()
   {
      // Create a non-directed and directed Graph.
      undirectedGraph = new Graph<String>(false);
      directedGraph = new Graph<String>(true);
      vertices = new ArrayList<String>();

      // Add A, B, C, D, E to vertices ArrayList.
      for (int i = 0; i < 5; i++)
      {
         vertices.add(String.valueOf(Character.valueOf((char) ('A' + i))));
      }

   }

   private void clearGraph()
   {
      undirectedGraph = null;
      directedGraph = null;
      vertices = null;
   }

   private void addVerticesToGraph()
   {
      for (String v : vertices)
      {
         undirectedGraph.addVertex(v);
         directedGraph.addVertex(v);
      }
   }

   private void addEdgesToGraph()
   {
      addVerticesToGraph();

      // Map A->B, B->C...E->A, create a cycle.
      for (int i = 0; i < vertices.size(); i++)
      {
         undirectedGraph.addEdge(vertices.get(i),
                  vertices.get((i + 1) % vertices.size()), 1);

         directedGraph.addEdge(vertices.get(i),
                  vertices.get((i + 1) % vertices.size()), 1);
      }
   }

   /**
    * Test method for {@link Graph#Graph(boolean)}.
    */
   @Test
   public void testGraph()
   {
      // Clear, verify null.
      clearGraph();
      assert (directedGraph == null);
      assert (undirectedGraph == null);
      assert (vertices == null);

      // setup, test if null.
      setupGraph();
      assert (directedGraph != null);
      assert (undirectedGraph != null);
      assert (vertices != null);
   }

   /**
    * Test method for {@link Graph#getVertices()}.
    */
   @Test
   public void testGetVertices()
   {
      clearGraph();
      undirectedGraph = new Graph<>(false);
      directedGraph = new Graph<>(true);

      // Test directed
      if (undirectedGraph.getVertices().size() != 0)
      {
         fail("Empty undirected Graph has invalid vertices.");
      }

      // Test directed
      if (directedGraph.getVertices().size() != 0)
      {
         fail("Empty directed Graph has invalid vertices.");
      }

      setupGraph();
      addEdgesToGraph();

      // Test undirected.
      for (String s : vertices)
      {
         if (!undirectedGraph.getVertices().contains(s))
         {
            fail("Undirected Graph missing vertice:" + s);
         }
      }

      // Test undirected.
      for (String s : vertices)
      {
         if (!directedGraph.getVertices().contains(s))
         {
            fail("Directed Graph missing vertice:" + s);
         }
      }
   }

   /**
    * Test method for
    * {@link Graph#getEdgeWeight(java.lang.Object, java.lang.Object)}.
    */
   @Test
   public void testGetEdgeWeight()
   {
      setupGraph();
      addEdgesToGraph();

      // Test undirected Graph.
      if (undirectedGraph.getEdgeWeight("A", "B") != 1)
      {
         fail("A->B != 1");
      }

      // Test directed Graph.
      if (directedGraph.getEdgeWeight("A", "B") != 1)
      {
         fail("A->B != 1");
      }

      // Test undirected Graph.
      if (undirectedGraph.getEdgeWeight("A", "A") != MIN_EDGE_WEIGHT)
      {
         fail("A->A != 0");
      }

      // Test directed Graph.
      if (directedGraph.getEdgeWeight("A", "A") != MIN_EDGE_WEIGHT)
      {
         fail("A->A != 0");
      }

      // Clear Edges and create new Graphs/vertices.
      setupGraph();

      // Test undirected Graph.
      if (undirectedGraph.getEdgeWeight("A", "B") != INVALID_EDGE)
      {
         fail("A->B exists in undirected Graph when it shouldn't.");
      }
      if (undirectedGraph.getEdgeWeight("B", "A") != INVALID_EDGE)
      {
         fail("B->A exists in undirected Graph when it shouldn't.");
      }

      // Test directed Graph.
      if (directedGraph.getEdgeWeight("A", "B") != INVALID_EDGE)
      {
         fail("A->B exists in undirected Graph when it shouldn't.");
      }

   }

   /**
    * Test method for
    * {@link Graph#edgeExists(java.lang.Object, java.lang.Object)}.
    */
   @Test
   public void testEdgeExists()
   {
      setupGraph();

      // Test undirected Graph null to null.
      if (undirectedGraph.edgeExists(null, null))
      {
         fail("Undirected null to null test failed");
      }

      // Test directed Graph null to null.
      if (directedGraph.edgeExists(null, null))
      {
         fail("Directed null to null test failed");
      }

      // Test undirected Graph for some invalid edges.
      for (int i = 0; i < vertices.size() - 1; i++)
      {
         if (undirectedGraph.edgeExists(vertices.get(i), vertices.get(i + 1)))
         {
            fail("Edge from: " + vertices.get(i) + " to: "
                     + vertices.get(i + 1)
                     + "\nExists in empty undirected Graph.");
         }
      }

      // Test directed Graph for some invalid Edges.
      for (int i = 0; i < vertices.size() - 1; i++)
      {
         if (directedGraph.edgeExists(vertices.get(i), vertices.get(i + 1)))
         {
            fail("Edge from: " + vertices.get(i) + " to: "
                     + vertices.get(i + 1)
                     + "\nExists in empty directed Graph.");
         }
      }

      addEdgesToGraph();

      // Test undirected Graph for some invalid Edges.
      for (int i = 0; i < vertices.size() - 1; i++)
      {
         if (!undirectedGraph.edgeExists(vertices.get(i), vertices.get(i + 1)))
         {
            fail("Edge from: " + vertices.get(i) + " to: "
                     + vertices.get(i + 1)
                     + "\nDoes not exist in undirected Graph.");
         }
      }

      // Test directed Graph for some invalid Edges.
      for (int i = 0; i < vertices.size() - 1; i++)
      {
         if (!directedGraph.edgeExists(vertices.get(i), vertices.get(i + 1)))
         {
            fail("Edge from: " + vertices.get(i) + " to: "
                     + vertices.get(i + 1)
                     + "\nDoes not exist in directed Graph.");
         }
      }
   }

   /**
    * Test method for {@link Graph#addVertex(java.lang.Object)}.
    */
   @Test
   public void testAddVertex()
   {
      setupGraph();

      // Test undirected.
      undirectedGraph.addVertex("A");
      if (!undirectedGraph.getVertices().contains("A"))
      {
         fail("Undirected Graph doesn't contain vertex: A");
      }

      // Test directed.
      directedGraph.addVertex("A");
      if (!directedGraph.getVertices().contains("A"))
      {
         fail("Directed Graph doesn't contain vertex: A");
      }
   }

   /**
    * Test method for {@link Graph#removeVertex(java.lang.Object)}.
    */
   @Test
   public void testRemoveVertex()
   {
      setupGraph();
      addEdgesToGraph();

      // Test undirected.
      // Remove vertex, then test to see if the Graph still contains the vertex
      // in its vertices.
      undirectedGraph.removeVertex("A");

      if (undirectedGraph.getVertices().contains("A"))
      {
         fail("Undirected Graph still contains vertex: A");
      }
      else
      {
         try
         {
            undirectedGraph.removeVertex("A");
            fail("Exception not thrown for attempting to remove invalid Vertex on undirected Graph.");
         }
         catch (Exception e)
         {
            // pass
         }
      }

      // Test directed.
      // Remove vertex, then test to see if the Graph still contains the vertex
      // in its vertices.
      directedGraph.removeVertex("A");
      if (directedGraph.getVertices().contains("A"))
      {
         fail("Directed Graph still contains vertex: A");
      }
      else
      {
         try
         {
            directedGraph.removeVertex("A");
            fail("Exception not thrown for attempting to remove invalid Vertex on directed Graph.");
         }
         catch (Exception e)
         {
            // pass
         }
      }
   }

   /**
    * Test method for
    * {@link Graph#addEdge(java.lang.Object, java.lang.Object, int)}.
    */
   @Test
   public void testAddEdge()
   {
      setupGraph();

      // Make sure Edge is not in Graph.
      checkEdgeABExists(false);

      // Add Edge.
      addEdgesToGraph();

      // Make sure Edge was added to both Graphs.
      checkEdgeABExists(true);

      // Clear, setup new vertices
      clearGraph();
      setupGraph();

      // Test undirected Graph.
      try
      {
         undirectedGraph.addEdge("A", "A", 1);
         fail("Exception not thrown for undirected Graph adding Edge that links to itself.");
      }
      catch (Exception e)
      {
         // pass
      }

      // Test directed Graph.
      try
      {
         directedGraph.addEdge("A", "A", 1);
         fail("Exception not thrown for directed Graph adding Edge that links to itself.");
      }
      catch (Exception e)
      {
         // pass
      }

      // Test undirected Graph.
      try
      {
         undirectedGraph.addEdge("A", "C", INVALID_EDGE);
         fail("Exception not thrown for undirected Graph adding INVALID_EDGE weight.");
      }
      catch (Exception e)
      {
         // pass
      }

      // Test directed Graph.
      try
      {
         directedGraph.addEdge("A", "C", INVALID_EDGE);
         fail("Exception not thrown for directed Graph adding INVALID_EDGE weight.");
      }
      catch (Exception e)
      {
         // pass
      }
   }

   /**
    * Test method for
    * {@link Graph#removeEdge(java.lang.Object, java.lang.Object)}.
    */
   @Test
   public void testRemoveEdge()
   {
      setupGraph();
      addEdgesToGraph();

      // Make sure Edge was added to both Graphs.
      checkEdgeABExists(true);

      // Remove A->B from both Graphs.
      directedGraph.removeEdge("A", "B");
      undirectedGraph.removeEdge("A", "B");

      // Make sure Edge was removed from both Graphs.
      checkEdgeABExists(false);

      // We should still be able to get from one node to itself.
      // There is no good logic for this because we can't add Edges where the
      // source and destination is the same node, but Hansen wants it this
      // way anyways...
      if (!directedGraph.edgeExists("A", "A")
               || directedGraph.getEdgeWeight("A", "A") != MIN_EDGE_WEIGHT)
      {
         fail("A-->A does not exist in the directed Graph.");
      }

      // We should still be able to get from one node to itself.
      if (!undirectedGraph.edgeExists("A", "A")
               || undirectedGraph.getEdgeWeight("A", "A") != MIN_EDGE_WEIGHT)
      {
         fail("A-->A does not exist in the undirected Graph.");
      }
   }

   // Testing whether Edge exists or not.
   public void checkEdgeABExists(boolean exists)
   {

      // Test undirected.
      // Edge A->B exists, and we want it to exist.
      // or Edge A->B does not exist, and we don't want it to exist.
      if (undirectedGraph.edgeExists("A", "B") == exists)
      {
         // pass
      }
      // Edge A->B does not exist, and we want it to exist
      // or Edge A->B does exist and we don't want it to exists,
      else if (undirectedGraph.edgeExists("A", "B") != exists)
      {
         if (exists)
            fail("Edge A->B doesn't exist in undirected Graph.");
         else
            fail("Edge A->B still exists in undirected Graph.");
      }

      // Test opposite direction for undirected Graph.
      // Edge B->A exists, and we want it to exist.
      // or Edge B->A does not exist, and we don't want it to exist.
      if (undirectedGraph.edgeExists("B", "A") == exists)
      {
         // pass
      }
      // Edge B->A does not exist, and we want it to exist
      // or Edge B->A does exist and we don't want it to exists,
      else if (undirectedGraph.edgeExists("B", "A") != exists)
      {
         // since the Graph is undirected, we should be able to get
         // from A->B as well as B->A.
         if (exists)
            fail("Edge B->A doesn't exist in undirected Graph.");
         else
            fail("Edge B->A still exists in undirected Graph.");
      }

      // Test undirected.
      // Edge B->A exists, and we want it to exist.
      // or Edge B->A does not exist, and we don't want it to exist.
      if (undirectedGraph.edgeExists("B", "A") == exists)
      {
         // pass
      }
      // Edge B->A does not exist, and we want it to exist
      // or Edge B->A exists and we do not want it to exist.
      else if (undirectedGraph.edgeExists("B", "A") != exists)
      {
         if (exists)
            fail("Edge B->A doesn't exist in undirected Graph.");
         else
            fail("Edge B->A still exists in undirected Graph.");
      }
   }

   /**
    * Test method for {@link Graph#toString()}.
    */
   @Test
   public void testToString()
   {
      setupGraph();
      addEdgesToGraph();
      undirectedGraph.addEdge("A", "D", 3);
      directedGraph.addEdge("A", "D", 3);
      System.out.println(undirectedGraph.toString());
      System.out.println(directedGraph.toString());

      directedGraph.addEdge("D", "A", 3);
      System.out.println(directedGraph.toString());

      undirectedGraph.addEdge("A", "D", 1);
      directedGraph.addEdge("A", "D", 1);
      System.out.println(undirectedGraph.toString());
      System.out.println(directedGraph.toString());

      undirectedGraph.removeEdge("A", "D");
      directedGraph.removeEdge("A", "D");
      System.out.println(undirectedGraph.toString());
      System.out.println(directedGraph.toString());
   }

   /**
    * Test method for {@link Graph#shortestPathBetween(Object, Object)}.
    * 
    * @throws IOException
    */
   @Test
   public void testshortestPathBetweenAndfromCSVFile() throws IOException
   {
      Scanner in = new Scanner(
               new File(
                        "/Users/michaelhiggins/Desktop/Google Drive/Desktop-MichaelsMBP/GFU CSIS/CSIS430 Hw #2 Graph II/src/Campus_Map.csv"));

      Graph<String> g = Graph.fromCSVFile(false, in);

      // 0
      runShortestPathBetweenTestFromToExpectingWeight(g, "EHS Center",
               "EHS Center", 0);

      // 258
      // Edward Stevens Center <-- Hoover Academic Building <-- EHS Center
      runShortestPathBetweenTestFromToExpectingWeight(g, "EHS Center",
               "Edward Stevens Center", 258);

      // 130
      // Hoover Academic Building <-- EHS Center
      runShortestPathBetweenTestFromToExpectingWeight(g, "EHS Center",
               "Hoover Academic Building", 130);

      // 128
      // Edward Stevens Center <-- Hoover Academic Building
      runShortestPathBetweenTestFromToExpectingWeight(g,
               "Hoover Academic Building", "Edward Stevens Center", 128);

      // 459
      // Amphitheater <-- Edwards Residence Hall <-- Student Union Building <--
      // Hoover Academic Building
      runShortestPathBetweenTestFromToExpectingWeight(g,
               "Hoover Academic Building", "Amphitheater", 459);

      // 696
      // Roberts Center <-- Edwards Residence Hall <-- Student Union Building
      // <-- EHS Center
      runShortestPathBetweenTestFromToExpectingWeight(g, "EHS Center",
               "Roberts Center", 696);

      // 761
      // Coffin Residence Hall <-- Barclay House <-- Amphitheater <-- Edwards
      // Residence Hall <-- Student Union Building <-- EHS Center
      runShortestPathBetweenTestFromToExpectingWeight(g, "EHS Center",
               "Coffin Residence Hall", 761);

      // 6
      // River Street Apartment <-- Marketing and Communications (River Street
      // House)
      runShortestPathBetweenTestFromToExpectingWeight(g,
               "Marketing and Communications (River Street House)",
               "River Street Apartment", 6);

      // 1669
      // Spiritual Life (Sheridan Street House) <-- Security Office <-- Hoover
      // Academic Building <-- Student Union Building <-- Edwards Residence Hall
      // <-- Amphitheater <-- Wheeler Sports Center <-- Plant Services Building
      runShortestPathBetweenTestFromToExpectingWeight(g,
               "Plant Services Building",
               "Spiritual Life (Sheridan Street House)", 1669);

      // 824
      // Coffin Residence Hall --92--> Barclay House --168--> Amphitheater
      // --275--> Edwards Residence Hall --79--> Student Union Building --210-->
      // Pennington Residence Hall
      runShortestPathBetweenTestFromToExpectingWeight(g,
               "Coffin Residence Hall", "Pennington Residence Hall", 824);

      // 5355
      // Coffin Residence Hall --92--> Barclay House --168--> Amphitheater
      // --174--> Wheeler Sports Center --485--> Stoffer Family Stadium &
      // Lemmons Family Field --86--> Duke Athletic Center --126--> Fulton
      // Street House --4224--> Austin Sports Complex
      runShortestPathBetweenTestFromToExpectingWeight(g,
               "Coffin Residence Hall", "Austin Sports Complex", 5355);

   }

   /**
    * Test method for {@link Graph.Edge#compareTo(Object)}.
    */
   @Test
   public void testgraphEdgeCompare()
   {
      Graph.Edge<String> e = new Graph.Edge<String>("A", "B", 1);
      Graph.Edge<String> f = new Graph.Edge<String>("A", "B", 1);
      Graph.Edge<String> g = new Graph.Edge<String>("A", "B", 2);
      Graph.Edge<String> h = new Graph.Edge<String>("A", "B", 3);

      if (e.compareTo(f) != 0)
      {
         fail("Edges are equal, but compareTo() returns a non-zero value.");
      }

      if (g.compareTo(h) >= 0)
      {
         fail("This Edge is less than the Edge passed as an argument, but compareTo() returns a non-negative value.");
      }

      if (g.compareTo(f) <= 0)
      {
         fail("This Edge is greater than the Edge passed as an argument, but compareTo() returns a non-positive value.");
      }
   }

   /**
    * Test method for {@link Graph#pathLength()}.
    */
   @Test
   public void testpathLength()
   {
      List<String> path = new ArrayList<>();
      setupGraph();
      addVerticesToGraph();

      // Graphs depicted by the below code:
      //
      // NOTE: undirectedGraph will be able to navigate from one node to any
      // other node. directedGraph, on the other hand, will not as it is a
      // directed
      // Graph.
      //
      // Graph Edges to be added:
      // A-->B, A-->C
      // B-->D, B-->E
      // Essentially we are creating a binary tree of nodes such that A is the
      // root, B and C are its direct children, and B has the children D and E.
      // Thus, we should only be able to get to C from A, and we have to go
      // through B to get to D or E.
      //

      // Add Edges for undirectedGraph.
      undirectedGraph.addEdge("A", "B", 1);
      undirectedGraph.addEdge("A", "C", 2);
      undirectedGraph.addEdge("B", "D", 2);
      undirectedGraph.addEdge("B", "E", 3);

      // Add Edges for directedGraph.
      directedGraph.addEdge("A", "B", 1);
      directedGraph.addEdge("A", "C", 2);
      directedGraph.addEdge("B", "D", 2);
      directedGraph.addEdge("B", "E", 3);
      path.add("A");
      path.add("B");
      path.add("D");

      // Test valid path for Directed Graph.
      if (directedGraph.pathLength(path) != 3L)
      {

         System.out.println("fail: Directed Graph path: "
                  + getPathOfVertices(path)
                  + " does not equal 3. Instead it is: "
                  + directedGraph.pathLength(path));
         fail("Length of path A -1-> B -2-> D is not 3."
                  + System.lineSeparator());
      }

      // Test valid path for Undirected Graph.
      if (undirectedGraph.pathLength(path) != 3L)
      {

         System.out.println("fail: Undirected Graph path: "
                  + getPathOfVertices(path)
                  + " does not equal 3. Instead it is: "
                  + undirectedGraph.pathLength(path));
         fail("Length of path A -1-> B -2-> D is not 3."
                  + System.lineSeparator());
      }

      // Alter the path to terminate at E instead of D.
      path.remove("D");
      path.add("E");

      // Test valid path for Directed Graph.
      if (directedGraph.pathLength(path) != 4L)
      {

         System.out.println("fail: Directed Graph path: "
                  + getPathOfVertices(path)
                  + " does not equal 4. Instead it is: "
                  + directedGraph.pathLength(path));
         fail("Length of path A -1-> B -3-> E is not 4."
                  + System.lineSeparator());
      }

      // Test valid path for Undirected Graph.
      if (undirectedGraph.pathLength(path) != 4L)
      {

         System.out.println("fail: Undirected Graph path: "
                  + getPathOfVertices(path)
                  + " does not equal 4. Instead it is: "
                  + undirectedGraph.pathLength(path));
         fail("Length of path A -1-> B -3-> E is not 4."
                  + System.lineSeparator());
      }

      // Uncomment this line to test if the shortestPathBetween returns 1 for
      // A-->E (A-->E) instead of 4 (A-->B-->E):
      directedGraph.addEdge("A", "E", 1);
      undirectedGraph.addEdge("A", "E", 1); // <--Uncomment for magic.
      path.remove("B");

      // Test valid, shorter path for Directed Graph.
      if (directedGraph.pathLength(path) != 1L)
      {

         System.out.println("fail: Directed Graph path: "
                  + getPathOfVertices(path)
                  + " does not equal 1. Instead it is: "
                  + directedGraph.pathLength(path));
         fail("Length of path A -1-> E is not 1." + System.lineSeparator());
      }

      // Test valid, shorter path for Undirected Graph.
      if (undirectedGraph.pathLength(path) != 1L)
      {

         System.out.println("fail: Undirected Graph path: "
                  + getPathOfVertices(path)
                  + " does not equal 1. Instead it is: "
                  + undirectedGraph.pathLength(path));
         fail("Length of path A -1-> E is not 1." + System.lineSeparator());
      }

      // Remove A-->E to create an invalid path.
      undirectedGraph.removeEdge("A", "E");
      directedGraph.removeEdge("A", "E");

      // Test directed Graph.
      if (directedGraph.pathLength(path) != INVALID_PATH)
      {
         fail("pathLength() failed to return INVALID_PATH for an invalid path a directed Graph.");
      }

      // Test undirected Graph.
      if (undirectedGraph.pathLength(path) != INVALID_PATH)
      {
         fail("pathLength() failed to return INVALID_PATH for an invalid path on an undirected Graph.");
      }
   }

   /**
    * Helper method to build a String representing the path passed as an
    * argument.
    * 
    * @param path
    *           The path to build the path.
    * @return The String representing the path passed as an argument.
    */
   private String getPathOfVertices(List<String> path)
   {
      StringBuilder s = new StringBuilder();

      if (!path.isEmpty())
      {
         s.append(path.get(0));
      }

      for (int i = 1; i < path.size(); i++)
      {
         s.append(" ---> " + path.get(i));
      }

      return s.toString();
   }

   /**
    * Private method to aid in running shortest path tests.
    * 
    * @param source
    *           The source node for the shortest path test.
    * @param destination
    *           The destination node for the shortest path test.
    * @param expectedMinWeight
    *           The expected minimum weight for the test.
    */
   private void runShortestPathBetweenTestFromToExpectingWeight(
            Graph<String> g, String source, String destination,
            Integer expectedMinWeight)
   {
      List<Graph.Edge<String>> shortestPath = g.shortestPathBetween(source,
               destination);
      if (expectedMinWeight == null
               || getPathWeightFromEdgesList(shortestPath) == expectedMinWeight)
      {
         System.out.println("TEST CASE: " + System.lineSeparator() + source
                  + " ---> " + destination);

         // If the path is empty, then it must be a single path from the node to
         // itself (which we don't technically allow, but we will for the
         // purpose of fuzzy Hansen logic.
         if (shortestPath.isEmpty())
         {
            System.out.println("Total path weight: "
                     + getPathWeightFromEdgesList(shortestPath));
            System.out.println(source + " --0--> " + destination
                     + System.lineSeparator());
            return; // just get out, who cares how.
         }

         displayPathOfEdges(shortestPath);
      }
      else
      {
         fail("Path from " + source + " to " + destination
                  + " did not match the weight: " + expectedMinWeight
                  + "  passed as an arg.");
      }
   }

   /**
    * Private method to quickly get us the length of a given list of Edges.
    * 
    * ASSUMPTIONS: We assume that the list of Edges has been verified against
    * the Graph.
    * 
    * @param path
    *           The path to measure.
    * @return The total length of the path.
    */
   private static int getPathWeightFromEdgesList(List<Graph.Edge<String>> path)
   {
      int totalPathWeight = 0;

      // Iterate over each of the Edges and sum each weight.
      for (Graph.Edge<String> edge : path)
      {
         totalPathWeight += edge.getWeight();
      }

      return totalPathWeight;
   }

   /**
    * Private method to display the path of a given list of Edges. This method
    * assumes that we have at least ONE Edge.
    * 
    * @param path
    *           The path to measure.
    */
   private static void displayPathOfEdges(List<Graph.Edge<String>> path)
   {
      System.out.println("Total path weight: "
               + getPathWeightFromEdgesList(path));

      // Display first Edge, then display the destinations of each following
      // Edge.
      System.out.print(path.get(0).getSource());

      for (Graph.Edge<String> edge : path)
      {
         System.out.print(" --" + edge.getWeight() + "--> "
                  + edge.getDestination());
      }
      System.out.println(System.lineSeparator());

   }
}

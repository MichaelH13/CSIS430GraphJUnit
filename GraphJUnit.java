import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Test;

/**
 * @author Michael
 * @param <T>
 * @version 1.5
 */
public class GraphJUnit<T>
{
   private final String     GRAPH_CSV_FILE  = "src/Campus_Map.csv";
   private final boolean    CSV_HAS_MST     = true;

   private final long       INVALID_PATH    = -1;
   private final long       MIN_EDGE_WEIGHT = 0;
   private final int        INVALID_EDGE    = -1;
   private static int       testNum         = 0;
   private String           testMethod      = "";

   /**
    * Undirected Graph used for testing.
    */
   public Graph<String>     undirectedGraph;

   /**
    * Directed Graph used for testing.
    */
   public Graph<String>     directedGraph;
   public ArrayList<String> vertices;

   /**
    * Method to create a directed and an undirected graph as well as a list of
    * vertices. Note: Vertices are not added, the Graph objects are only
    * created.
    */
   private void createGraphs()
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

   /**
    * Test method for {@link Graph#Graph(boolean)}. Tests object creation.
    */
   @Test
   public void testGraph()
   {
      testMethod = "Graph(boolean)";
      // Clear, verify null.
      if (directedGraph != null)
      {
         failTest("Directed Graph is non-null.");
      }
      else
      {
         passTest("Directed Graph is indeed null.");
      }

      if (undirectedGraph != null)
      {
         failTest("Undirected Graph is non-null.");
      }
      else
      {
         passTest("Undirected Graph is indeed null.");
      }

      if (vertices != null)
      {
         failTest("Vertices are non-null.");
      }
      else
      {
         passTest("Vertices are indeed null.");
      }

      // setup, test if null.
      createGraphs();
      if (directedGraph == null)
      {
         failTest("Directed Graph not created");
      }
      else
      {
         passTest("Directed Graph created.");
      }

      if (undirectedGraph == null)
      {
         failTest("Undirected Graph not created.");
      }
      else
      {
         passTest("Undirected Graph created.");
      }

      if (vertices == null)
      {
         failTest("Vertices were not created.");
      }
      else
      {
         passTest("Vertices were created.");
      }
   }

   /**
    * Test method for {@link Graph#shortestPathBetween(Object, Object)}.
    * 
    * Test for shortestPathBetween(T, T):
    * 
    * 1. Empty Graph.
    */
   @Test
   public void testShortest1()
   {
      createGraphs();
      List<Graph.Edge<String>> p;
      testMethod = "shortestPathBetween(T, T)";

      // 1.1 Empty Undirected Graph.
      try
      {
         p = undirectedGraph.shortestPathBetween("A", "B");
         failTest("Empty Undirected Graph returned a Path instead of throwing an exception. Make sure that the source and destination are validated...");
      }
      catch (NoSuchElementException e)
      {
         passTest("Empty Undirected Graph threw an exception when asked for a shortest Path.");
      }

      // 1.2 Empty Undirected Graph.
      try
      {
         p = directedGraph.shortestPathBetween("A", "B");
         failTest("Empty Directed Graph returned a Path instead of throwing an exception. Make sure that the source and destination are validated...");
      }
      catch (NoSuchElementException e)
      {
         passTest("Empty Directed Graph threw an exception when asked for a shortest Path.");
      }
   }

   /**
    * Test method for {@link Graph#shortestPathBetween(Object, Object)}.
    * 
    * Test for shortestPathBetween(T, T):
    * 
    * 2. Unconnected Graph with one Vertex.
    */
   @Test
   public void testShortest2()
   {
      createGraphs();
      testMethod = "shortestPathBetween(T, T)";
      undirectedGraph.addVertex("A");
      directedGraph.addVertex("A");

      List<Graph.Edge<String>> p;

      // 2.1 Unconnected Undirected Graph with one Vertex.
      try
      {
         p = undirectedGraph.shortestPathBetween("A", "B");
         failTest("Unconnected Undirected Graph with one Vertex does not throw an exception when asked for Path where the destination does not exist. Make sure that the source AND destination are validated...");
      }
      catch (NoSuchElementException e)
      {
         passTest("Unconnected Undirected Graph with one Vertex threw a NoSuchElementException when asked for Path where the destination does not exist.");
      }

      // 2.2 Unconnected Directed Graph with one Vertex.
      try
      {
         p = directedGraph.shortestPathBetween("A", "B");
         failTest("Unconnected Directed Graph with one Vertex does not throw an exception when asked for Path where the destination does not exist. Make sure that the source AND destination are validated...");
      }
      catch (NoSuchElementException e)
      {
         passTest("Unconnected Directed Graph with one Vertex threw a NoSuchElementException when asked for Path where the destination does not exist.");
      }
   }

   /**
    * Test method for {@link Graph#shortestPathBetween(Object, Object)}.
    * 
    * Test for shortestPathBetween(T, T):
    * 
    * 3. Unconnected Graph with two Vertices.
    */
   @Test
   public void testShortest3()
   {
      createGraphs();
      testMethod = "shortestPathBetween(T, T)";
      undirectedGraph.addVertex("A");
      directedGraph.addVertex("A");
      undirectedGraph.addVertex("B");
      directedGraph.addVertex("B");

      List<Graph.Edge<String>> p;

      // 3.1 Unconnected Undirected Graph with two Vertices.
      try
      {
         p = undirectedGraph.shortestPathBetween("A", "B");

         if (p != null)
         {
            failTest("Unconnected Undirected Graph with two Vertices does return null when asked for Path where Vertices exist, but no Edges exist.");
         }
         else
         {
            passTest("Unconnected Undirected Graph with two Vertices returns null when asked for Path where Vertices exist, but no Edges exist.");
         }
      }
      catch (Exception e)
      {
         failTest("Unconnected Undirected Graph with two Vertices throws an exception when asked for Path where Vertices exist, but no Edges exist.");
      }

      // 3.2 Unconnected Directed Graph with two Vertices.
      try
      {
         p = directedGraph.shortestPathBetween("A", "B");

         if (p != null)
         {
            failTest("Unconnected Directed Graph with two Vertices does return null when asked for Path where Vertices exist, but no Edges exist.");
         }
         else
         {
            passTest("Unconnected Directed Graph with two Vertices returns null when asked for Path where Vertices exist, but no Edges exist.");
         }
      }
      catch (Exception e)
      {
         failTest("Unconnected Directed Graph with two Vertices throws an exception when asked for Path where Vertices exist, but no Edges exist.");
      }
   }

   /**
    * Test method for {@link Graph#shortestPathBetween(Object, Object)}.
    * 
    * Test for shortestPathBetween(T, T):
    * 
    * 4. Connected Graph with two Vertices.
    */
   @Test
   public void testShortest4()
   {
      createGraphs();
      testMethod = "shortestPathBetween(T, T)";
      undirectedGraph.addVertex("A");
      undirectedGraph.addVertex("B");
      undirectedGraph.addEdge("A", "B", 1);

      directedGraph.addVertex("A");
      directedGraph.addVertex("B");
      directedGraph.addEdge("A", "B", 1);

      List<Graph.Edge<String>> p;

      // 4.1 Connected Undirected Graph with two Vertices and an Edge between
      // them.
      try
      {
         p = undirectedGraph.shortestPathBetween("A", "B");

         if (p != null)
         {
            passTest("Connected Undirected Graph with two Vertices does return null when asked for Path where Vertices and an Edge between the Vertices exists.");
         }
         else
         {
            failTest("Connected Undirected Graph with two Vertices returned null when asked for Path where Vertices and an Edge between the Vertices exists.");
         }

         p = undirectedGraph.shortestPathBetween("B", "A");

         if (p != null)
         {
            passTest("Connected Undirected Graph with two Vertices does return null when asked for Path where Vertices and an Edge between the Vertices exists.");
         }
         else
         {
            failTest("Connected Undirected Graph with two Vertices returned null when asked for Path where Vertices and an Edge between the Vertices exists.");
         }
      }
      catch (Exception e)
      {
         failTest("Connected Undirected Graph with two Vertices threw an exception when asked for Path where Vertices and an Edge between the Vertices exists.");
      }

      // 4.2 Connected Directed Graph with two Vertices and an Edge between
      // them.
      try
      {
         p = directedGraph.shortestPathBetween("A", "B");

         if (p == null)
         {
            failTest("Connected Directed Graph with two Vertices returned null when asked for Path where Vertices and an Edge between the Vertices exists.");
         }
         else
         {
            passTest("Connected Directed Graph with two Vertices returned non-null when asked for Path where Vertices and an Edge between the Vertices exists.");
         }

         p = directedGraph.shortestPathBetween("B", "A");

         if (p != null)
         {
            failTest("Connected Directed Graph with two Vertices returned non-null when asked for Path where Vertices and an Edge between the Vertices exists, but in the wrong direction.");
         }
         else
         {
            passTest("Connected Directed Graph with two Vertices returned null when asked for Path where Vertices and an Edge between the Vertices exists, but in the wrong direction");
         }
      }
      catch (Exception e)
      {
         failTest("Connected Directed Graph with two Vertices threw an exception when asked for Path where Vertices and an Edge between the Vertices exists.");
      }
   }

   /**
    * Test method for {@link Graph#shortestPathBetween(Object, Object)}.
    * 
    * Test for shortestPathBetween(T, T):
    * 
    * 5. Unconnected Graph with six Vertices.
    */
   @Test
   public void testShortest5()
   {
      List<Graph.Edge<String>> p;
      createGraphs();
      testMethod = "shortestPathBetween(T, T)";

      // Add Vertices A, B,...,E , F to the Graphs.
      for (int i = 0; i < 6; i++)
      {
         undirectedGraph.addVertex(String.valueOf(Character
                  .valueOf((char) ('A' + i))));
         directedGraph.addVertex(String.valueOf(Character
                  .valueOf((char) ('A' + i))));
      }

      undirectedGraph.addEdge("A", "B", 4);
      undirectedGraph.addEdge("B", "C", 3);
      undirectedGraph.addEdge("C", "D", 1);
      undirectedGraph.addEdge("D", "E", 2);
      undirectedGraph.addEdge("D", "A", 2);
      undirectedGraph.addEdge("D", "B", 1);
      undirectedGraph.addEdge("E", "A", 1);
      // F is the disconnected Vertex.

      directedGraph.addEdge("A", "B", 4);
      directedGraph.addEdge("B", "C", 3);
      directedGraph.addEdge("B", "D", 1);
      directedGraph.addEdge("C", "D", 1);
      directedGraph.addEdge("D", "E", 2);
      directedGraph.addEdge("D", "A", 2);
      directedGraph.addEdge("D", "B", 1);
      directedGraph.addEdge("E", "A", 1);
      // F is the disconnected Vertex.

      // 5.1 Unconnected Undirected Graph with six Vertices and some Edges, but
      // no Edges to one Vertex.
      try
      {
         p = undirectedGraph.shortestPathBetween("A", "B");

         // Try going from A-->B...
         if (p == null)
         {
            failTest("Unconnected Undirected Graph with six Vertices returns null shortest Path when asked for a valid shortest Path..");
         }
         else if (getPathWeightFromEdgesList(p) == 3)
         {
            passTest("Unconnected Undirected Graph with six Vertices returns correct shortest Path when asked for a valid shortest Path..");
         }
         else
         {
            System.out.println(pathToString(p));
            failTest("Unconnected Undirected Graph with six Vertices returns incorrect shortest Path when asked for a valid shortest Path..");
         }

         p = undirectedGraph.shortestPathBetween("B", "A");

         // Validate the other direction...
         if (p == null)
         {
            failTest("Unconnected Undirected Graph with six Vertices returns null shortest Path when asked for a valid shortest Path..");
         }
         else if (getPathWeightFromEdgesList(p) == 3)
         {
            passTest("Unconnected Undirected Graph with six Vertices returns correct shortest Path when asked for a valid shortest Path..");
         }
         else
         {
            System.out.println(pathToString(p));
            failTest("Unconnected Undirected Graph with six Vertices returns incorrect shortest Path when asked for a valid shortest Path..");
         }

         p = undirectedGraph.shortestPathBetween("A", "F");

         // Try using impossible Path to an unconnected Vertex...
         if (p != null)
         {
            System.out.println(pathToString(p));
            failTest("Unconnected Undirected Graph with six Vertices returns shortest Path when no such Path exists.");
         }
         else
         {
            passTest("Unconnected Undirected Graph with six Vertices returns null shortest Path when no such Path exists.");
         }
      }
      catch (Exception e)
      {
         failTest("Unconnected Undirected Graph with six Vertices threw an exception when it should not have...");
      }

      // 5.2 Unconnected Undirected Graph with six Vertices and some Edges, but
      // no Edges to one Vertex.
      try
      {
         p = directedGraph.shortestPathBetween("A", "B");

         // Trying A-->B...
         if (p == null)
         {
            failTest("Unconnected Directed Graph with six Vertices returned null when asked for a valid shortest Path.");
         }
         else if (getPathWeightFromEdgesList(p) == 4)
         {
            passTest("Unconnected Directed Graph with six Vertices returned correct shortest Path.");
         }
         else
         {
            System.out.println(pathToString(p));
            failTest("Unconnected Directed Graph with six Vertices returned incorrect shortest Path.");
         }

         p = directedGraph.shortestPathBetween("B", "A");

         // Trying B-->A...
         if (p == null)
         {
            failTest("Unconnected Directed Graph with six Vertices returned null when asked for a valid shortest Path.");
         }
         else if (getPathWeightFromEdgesList(p) == 3)
         {
            passTest("Unconnected Directed Graph with six Vertices returned correct shortest Path when asked for a valid shortest Path.");
         }
         else
         {
            System.out.println(pathToString(p));
            failTest("Unconnected Directed Graph with six Vertices returned incorrect shortest Path when asked for a valid shortest Path.");
         }

         p = directedGraph.shortestPathBetween("A", "F");

         // Try using impossible Path to an unconnected Vertex...
         if (p != null)
         {
            System.out.println(pathToString(p));
            failTest("Unconnected Directed Graph with six Vertices returns shortest Path when no such Path exists.");
         }
         else
         {
            passTest("Unconnected Directed Graph with six Vertices returns null shortest Path when no such Path exists.");
         }

         p = directedGraph.shortestPathBetween("A", "C");

         // Some longer distance tests...
         if (directedGraph.shortestPathBetween("A", "C") == null)
         {
            failTest("Unconnected Directed Graph with six Vertices returned null shortest Path when asked for a valid shortest Path.");
         }
         else if (getPathWeightFromEdgesList(directedGraph
                  .shortestPathBetween("A", "C")) != 7)
         {
            failTest("Unconnected Directed Graph with six Vertices returned incorrect shortest Path when asked for a valid shortest Path.");
         }
         else
         {
            passTest("Unconnected Directed Graph with six Vertices returned correct shortest Path when asked for a valid shortest Path.");
         }

         p = directedGraph.shortestPathBetween("A", "E");

         // Some longer distance tests...
         if (p == null)
         {
            failTest("Unconnected Directed Graph with six Vertices returned null shortest Path when asked for a valid shortest Path.");
         }
         else if (getPathWeightFromEdgesList(p) != 7)
         {
            failTest("Unconnected Directed Graph with six Vertices returned incorrect shortest Path when asked for a valid shortest Path.");
         }
         else
         {
            passTest("Unconnected Directed Graph with six Vertices returned correct shortest Path when asked for a valid shortest Path.");
         }
      }
      catch (Exception e)
      {
         failTest("Unconnected Directed Graph with six Vertices threw an exception when it should not have.");
      }
   }

   /**
    * Test method for {@link Graph#shortestPathBetween(Object, Object)}.
    * 
    * Test for shortestPathBetween(T, T):
    * 
    * 6. Connected Graph with six Vertices.
    */
   @Test
   public void testShortest6()
   {
      List<Graph.Edge<String>> p;
      createGraphs();
      testMethod = "shortestPathBetween(T, T)";

      // Add Vertices A, B,...,E , F to the Graphs.
      for (int i = 0; i < 6; i++)
      {
         directedGraph.addVertex(String.valueOf(Character
                  .valueOf((char) ('A' + i))));
      }

      directedGraph.addEdge("A", "B", 4);
      directedGraph.addEdge("B", "C", 3);
      directedGraph.addEdge("B", "D", 1);
      directedGraph.addEdge("C", "D", 1);
      directedGraph.addEdge("D", "E", 2);
      directedGraph.addEdge("D", "A", 2);
      directedGraph.addEdge("D", "B", 1);
      directedGraph.addEdge("E", "A", 1);
      directedGraph.addEdge("F", "E", 1);

      p = directedGraph.shortestPathBetween("A", "F");

      // 6.1 Try using impossible Path to a stranded Vertex...
      if (p != null)
      {
         System.out.println(pathToString(p));
         failTest("Connected Directed Graph with six Vertices returns shortest Path when no such Path exists.");
      }
      else
      {
         passTest("Connected Directed Graph with six Vertices returns null shortest Path when no such Path exists.");
      }

      directedGraph.addEdge("A", "F", 3);
      p = directedGraph.shortestPathBetween("A", "F");

      if (p == null)
      {
         System.out.println(pathToString(p));
         failTest("Connected Directed Graph with six Vertices returns null shortest Path when asked for a valid shortest Path..");
      }
      else
      {
         passTest("Connected Directed Graph with six Vertices returns correct shortest Path when asked for a valid shortest Path..");
      }

   }

   /**
    * Test method for {@link Graph#shortestPathBetween(Object, Object)}.
    * 
    * Test for shortestPathBetween(T, T):
    * 
    * 7. Source and Destination as the same Vertex.
    */
   @Test
   public void testShortest7()
   {
      List<Graph.Edge<String>> p;
      createGraphs();
      testMethod = "shortestPathBetween(T, T)";

      // Add Vertices A, B,...,E , F to the Graphs.
      for (int i = 0; i < 2; i++)
      {
         undirectedGraph.addVertex(String.valueOf(Character
                  .valueOf((char) ('A' + i))));
         directedGraph.addVertex(String.valueOf(Character
                  .valueOf((char) ('A' + i))));
      }

      undirectedGraph.addEdge("A", "B", 4);
      directedGraph.addEdge("A", "B", 4);

      // 7.1 Try getting to/from the same node...
      try
      {
         p = directedGraph.shortestPathBetween("A", "A");
         if (p != null && getPathWeightFromEdgesList(p) == 0)
         {
            passTest("Connected Directed Graph with two Vertices returns empty list when asked for Path where source and destination were the same.");
         }
         else
         {
            System.out.println(pathToString(p));
            failTest("Connected Directed Graph with two Vertices returns null shortest Path when asked for Path where source and destination were the same.");
         }
      }
      catch (Exception e)
      {
         failTest("Connected Directed Graph with two Vertices returns null shortest Path when no such Path exists.");
      }
   }

   /**
    * Test method for {@link Graph#shortestPathBetween(Object, Object)}.
    * 
    * Test for shortestPathBetween(T, T):
    * 
    * 8. Connected, Directed Graph that has a stranded Vertex.
    */
   @Test
   public void testShortest8()
   {
      List<Graph.Edge<String>> p;
      createGraphs();
      testMethod = "shortestPathBetween(T, T)";

      // Add Vertices A, B,...,E , F to the Graphs.
      for (int i = 0; i < 6; i++)
      {
         undirectedGraph.addVertex(String.valueOf(Character
                  .valueOf((char) ('A' + i))));
         directedGraph.addVertex(String.valueOf(Character
                  .valueOf((char) ('A' + i))));
      }

      directedGraph.addEdge("A", "B", 4);
      directedGraph.addEdge("B", "C", 3);
      directedGraph.addEdge("B", "D", 1);
      directedGraph.addEdge("C", "D", 1);
      directedGraph.addEdge("D", "E", 2);
      directedGraph.addEdge("D", "A", 2);
      directedGraph.addEdge("D", "B", 1);
      directedGraph.addEdge("E", "A", 1);
      directedGraph.addEdge("F", "E", 1);
      // F is the stranded Vertex.

      try
      {
         p = directedGraph.shortestPathBetween("A", "F");

         // 8.1 Try to reach the stranded Vertex...
         if (p == null)
         {
            passTest("Connected Directed Graph with two Vertices returned null when asked for Path where Vertices and some Edges between the destination exist, but in the wrong direction");
         }
         else
         {
            System.out.println(pathToString(p));
            failTest("Connected Directed Graph with two Vertices returned non-null when asked for Path where Vertices and some Edges between the destination exist, but in the wrong direction");
         }
      }
      catch (Exception e)
      {
         failTest("Connected Directed Graph with six Vertices returns null shortest Path when no such Path exists.");
      }
   }

   /**
    * Test method for {@link Graph#shortestPathBetween(Object, Object)}.
    * 
    * @throws IOException
    */
   @Test
   public void testShortestPathBetweenCampus() throws IOException
   {
      testMethod = "shortestPathBetween(T, T)";
      Scanner in = new Scanner(new File(GRAPH_CSV_FILE));

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
   public void testGraphEdgeCompare()
   {
      testMethod = "Graph.compareEdge(Edge<T>)";
      Graph.Edge<String> e = new Graph.Edge<String>("A", "B", 1);
      Graph.Edge<String> f = new Graph.Edge<String>("A", "B", 1);
      Graph.Edge<String> g = new Graph.Edge<String>("A", "B", 2);
      Graph.Edge<String> h = new Graph.Edge<String>("A", "B", 3);

      if (e.compareTo(f) != 0)
      {
         failTest("Edges are equal, but compareTo() returns a non-zero value.");
      }

      if (g.compareTo(h) >= 0)
      {
         failTest("This Edge is less than the Edge passed as an argument, but compareTo() returns a non-negative value.");
      }

      if (g.compareTo(f) <= 0)
      {
         failTest("This Edge is greater than the Edge passed as an argument, but compareTo() returns a non-positive value.");
      }
   }

   /**
    * Test method for {@link Graph#getEdge(Object, Object)}.
    */
   @Test
   public void testGetEdge()
   {
      testMethod = "getEdge(T, T)";
      createGraphs();
      addVerticesToGraphs();

      undirectedGraph.addEdge("A", "B", 3);

      // Undirected test exists.
      if (undirectedGraph.getEdge("A", "B").getWeight() != 3)
      {
         failTest("Undirected Graph failed to properly return an Edge.");
      }
      else
      {
         passTest("Undirected Graph returned correct weight for requested Edge.");
      }

      undirectedGraph.removeEdge("A", "B");

      // Undirected test not exists.
      if (undirectedGraph.getEdge("A", "B") != null)
      {
         failTest("Undirected Graph failed to properly return null for a non-existent Edge.");
      }
      else
      {
         passTest("Undirected Graph properly returned null for a non-existent Edge.");
      }

      // Test source.equals(destination).
      if (undirectedGraph.getEdge("A", "A") != null)
      {
         passTest("Undirected Graph returned source.equals(destination) Edge.");
      }
      else
      {
         failTest("Undirected Graph failed to return source.equals(destination) Edge.");
      }

      directedGraph.addEdge("A", "B", 3);

      // Directed test exists.
      if (directedGraph.getEdge("A", "B").getWeight() != 3)
      {
         failTest("Directed Graph failed to properly return an Edge.");
      }
      else
      {
         passTest("Directed Graph returned correct weight for requested Edge.");
      }

      directedGraph.removeEdge("A", "B");

      // Directed test not exists.
      if (directedGraph.getEdge("A", "B") != null)
      {
         failTest("Directed Graph failed to properly return null for a non-existent Edge.");
      }
      else
      {
         passTest("Directed Graph properly returned null for a non-existent Edge.");
      }

      // Directed test source.equals(destination).
      if (directedGraph.getEdge("A", "A") != null)
      {
         passTest("Directed Graph returned source.equals(destination) Edge.");
      }
      else
      {
         failTest("Directed Graph failed to return source.equals(destination) Edge.");
      }
   }

   /**
    * Test method for {@link Graph#getEdges()}.
    */
   @Test
   public void testGetEdges()
   {
      testMethod = "getEdges()";
      createGraphs();
      addVerticesToGraphs();

      undirectedGraph.addEdge("A", "B", 3);
   }

   /**
    * Test method for {@link Graph#minimumSpanningTree()}.
    * 
    * TODO: Finish writing this test.
    */
   @Test
   public void testMinimumSpanningTree()
   {
      testMethod = "minimumSpanningTree()";
      createGraphs();
      addVerticesToGraphs();

      try
      {
         directedGraph.minimumSpanningTree();
         failTest("Directed Graph failed to throw exception when called.");
      }
      catch (IllegalStateException e)
      {
         passTest("Directed Graph correctly threw exception when called.");
      }

      createGraphs();
      addVerticesToGraphs();

      // Since we don't have any Edges in our Graph, but we have Vertices, we
      // should get a null return when we ask for the MST because it does not
      // exist.
      if (undirectedGraph.minimumSpanningTree() != null)
      {
         failTest("Undirected Graph did not return null when there was no such MST.");
      }

      createGraphs();
      addEdgesToGraphs();

      // Now, since we have Edges in our Graph, we should get a non-null return
      // when we ask for the MST because it does exist in this Graph.
      if (undirectedGraph.minimumSpanningTree() == null)
      {
         failTest("Undirected Graph returned null when there is a valid MST.");
      }

      try
      {
         undirectedGraph = Graph.fromCSVFile(false, new Scanner(new File(
                  GRAPH_CSV_FILE)));
      }
      catch (IOException e)
      {
         failTest("IO Exception in opening/initializing " + GRAPH_CSV_FILE);
      }

      // Test the CSV Graph to see if it has an MST.
      if ((undirectedGraph.minimumSpanningTree() == null) == CSV_HAS_MST)
      {
         failTest("undirectedGraph.minimumSpanningTree() returns NULL.");
      }
   }

   /**
    * Test method for {@link Graph#pathLength(List)}.
    * 
    * TODO: Verify this test after grading comes back.
    */
   @Test
   public void testPathLength()
   {
      testMethod = "pathLength(List<T>)";
      List<String> path = new ArrayList<>();
      createGraphs();
      addVerticesToGraphs();

      /*
       * Graphs depicted by the below code:
       * 
       * NOTE: undirectedGraph will be able to navigate from one node to any
       * other node. directedGraph, on the other hand, will not as it is a
       * directed Graph.
       * 
       * Graph Edges to be added: A-->B, A-->C B-->D, B-->E Essentially we are
       * creating a binary tree of nodes such that A is the root, B and C are
       * its direct children, and B has the children D and E. Thus, we should
       * only be able to get to C from A, and we have to go through B to get to
       * D or E.
       */

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
         failTest("Length of path A -1-> B -2-> D is not 3."
                  + System.lineSeparator());
      }

      // Test valid path for Undirected Graph.
      if (undirectedGraph.pathLength(path) != 3L)
      {
         failTest("Length of path A -1-> B -2-> D is not 3."
                  + System.lineSeparator());
      }

      // Alter the path to terminate at E instead of D.
      path.remove("D");
      path.add("E");

      // Test valid path for Directed Graph.
      if (directedGraph.pathLength(path) != 4L)
      {
         failTest("Length of path A -1-> B -3-> E is not 4."
                  + System.lineSeparator());
      }

      // Test valid path for Undirected Graph.
      if (undirectedGraph.pathLength(path) != 4L)
      {
         failTest("Length of path A -1-> B -3-> E is not 4."
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
         failTest("Length of path A -1-> E is not 1."
                  + System.lineSeparator());
      }

      // Test valid, shorter path for Undirected Graph.
      if (undirectedGraph.pathLength(path) != 1L)
      {
         failTest("Length of path A -1-> E is not 1."
                  + System.lineSeparator());
      }

      // Remove A-->E to create an invalid path.
      undirectedGraph.removeEdge("A", "E");
      directedGraph.removeEdge("A", "E");

      // Test directed Graph.
      if (directedGraph.pathLength(path) != INVALID_PATH)
      {
         failTest("Failed to return INVALID_PATH for an invalid path a directed Graph.");
      }

      // Test undirected Graph.
      if (undirectedGraph.pathLength(path) != INVALID_PATH)
      {
         failTest("Failed to return INVALID_PATH for an invalid path on an undirected Graph.");
      }
   }

   /**
    * Test method for {@link Graph#getVertices()}.
    */
   @Test
   public void testGetVertices()
   {
      testMethod = "getVertices()";
      createGraphs();

      // Test directed
      if (undirectedGraph.getVertices().size() != 0)
      {
         failTest("Empty undirected Graph has invalid vertices.");
      }

      // Test directed
      if (directedGraph.getVertices().size() != 0)
      {
         failTest("Empty directed Graph has invalid vertices.");
      }

      createGraphs();
      addEdgesToGraphs();

      // Test undirected.
      for (String s : vertices)
      {
         if (!undirectedGraph.getVertices().contains(s))
         {
            failTest("Undirected Graph missing vertice:" + s);
         }
      }

      // Test undirected.
      for (String s : vertices)
      {
         if (!directedGraph.getVertices().contains(s))
         {
            failTest("Directed Graph missing vertice:" + s);
         }
      }
   }

   /**
    * Test method for {@link Graph#getEdgeWeight(Object, Object)}.
    */
   @Test
   public void testGetEdgeWeight()
   {
      testMethod = "getEdgeWeight(T, T)";
      createGraphs();
      addEdgesToGraphs();

      // Test undirected Graph.
      if (undirectedGraph.getEdgeWeight("A", "B") != 1)
      {
         failTest("A->B != 1");
      }

      // Test directed Graph.
      if (directedGraph.getEdgeWeight("A", "B") != 1)
      {
         failTest("A->B != 1");
      }

      // Test undirected Graph.
      if (undirectedGraph.getEdgeWeight("A", "A") != MIN_EDGE_WEIGHT)
      {
         failTest("A->A != 0");
      }

      // Test directed Graph.
      if (directedGraph.getEdgeWeight("A", "A") != MIN_EDGE_WEIGHT)
      {
         failTest("A->A != 0");
      }

      // Clear Edges and create new Graphs/vertices.
      createGraphs();

      // Test undirected Graph.
      if (undirectedGraph.getEdgeWeight("A", "B") != INVALID_EDGE)
      {
         failTest("A->B exists in undirected Graph when it shouldn't.");
      }
      if (undirectedGraph.getEdgeWeight("B", "A") != INVALID_EDGE)
      {
         failTest("B->A exists in undirected Graph when it shouldn't.");
      }

      // Test directed Graph.
      if (directedGraph.getEdgeWeight("A", "B") != INVALID_EDGE)
      {
         failTest("A->B exists in undirected Graph when it shouldn't.");
      }
   }

   /**
    * Test method for {@link Graph#edgeExists(Object, Object)}.
    */
   @Test
   public void testEdgeExists()
   {
      testMethod = "edgeExists(T, T)";
      createGraphs();

      // Test undirected Graph null to null.
      if (undirectedGraph.edgeExists(null, null))
      {
         failTest("Undirected null to null test.");
      }

      // Test directed Graph null to null.
      if (directedGraph.edgeExists(null, null))
      {
         failTest("Directed null to null test.");
      }

      // Test undirected Graph for some invalid edges.
      for (int i = 0; i < vertices.size() - 1; i++)
      {
         if (undirectedGraph.edgeExists(vertices.get(i),
                  vertices.get(i + 1)))
         {
            failTest("Edge from: " + vertices.get(i) + " to: "
                     + vertices.get(i + 1)
                     + "\nExists in empty undirected Graph.");
         }
      }

      // Test directed Graph for some invalid Edges.
      for (int i = 0; i < vertices.size() - 1; i++)
      {
         if (directedGraph.edgeExists(vertices.get(i), vertices.get(i + 1)))
         {
            failTest("Edge from: " + vertices.get(i) + " to: "
                     + vertices.get(i + 1) + System.lineSeparator()
                     + "Exists in empty directed Graph.");
         }
      }

      addEdgesToGraphs();

      // Test undirected Graph for some invalid Edges.
      for (int i = 0; i < vertices.size() - 1; i++)
      {
         if (!undirectedGraph.edgeExists(vertices.get(i),
                  vertices.get(i + 1)))
         {
            failTest("Edge from: " + vertices.get(i) + " to: "
                     + vertices.get(i + 1) + System.lineSeparator()
                     + "Does not exist in undirected Graph.");
         }
      }

      // Test directed Graph for some invalid Edges.
      for (int i = 0; i < vertices.size() - 1; i++)
      {
         if (!directedGraph
                  .edgeExists(vertices.get(i), vertices.get(i + 1)))
         {
            failTest("Edge from: " + vertices.get(i) + " to: "
                     + vertices.get(i + 1) + System.lineSeparator()
                     + "Does not exist in directed Graph.");
         }
      }
   }

   /**
    * Test method for {@link Graph#addVertex(java.lang.Object)}.
    */
   @Test
   public void testAddVertex()
   {
      testMethod = "addVertex(T)";
      createGraphs();

      // Test undirected.
      undirectedGraph.addVertex("A");
      if (!undirectedGraph.getVertices().contains("A"))
      {
         failTest("Undirected Graph doesn't contain vertex: A");
      }

      // Test directed.
      directedGraph.addVertex("A");
      if (!directedGraph.getVertices().contains("A"))
      {
         failTest("Directed Graph doesn't contain vertex: A");
      }
   }

   /**
    * Test method for {@link Graph#removeVertex(java.lang.Object)}.
    */
   @Test
   public void testRemoveVertex()
   {
      testMethod = "removeVertex(T)";
      createGraphs();
      addEdgesToGraphs();

      // Test undirected.
      // Remove vertex, then test to see if the Graph still contains the vertex
      // in its vertices.
      undirectedGraph.removeVertex("A");

      if (undirectedGraph.getVertices().contains("A"))
      {
         failTest("Undirected Graph still contains vertex: A");
      }
      else
      {
         try
         {
            undirectedGraph.removeVertex("A");
            failTest("Exception not thrown for attempting to remove invalid Vertex on undirected Graph.");
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
         failTest("Directed Graph still contains vertex: A");
      }
      else
      {
         try
         {
            directedGraph.removeVertex("A");
            failTest("Exception not thrown for attempting to remove invalid Vertex on directed Graph.");
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
      testMethod = "addEdge(T, T, int)";
      createGraphs();

      // Make sure Edge is not in Graph.
      checkEdgeABExists(false);

      // Add Edge.
      addEdgesToGraphs();

      // Make sure Edge was added to both Graphs.
      checkEdgeABExists(true);

      // Clear, setup new vertices
      createGraphs();

      // Test undirected Graph.
      try
      {
         undirectedGraph.addEdge("A", "A", 1);
         failTest("Exception not thrown for undirected Graph adding Edge that links to itself.");
      }
      catch (Exception e)
      {
         // pass
      }

      // Test directed Graph.
      try
      {
         directedGraph.addEdge("A", "A", 1);
         failTest("Exception not thrown for directed Graph adding Edge that links to itself.");
      }
      catch (Exception e)
      {
         // pass
      }

      // Test undirected Graph.
      try
      {
         undirectedGraph.addEdge("A", "C", INVALID_EDGE);
         failTest("Exception not thrown for undirected Graph adding INVALID_EDGE weight.");
      }
      catch (Exception e)
      {
         // pass
      }

      // Test directed Graph.
      try
      {
         directedGraph.addEdge("A", "C", INVALID_EDGE);
         failTest("Exception not thrown for directed Graph adding INVALID_EDGE weight.");
      }
      catch (Exception e)
      {
         // pass
      }
   }

   /**
    * Test method for {@link Graph#removeEdge(Object, Object)}.
    */
   @Test
   public void testRemoveEdge()
   {
      testMethod = "removeEdge(T)";
      createGraphs();
      addEdgesToGraphs();

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
         failTest("A-->A does not exist in the directed Graph.");
      }

      // We should still be able to get from one node to itself.
      if (!undirectedGraph.edgeExists("A", "A")
               || undirectedGraph.getEdgeWeight("A", "A") != MIN_EDGE_WEIGHT)
      {
         failTest("A-->A does not exist in the undirected Graph.");
      }
   }

   /**
    * Helper method to aid in running shortest path tests.
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
      // System.out.println("TEST CASE: " + System.lineSeparator() + source
      // + " ---> " + destination);
      if (shortestPath != null
               && (expectedMinWeight == null || getPathWeightFromEdgesList(shortestPath) == expectedMinWeight))
      {
         // If the path is empty, then it must be a single path from the node to
         // itself (which we don't technically allow, but we will for the
         // purpose of fuzzy Hansen logic.
         // System.out.println("Total path weight: "
         // + getPathWeightFromEdgesList(shortestPath));
         // System.out.println(source + " --0--> " + destination
         // + System.lineSeparator());
         passTest(System.lineSeparator() + source + " --0--> "
                  + destination + System.lineSeparator()
                  + pathToString(shortestPath));
      }
      else if (shortestPath == null)
      {
         if (expectedMinWeight != null)
         {
            // System.out.println("Length: "
            // + getPathWeightFromEdgesList(shortestPath)
            // + System.lineSeparator() + "Expected Weight: "
            // + expectedMinWeight);
            failTest("Graph with expected min weight returned null shortest path.");
         }
         // System.out.println("NO SHORTEST PATH");
      }
      else
      {
         failTest("Path from " + source + " to " + destination
                  + " did not match the weight: " + expectedMinWeight
                  + "  passed as an arg.");
      }
   }

   /**
    * Helper method to display the Path of a given list of Edges. This method
    * assumes that we have at least ONE Edge.
    * 
    * @param path
    *           The path to measure.
    */
   private static String pathToString(List<Graph.Edge<String>> path)
   {
      StringBuilder s = new StringBuilder();

      s.append("Total Path weight: " + getPathWeightFromEdgesList(path)
               + System.lineSeparator());

      if (path.size() > 0)
      {
         // Display first Edge, then display the destinations of each following
         // Edge.
         s.append(path.get(0).getSource());

         for (Graph.Edge<String> edge : path)
         {
            s.append(" --" + edge.getWeight() + "--> "
                     + edge.getDestination());
         }
      }
      else
      {
         s.append("NULL PATH");
      }

      return s.toString();
   }

   /**
    * Helper method to add Vertices to each Graph.
    */
   private void addVerticesToGraphs()
   {
      for (String v : vertices)
      {
         undirectedGraph.addVertex(v);
         directedGraph.addVertex(v);
      }
   }

   /**
    * Helper to test whether Edge exists or not.
    * 
    * @param exists
    *           True if Edge should exist, false otherwise.
    */
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
         {
            failTest("Edge A->B doesn't exist in undirected Graph.");
         }
         else
         {
            failTest("Edge A->B still exists in undirected Graph.");
         }
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
         {
            failTest("Edge B->A doesn't exist in undirected Graph.");
         }
         else
         {
            failTest("Edge B->A still exists in undirected Graph.");
         }
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
         {
            failTest("Edge B->A doesn't exist in undirected Graph.");
         }
         else
         {
            failTest("Edge B->A still exists in undirected Graph.");
         }
      }
   }

   /**
    * Helper to display and fail the failure runs.
    */
   private void failTest(String testDescription)
   {
      System.out.println("FAILED TEST " + testMethod + " #" + testNum++
               + ": " + testDescription + System.lineSeparator());
      fail(testDescription);
   }

   /**
    * Helper to display success runs.
    */
   private void passTest(String testDescription)

   {
      System.out.println("PASSED TEST " + testMethod + " #" + testNum++
               + ": " + testDescription + System.lineSeparator());
   }

   /**
    * Helper method to quickly get us the length of a given list of Edges.
    * 
    * ASSUMPTIONS: We assume that the list of Edges has been verified against
    * the Graph.
    * 
    * @param path
    *           The path to measure.
    * @return The total length of the path.
    */
   private static int getPathWeightFromEdgesList(
            List<Graph.Edge<String>> path)
   {
      int totalPathWeight = 0;

      if (path != null)
      {
         // Iterate over each of the Edges and sum each weight.
         for (Graph.Edge<String> edge : path)
         {
            totalPathWeight += edge.getWeight();
         }
      }

      return totalPathWeight;
   }

   /**
    * Helper to add simple Edges to Graphs.
    */
   private void addEdgesToGraphs()
   {
      addVerticesToGraphs();

      // Map A->B, B->C...E->A, create a cycle.
      for (int i = 0; i < vertices.size(); i++)
      {
         undirectedGraph.addEdge(vertices.get(i),
                  vertices.get((i + 1) % vertices.size()), 1);

         directedGraph.addEdge(vertices.get(i),
                  vertices.get((i + 1) % vertices.size()), 1);
      }
   }

   // Commented out because it may be used later; code was written and I don't
   // like writing code twice.
   // /**
   // * Helper method to build a String representing the path passed as an
   // * argument.
   // *
   // * @param path
   // * The path to build the path.
   // * @return The String representing the path passed as an argument.
   // */
   // private String getPathOfVertices(List<String> path)
   // {
   // StringBuilder s = new StringBuilder();
   //
   // if (!path.isEmpty())
   // {
   // s.append(path.get(0));
   // }
   //
   // for (int i = 1; i < path.size(); i++)
   // {
   // s.append(" ---> " + path.get(i));
   // }
   //
   // return s.toString();
   // }
}

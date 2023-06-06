package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * A graph that is composed of a set of verticies and edges.
 *
 * <p>You must NOT change the signature of the existing methods or constructor of this class.
 *
 * @param <T> The type of each vertex, that have a total ordering.
 */
public class Graph<T extends Comparable<T>> {

  // comparator for comparing the values of two verticies
  Comparator<T> rootComparator =
      (string1, string2) -> {
        return Integer.parseInt(string1.toString()) - Integer.parseInt(string2.toString());
      };

  private Set<T> verticies;
  private Set<Edge<T>> edges;

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.verticies = verticies;
    this.edges = edges;
  }

  /**
   * Returns the set of roots of this graph.
   *
   * <p>A root is a vertex that has no incoming edges.
   *
   * <p>If the graph is an equivalence relation, then the roots are the minimum elements of each
   * equivalence class.
   *
   * @return The set of roots of this graph.
   */
  public Set<T> getRoots() {

    Set<T> roots;

    // If the graph is an equivalence relation, then the roots are the minimum elements of each
    // If the graph is not an equivalence relation, then the roots are the vertices with no incoming
    // edges
    if (isEquivalence()) {
      roots = new TreeSet<>();
      for (T vertex : verticies) {
        T min = getEquivalenceClass(vertex).stream().findFirst().get();
        roots.add(min);
      }
    } else {

      roots = new TreeSet<>(rootComparator);
      roots.addAll(verticies);
      for (Edge<T> edge : edges) {
        roots.remove(edge.getDestination());
      }
    }
    return roots;
  }

  /**
   * Returns true if the graph is reflexive.
   *
   * <p>A graph is reflexive if every vertex has a self-loop.
   *
   * @return True if the graph is reflexive.
   */
  public boolean isReflexive() {

    int verticeNumber = verticies.size();
    int reflexiveEdges = 0;

    // increments reflexiveEdges if the source and destination of an edge are the same
    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(edge.getDestination())) {
        reflexiveEdges++;
      }
    }

    // returns true if the number of reflexive edges is equal to the number of vertices
    return verticeNumber == reflexiveEdges;
  }

  /**
   * Returns true if the graph is symmetric.
   *
   * <p>A graph is symmetric if for every edge (A,B), there is also an edge (B,A).
   *
   * @return True if the graph is symmetric.
   */
  public boolean isSymmetric() {
    T source;
    T destination;

    // check for every edge if the source and destination are the same as the destination and source
    // of another edge
    for (Edge<T> e : edges) {
      int check = 0;
      source = e.getSource();
      destination = e.getDestination();

      // if the source and destination are the same as the destination and source of another edge,
      // increment check
      for (Edge<T> e2 : edges) {
        if (e2.getSource() == destination && e2.getDestination() == source) {
          check++;
        }
      }
      if (check == 0) {
        return false;
      }
    }
    return true;
  }

  /**
   * A graph is transitive if for every pair of edges (A,B) and (B,C), there is also an edge (A,C).
   *
   * @return True if the graph is transitive.
   */
  public boolean isTransitive() {

    // gets the source and destination of each edge
    for (Edge<T> e : edges) {
      T source1 = e.getSource();
      T destination1 = e.getDestination();

      // checks if the destination of an edge is the same as the source of another edge
      for (Edge<T> e2 : edges) {
        T source2 = e2.getSource();
        T destination2 = e2.getDestination();
        if (destination1 == source2) {
          int count = 0;

          // checks if the source of an edge is the same as the destination of another edge
          for (Edge<T> e3 : edges) {
            if (e3.getSource() == source1 && e3.getDestination() == destination2) {
              count++;
            }
          }
          if (count == 0) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * A graph is anti-symmetric if for every pair of edges (A,B) and (B,A), A=B.
   *
   * @return True if the graph is anti-symmetric.
   */
  public boolean isAntiSymmetric() {

    // creates a source and desitination edge
    for (Edge<T> e : edges) {
      T source = e.getSource();
      T destination = e.getDestination();

      // checks if the source and destination of an edge are the same as the destination and source
      for (Edge<T> e2 : edges) {
        T source2 = e2.getSource();
        T destination2 = e2.getDestination();

        // if the source and destination of an edge are the same as the destination and source of
        // another edge, then check if the source and destination are the same
        if (source == destination2 && destination == source2) {
          if (source != destination) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * A graph is an equivalence relation if it is reflexive, symmetric and transitive.
   *
   * @return True if the graph is an equivalence relation.
   */
  public boolean isEquivalence() {
    // if the graph is reflexive, symmetric and transitive, then it is an equivalence relation
    if (isReflexive() && isSymmetric() && isTransitive()) {
      return true;
    }
    return false;
  }

  /**
   * Returns the equivalence class of a vertex.
   *
   * @param vertex The vertex to find the equivalence class of.
   * @return The set of vertices that are in the equivalence class of the vertex.
   */
  public Set<T> getEquivalenceClass(T vertex) {

    Set<T> equivalenceClass = new TreeSet<>();

    // if the graph is not an equivalence relation, then return the vertex
    if (!isEquivalence()) {
      return equivalenceClass;
    }

    if (isEquivalence()) {
      // if the graph is an equivalence relation, then add the vertex to the equivalence class
      equivalenceClass.add(vertex);

      // for all edges in the graph
      for (Edge<T> e : edges) {
        // if the source of the edge is equal to the vertex, then add the destination to the
        // equivalence class

        if (e.getSource().equals(vertex)) {
          equivalenceClass.add(e.getDestination());
        }
        if (e.getDestination().equals(vertex)) {
          equivalenceClass.add(e.getSource());
        }
      }
    }

    return equivalenceClass;
  }

  /**
   * Goes through the graph in the order the first node is connected to them.
   *
   * @return A list of the vertices in the order they were visited.
   */
  public List<T> iterativeBreadthFirstSearch() {
    // List to store the visited vertices in order
    List<T> result = new ArrayList<>();
    // Set to keep track of visited vertices
    Set<T> visited = new HashSet<>();

    // Queue for breadth-first search
    Queue<T> queue = new Queue<>();

    Set<T> roots = getRoots();

    // adds all roots to the queue and visited set
    for (T root : roots) {
      if (!visited.contains(root)) {
        queue.enqueue(root);
        visited.add(root);
      }
      // Explore the graph until all vertices are visited
      while (!queue.isEmpty()) {
        T vertex = queue.dequeue();
        result.add(vertex);

        // Explore the neighbors of the current vertex
        for (Edge<T> edge : edges) {
          if (edge.getSource().equals(vertex)) {
            T destination = edge.getDestination();
            if (!visited.contains(destination)) {
              queue.enqueue(destination);
              visited.add(destination);
            }
          }
        }
      }
    }

    // Return the result list in the order of breadth-first search
    return result;
  }

  /**
   * Goes through the graph in the order each node is connected to them.
   *
   * @return A list of the vertices in the order they were visited.
   */
  public List<T> iterativeDepthFirstSearch() {
    List<T> result = new ArrayList<>(); // List to store the visited vertices in order
    Set<T> visited = new HashSet<>(); // Set to keep track of visited vertices
    Stack<T> stack = new Stack<>(); // Stack for depth-first search

    // Start with the roots of the graph
    Set<T> roots = getRoots();
    for (T root : roots) {
      if (!visited.contains(root)) {
        stack.push(root);

        while (!stack.isEmpty()) {
          T vertex = stack.pop();

          if (!visited.contains(vertex)) {
            result.add(vertex); // Add the vertex to the result list
            visited.add(vertex); // Mark the vertex as visited

            // Explore the neighbors of the current vertex
            List<T> neighbors = new ArrayList<>();
            for (Edge<T> edge : edges) {
              if (edge.getSource().equals(vertex)) {
                neighbors.add(edge.getDestination());
              }
            }

            // Reverse the order of neighbors manually
            List<T> reversedNeighbors = new ArrayList<>();
            for (int i = neighbors.size() - 1; i >= 0; i--) {
              reversedNeighbors.add(neighbors.get(i));
            }

            // Push the reversed neighbors to the stack
            for (T neighbor : reversedNeighbors) {
              if (!visited.contains(neighbor)) {
                stack.push(neighbor);
              }
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * Goes through the graph in the order the first node is connected to them using a helper function
   * to reduce complexity .
   *
   * @return A list of the vertices in the order they were visited.
   */
  public List<T> recursiveBreadthFirstSearch() {
    List<T> result = new ArrayList<>(); // List to store the visited vertices in order
    Set<T> visited = new HashSet<>(); // Set to keep track of visited vertices
    Queue<T> queue = new Queue<>(); // Queue for breadth-first search

    // Start with the roots of the graph
    Set<T> roots = getRoots();
    for (T root : roots) {

      // If the root is not visited, then perform recursive breadth-first search using the helper
      // function

      if (!visited.contains(root)) {
        searchForBreadth(root, queue, visited, result);
      }
    }

    return result;
  }

  /**
   * Helper function for recursive breadth-first search.
   *
   * @param vertex The vertex to start the search from.
   * @param queue The queue to use for the search.
   * @param visited The set to keep track of visited vertices.
   * @param result The list to store the visited vertices in order.
   */
  private void searchForBreadth(T vertex, Queue<T> queue, Set<T> visited, List<T> result) {
    if (visited.contains(vertex)) {
      return;
    }

    queue.enqueue(vertex); // Enqueue the vertex
    visited.add(vertex); // Mark the vertex as visited

    while (!queue.isEmpty()) {
      T current = queue.dequeue(); // Dequeue a vertex from the queue
      result.add(current); // Add the vertex to the result list

      // Explore the neighbors of the current vertex
      for (Edge<T> edge : edges) {
        if (edge.getSource().equals(current) && !visited.contains(edge.getDestination())) {
          T neighbor = edge.getDestination();
          queue.enqueue(neighbor); // Enqueue the neighbor if not visited
          visited.add(neighbor); // Mark the neighbor as visited
        }
      }
    }
  }

  /**
   * Goes through the graph in the order each node is connected to them using a helper function to
   * reduce complexity .
   *
   * @return A list of the vertices in the order they were visited.
   */
  public List<T> recursiveDepthFirstSearch() {

    List<T> result = new ArrayList<>(); // List to store the visited vertices in order
    Set<T> visited = new HashSet<>(); // Set to keep track of visited vertices

    // Start with the roots of the graph
    Set<T> roots = getRoots();
    for (T root : roots) {
      if (!visited.contains(root)) {
        searchForDepth(root, visited, result); // Perform recursive depth-first search
      }
    }

    return result;
  }

  /**
   * Helper function for recursive depth-first search.
   *
   * @param vertex The vertex to start the search from.
   * @param visited The set to keep track of visited vertices.
   * @param result The list to store the visited vertices in order.
   */
  private void searchForDepth(T vertex, Set<T> visited, List<T> result) {
    visited.add(vertex); // Mark the vertex as visited
    result.add(vertex); // Add the vertex to the result list

    // Explore the neighbors of the current vertex
    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(vertex) && !visited.contains(edge.getDestination())) {
        searchForDepth(edge.getDestination(), visited, result); // Recursively visit the neighbor
      }
    }
  }
}

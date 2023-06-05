package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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

  private Set<T> verticies;
  private Set<Edge<T>> edges;

  public Graph(Set<T> verticies, Set<Edge<T>> edges) {
    this.verticies = verticies;
    this.edges = edges;
  }

  public Set<T> getRoots() {

    Set<T> roots = new TreeSet<>();
    List<Integer> rootsList = new ArrayList<>();
    Set<T> tempRoots = new TreeSet<>();

    if (isEquivalence()) {
      roots = new TreeSet<>();
      for (T vertex : verticies) {
        T min = getEquivalenceClass(vertex).stream().findFirst().get();
        roots.add(min);
      }
    } else {
      // roots = new TreeSet<>();

      tempRoots = new TreeSet<>(verticies); // Initialize roots with all vertices
      for (Edge<T> edge : edges) {
        tempRoots.remove(edge.getDestination()); // Remove destinations from roots
      }
      for(T root : tempRoots) {
        int temp =  Integer.parseInt(root.toString());
        rootsList.add(temp);
       }
       
       Collections.sort(rootsList);
       for(int hold: rootsList) {
        T inRoot = (T)(Object)hold;
        roots.add(inRoot);
      }
  
    }

    return roots;
  }

  public boolean isReflexive() {

    int verticeNumber = verticies.size();
    int reflexiveEdges = 0;

    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(edge.getDestination())) {
        reflexiveEdges++;
      }
    }

    return verticeNumber == reflexiveEdges;
  }

  public boolean isSymmetric() {
    T source;
    T destination;
    // find out if there is an edge from A to B and from B to A

    for (Edge<T> e : edges) {
      int check = 0;
      source = e.getSource();
      destination = e.getDestination();
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

  public boolean isTransitive() {
    // for all vertices, u, v and w in the set of vertices, if (u,v) is an edge and (v,w) is also an
    // edge, then there must also be an edge (u,w).
    for (Edge<T> e : edges) {

      T source1 = e.getSource();
      T destination1 = e.getDestination();
      for (Edge<T> e2 : edges) {
        T source2 = e2.getSource();
        T destination2 = e2.getDestination();
        if (destination1 == source2) {
          int count = 0;
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

  public boolean isAntiSymmetric() {
    // for all vertices, u, v in the set of vertices, if (u,v) is an edge, and (v,u) is also an
    // edge, then vertex u is equal to the vertex v.
    for (Edge<T> e : edges) {
      T source = e.getSource();
      T destination = e.getDestination();
      for (Edge<T> e2 : edges) {
        T source2 = e2.getSource();
        T destination2 = e2.getDestination();
        if (source == destination2 && destination == source2) {
          if (source != destination) {
            return false;
          }
        }
      }
    }
    return true;
  }

  public boolean isEquivalence() {
    // if the graph is reflexive, symmetric and transitive, then it is an equivalence relation
    if (isReflexive() && isSymmetric() && isTransitive()) {
      return true;
    }
    return false;
  }

  public Set<T> getEquivalenceClass(T vertex) {
    // create a new tree set
    Set<T> equivalenceClass = new TreeSet<>();

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

  public List<T> iterativeBreadthFirstSearch() {
    List<T> result = new ArrayList<>(); // List to store the visited vertices in order
    Set<T> visited = new HashSet<>(); // Set to keep track of visited vertices
    Queue<T> queue = new Queue<>(); // Queue for breadth-first search

    // Start with the roots of the graph
    Set<T> roots = getRoots();
    for (T root : roots) {
      if (!visited.contains(root)) {
        queue.enqueue(root); // Enqueue the root vertex
        visited.add(root); // Mark the root vertex as visited
      }
    }

    while (!queue.isEmpty()) {
      T vertex = queue.dequeue(); // Dequeue a vertex from the queue
      result.add(vertex); // Add the vertex to the result list

      // Explore the neighbors of the current vertex
      for (Edge<T> edge : edges) {
        if (edge.getSource().equals(vertex)) {
          T destination = edge.getDestination();
          if (!visited.contains(destination)) {
            queue.enqueue(destination); // Enqueue the neighbor if not visited
            visited.add(destination); // Mark the neighbor as visited
          }
        }
      }
    }

    return result; // Return the list of visited vertices in order
  }

  public List<T> iterativeDepthFirstSearch() {
    List<T> result = new ArrayList<>(); // List to store the visited vertices in order
    Set<T> visited = new HashSet<>(); // Set to keep track of visited vertices
    Stack<T> stack = new Stack<>(); // Stack for depth-first search

    // Start with the roots of the graph
    Set<T> roots = getRoots();
    for (T root : roots) {
      if (!visited.contains(root)) {
        stack.push(root); // Push the root vertex to the stack

        while (!stack.isEmpty()) {
          T vertex = stack.pop(); // Pop a vertex from the stack

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
                stack.push(neighbor); // Push the neighbor to the stack
              }
            }
          }
        }
      }
    }

    return result; // Return the list of visited vertices in order
  }

  public List<T> recursiveBreadthFirstSearch() {
    List<T> result = new ArrayList<>(); // List to store the visited vertices in order
    Set<T> visited = new HashSet<>(); // Set to keep track of visited vertices
    Queue<T> queue = new Queue<>(); // Queue for breadth-first search

    // Start with the roots of the graph
    Set<T> roots = getRoots();
    for (T root : roots) {
      if (!visited.contains(root)) {
        recursiveBFS(root, queue, visited, result); // Perform recursive breadth-first search
      }
    }

    return result; // Return the list of visited vertices in order
  }

  private void recursiveBFS(T vertex, Queue<T> queue, Set<T> visited, List<T> result) {
    if (visited.contains(vertex)) {
      return; // If the vertex is already visited, return
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

  public List<T> recursiveDepthFirstSearch() {

    List<T> result = new ArrayList<>(); // List to store the visited vertices in order
    Set<T> visited = new HashSet<>(); // Set to keep track of visited vertices

    // Start with the roots of the graph
    Set<T> roots = getRoots();
    for (T root : roots) {
      if (!visited.contains(root)) {
        recursiveDFS(root, visited, result); // Perform recursive depth-first search
      }
    }

    return result; // Return the list of visited vertices in order
  }

  private void recursiveDFS(T vertex, Set<T> visited, List<T> result) {
    visited.add(vertex); // Mark the vertex as visited
    result.add(vertex); // Add the vertex to the result list

    // Explore the neighbors of the current vertex
    for (Edge<T> edge : edges) {
      if (edge.getSource().equals(vertex) && !visited.contains(edge.getDestination())) {
        recursiveDFS(edge.getDestination(), visited, result); // Recursively visit the neighbor
      }
    }
  }
}

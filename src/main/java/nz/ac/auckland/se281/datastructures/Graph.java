package nz.ac.auckland.se281.datastructures;

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

    Set<T> roots;

    if (isEquivalence()) {
      roots = new TreeSet<>();
      for (T vertex : verticies) {
        T min = getEquivalenceClass(vertex).stream().findFirst().get();
        roots.add(min);
      }
    } else {

      roots = new TreeSet<>(verticies); // Initialize roots with all vertices
      for (Edge<T> edge : edges) {
        roots.remove(edge.getDestination()); // Remove destinations from roots
      }

      if (roots.isEmpty()) {
        // If there are no vertices without incoming edges, find the minimum vertex
        T minVertex = null;
        for (T vertex : verticies) {
          if (minVertex == null || vertex.compareTo(minVertex) < 0) {
            minVertex = vertex;
          }
        }
        roots.add(minVertex);
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
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> iterativeDepthFirstSearch() {
    // TODO: Task 2.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveBreadthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }

  public List<T> recursiveDepthFirstSearch() {
    // TODO: Task 3.
    throw new UnsupportedOperationException();
  }
}

package nz.ac.auckland.se281.datastructures;

import java.util.List;
import java.util.Set;

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

    Set<T> roots = verticies;
    for (Edge<T> edge : edges) {
      roots.remove(edge.getDestination());
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
    // TODO: Task 1.
    throw new UnsupportedOperationException();
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

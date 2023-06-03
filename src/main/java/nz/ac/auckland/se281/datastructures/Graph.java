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

    throw new UnsupportedOperationException();
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
        if (e2.getSource().equals(destination) && e2.getDestination().equals(source)) {
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
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public boolean isAntiSymmetric() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
  }

  public boolean isEquivalence() {
    // TODO: Task 1.
    throw new UnsupportedOperationException();
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

package nz.ac.auckland.se281.datastructures;

/**
 * The Linked List Class Has only one head pointer to the start node Nodes are indexed starting from
 * 0. List goes from 0 to size-1
 *
 * <p>author Partha Roop
 */
public class LinkedList<T> implements List<T> {
  private Node<T> head;

  public LinkedList() {
    head = null;
  }

  // Key methods of the List interface

  /**
   * This method adds a node with specified data as the start node of the list
   *
   * @param data: an object of type T, which is the value of the Node
   * @return void
   */
  public void prepend(T data) {
    Node<T> n = new Node<T>(data);
    n.setNext(head);
    head = n;
  }

  /**
   * This method adds a node with specified data as the end node of the list
   *
   * @param data: an object of type T, which is the value of the Node
   * @return void
   */
  public void append(T data) {
    Node<T> newNode = new Node<T>(data);
    if (head == null) {
      head = newNode;
    } else {
      Node<T> current = head;
      while (current.getNext() != null) {
        current = current.getNext();
      }
      current.setNext(newNode);
    }
  }

  /**
   * This method fetches the value of a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return the value at the position pos
   * @throws InvalidPositionException if the provided position is invalid
   */
  public T fetch(int pos) {

    Node<T> current = head;
    int index = 0;
    while (current != null && index < pos) {
      current = current.getNext();
      index++;
    }

    return current.getValue();
  }

  /**
   * This method inserts a node with specified data at a given position
   *
   * @param pos: an integer, which is the position
   * @param data: an object of type T, which is the value of the Node
   * @return void
   * @throws InvalidPositionException if the provided position is invalid
   */
  public void insert(int pos, T data) {

    if (pos == 0) {
      prepend(data);
    } else {
      Node<T> newNode = new Node<T>(data);
      Node<T> current = head;
      int index = 0;
      while (current != null && index < pos - 1) {
        current = current.getNext();
        index++;
      }

      newNode.setNext(current.getNext());
      current.setNext(newNode);
    }
  }

  /**
   * This method removes a node at a given position
   *
   * @param pos: an integer, which is the position
   * @return void
   * @throws InvalidPositionException if the provided position is invalid
   */
  public void remove(int pos) {

    if (pos == 0) {
      head = head.getNext();
    } else {
      Node<T> current = head;
      int index = 0;
      while (current != null && index < pos - 1) {
        current = current.getNext();
        index++;
      }

      Node<T> nodeToRemove = current.getNext();
      current.setNext(nodeToRemove.getNext());
      nodeToRemove.setNext(null);
    }
  }

  /**
   * This method returns the size of the list
   *
   * @return the size of the list
   */
  public int size() {
    int count = 0;
    Node<T> current = head;
    while (current != null) {
      count++;
      current = current.getNext();
    }
    return count;
  }
}

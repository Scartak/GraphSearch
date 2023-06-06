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
   * @param data an object of type T
   * @return void
   */
  public void prepend(T data) {
    Node<T> n = new Node<T>(data);
    n.setNext(head);
    head = n;
  }

  /**
   * This method adds a node with specified data at the end of the list
   *
   * @param data an object of type T
   * @return void
   */
  public void append(T data) {

    Node<T> newNode = new Node<T>(data);

    // If the list is empty, then the new node is the head node
    // Otherwise, traverse to the end of the list and add the new node there
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
   * @param pos an integer, which is the position
   * @return the value at the position pos
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
   * @param pos an integer, which is the position
   * @param data an object of type T
   * @return void
   */
  public void insert(int pos, T data) {

    // If the position is 0, then we need to change the head
    // If the position is not 0, then we need to traverse the list
    if (pos == 0) {
      prepend(data);
    } else {
      Node<T> newNode = new Node<T>(data);
      Node<T> current = head;
      int index = 0;

      // Traverse the list until we reach the node before the one we want to insert
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
   * @param pos an integer, which is the position
   * @return void
   */
  public void remove(int pos) {

    // If the position is 0, then we need to change the head
    // If the position is not 0, then we need to traverse the list
    if (pos == 0) {
      head = head.getNext();
    } else {
      Node<T> current = head;
      int index = 0;

      // Traverse the list until we reach the node before the one we want to remove
      while (current != null && index < pos - 1) {
        current = current.getNext();
        index++;
      }

      // Remove the node
      Node<T> nodeToRemove = current.getNext();
      current.setNext(nodeToRemove.getNext());
      nodeToRemove.setNext(null);
    }
  }

  /**
   * This method returns the size of the list.
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

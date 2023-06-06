package nz.ac.auckland.se281.datastructures;

/**
 * The Linked List Class has only one head pointer to the start node. Nodes are indexed starting
 * from 0. The list goes from 0 to size-1.
 *
 * @param <T> the type of elements stored in the linked list
 * @author Partha Roop
 */
public class LinkedList<T> implements List<T> {
  private Node<T> head;

  /** Constructs an empty linked list. */
  public LinkedList() {
    head = null;
  }

  /**
   * Adds a node with the specified data as the start node of the list.
   *
   * @param data the data to be prepended
   */
  public void prepend(T data) {
    Node<T> n = new Node<T>(data);
    n.setNext(head);
    head = n;
  }

  /**
   * Adds a node with the specified data as the end node of the list.
   *
   * @param data the data to be appended
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
   * Retrieves the value of the node at the specified position in the list.
   *
   * @param pos the position of the node to fetch
   * @return the value at the specified position
   * @throws IndexOutOfBoundsException if the position is out of range
   */
  public T fetch(int pos) {

    // Traverse the list until we reach the node at the specified position
    // If we reach the end of the list before reaching the specified position, then the position is
    // invalid
    Node<T> current = head;
    int index = 0;
    while (current != null && index < pos) {
      current = current.getNext();
      index++;
    }
    if (current == null) {
      throw new IndexOutOfBoundsException("Invalid position");
    }
    return current.getValue();
  }

  /**
   * Inserts a node with the specified data at the given position in the list.
   *
   * @param pos the position at which to insert the node
   * @param data the data to be inserted
   * @throws IndexOutOfBoundsException if the position is out of range
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

      if (current == null) {
        throw new IndexOutOfBoundsException("Invalid position");
      }

      newNode.setNext(current.getNext());
      current.setNext(newNode);
    }
  }

  /**
   * Removes the node at the specified position in the list.
   *
   * @param pos the position of the node to remove
   * @throws IndexOutOfBoundsException if the position is out of range
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

      if (current == null || current.getNext() == null) {
        throw new IndexOutOfBoundsException("Invalid position");
      }

      // Remove the node
      Node<T> nodeToRemove = current.getNext();
      current.setNext(nodeToRemove.getNext());
      nodeToRemove.setNext(null);
    }
  }

  /**
   * Returns the size of the linked list.
   *
   * @return the size of the linked list
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

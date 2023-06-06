package nz.ac.auckland.se281.datastructures;

/**
 * A generic implementation of a queue data structure.
 *
 * @param <T> the type of elements stored in the queue
 */
public class Queue<T> {

  private int size; // size of the queue
  private Node<T> front; // front of the queue
  private Node<T> rear; // rear of the queue

  /**
   * A private inner class representing a node in the queue.
   *
   * @param <T> the type of data stored in the node
   */
  public class Node<T> {
    private T data;
    private Node<T> next;

    /**
     * Constructs a new node with the given data.
     *
     * @param data the data to be stored in the node
     */
    public Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  /** Constructs an empty queue. */
  public Queue() {
    front = null;
    rear = null;
    size = 0;
  }

  /**
   * Checks if the queue is empty.
   *
   * @return true if the queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the size of the queue.
   *
   * @return the size of the queue.
   */
  public int size() {
    return size;
  }

  /**
   * Adds an item to the rear of the queue.
   *
   * @param item the item to be added to the queue
   */
  public void enqueue(T item) {

    // create a new node
    Node<T> newNode = new Node<>(item);

    // if queue is empty, set front and rear to newNode and increase the size of the node
    // accordingly
    if (isEmpty()) {
      front = newNode;
      rear = newNode;
    } else {
      rear.next = newNode;
      rear = newNode;
    }
    size++;
  }

  /**
   * Removes and returns the item at the front of the queue.
   *
   * @return the item at the front of the queue
   * @throws IllegalStateException if the queue is empty
   */
  public T dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    T data = front.data;
    front = front.next;
    size--;
    if (isEmpty()) {
      rear = null;
    }
    return data;
  }

  /**
   * Returns the item at the front of the queue without removing it.
   *
   * @return the item at the front of the queue
   * @throws IllegalStateException if the queue is empty
   */
  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    return front.data;
  }
}

package nz.ac.auckland.se281.datastructures;

/**
 * Queue implementation using LinkedList.
 *
 * @param <T> The type of elements stored in the queue.
 */
public class Queue<T> {
  private LinkedList<T> linkedList;

  /** Constructs an empty queue. */
  public Queue() {
    linkedList = new LinkedList<>();
  }

  /**
   * Adds an item to the rear of the queue.
   *
   * @param item the item to be added to the queue
   */
  public void enqueue(T item) {
    linkedList.append(item);
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
    T item = linkedList.fetch(0);
    linkedList.remove(0);
    return item;
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
    return linkedList.fetch(0);
  }

  /**
   * Returns the size of the queue.
   *
   * @return the size of the queue.
   */
  public int size() {
    return linkedList.size();
  }

  /**
   * Checks if the queue is empty.
   *
   * @return true if the queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    return linkedList.size() == 0;
  }
}

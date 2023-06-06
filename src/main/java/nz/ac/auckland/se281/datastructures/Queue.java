package nz.ac.auckland.se281.datastructures;

/**
 * Queue implementation using LinkedList.
 *
 * @param <T> The type of elements stored in the queue.
 */
public class Queue<T> {
  private LinkedList<T> linkedList;

  public Queue() {
    linkedList = new LinkedList<>();
  }

  public void enqueue(T item) {
    linkedList.append(item);
  }

  public T dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    T item = linkedList.fetch(0);
    linkedList.remove(0);
    return item;
  }

  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    return linkedList.fetch(0);
  }

  public int size() {
    return linkedList.size();
  }

  public boolean isEmpty() {
    return linkedList.size() == 0;
  }
}

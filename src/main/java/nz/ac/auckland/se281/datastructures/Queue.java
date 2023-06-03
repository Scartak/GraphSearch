package nz.ac.auckland.se281.datastructures;

public class Queue<T> {
  private LinkedList<T> list;

  public Queue() {
    list = new LinkedList<>();
  }

  public void enqueue(T item) {
    list.append(item);
  }

  public T dequeue() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    T item = list.fetch(0);
    list.remove(0);
    return item;
  }

  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Queue is empty");
    }
    return list.fetch(0);
  }

  public boolean isEmpty() {
    return list.size() == 0;
  }

  public int size() {
    return list.size();
  }
}

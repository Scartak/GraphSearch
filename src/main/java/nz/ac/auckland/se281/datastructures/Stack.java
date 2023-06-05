package nz.ac.auckland.se281.datastructures;

public class Stack<T> {
  private LinkedList<T> list;

  public Stack() {
    list = new LinkedList<>();
  }

  public void push(T item) {
    list.prepend(item);
  }

  public T pop() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    T item = list.fetch(0);
    list.remove(0);
    return item;
  }

  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
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

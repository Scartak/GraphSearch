package nz.ac.auckland.se281.datastructures;

/**
 * A generic implementation of a stack data structure.
 *
 * @param <T> the type of elements stored in the stack
 */
public class Stack<T> {
  private LinkedList<T> list;

  /** Constructs an empty stack. */
  public Stack() {
    list = new LinkedList<>();
  }

  /**
   * Pushes an item onto the top of the stack.
   *
   * @param item the item to be pushed onto the stack
   */
  public void push(T item) {
    list.prepend(item);
  }

  /**
   * Removes and returns the item at the top of the stack.
   *
   * @return the item at the top of the stack
   * @throws IllegalStateException if the stack is empty
   */
  public T pop() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    T item = list.fetch(0);
    list.remove(0);
    return item;
  }

  /**
   * Returns the item at the top of the stack without removing it.
   *
   * @return the item at the top of the stack
   * @throws IllegalStateException if the stack is empty
   */
  public T peek() {
    if (isEmpty()) {
      throw new IllegalStateException("Stack is empty");
    }
    return list.fetch(0);
  }

  /**
   * Checks if the stack is empty.
   *
   * @return true if the stack is empty, false otherwise
   */
  public boolean isEmpty() {
    return list.size() == 0;
  }

  /**
   * Returns the number of elements in the stack.
   *
   * @return the number of elements in the stack
   */
  public int size() {
    return list.size();
  }
}

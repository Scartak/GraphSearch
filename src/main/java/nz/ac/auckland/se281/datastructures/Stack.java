package nz.ac.auckland.se281.datastructures;

public class Stack<T> {

  private Node<T> top; // top of the stack
  private int size; // size of the stack

  public Stack() {
    top = null;
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void push(T item) {
    Node<T> newNode = new Node<>(item);
    newNode.setNext(top);
    top = newNode;
    size++;
  }

  public T pop() {
    T value = top.getValue();
    top = top.getNext();
    size--;
    return value;
  }

  public T peek() {
    return top.getValue();
  }
}

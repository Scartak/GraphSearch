package nz.ac.auckland.se281.datastructures;

public class Queue<T> {

  private Node<T> front; // front of the queue
  private Node<T> rear; // rear of the queue
  private int size; // size of the queue

  public class Node<T> {
    private T data;
    private Node<T> next;

    public Node(T data) {
      this.data = data;
      this.next = null;
    }
  }

  public Queue() {
    front = null;
    rear = null;
    size = 0;
  }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void enqueue(T item) {
    Node<T> newNode = new Node<>(item);
    if (isEmpty()) {
      front = newNode;
      rear = newNode;
    } else {
      rear.next = newNode;
      rear = newNode;
    }
    size++;
  }

  public T dequeue() {
    T data = front.data;
    front = front.next;
    size--;
    if (isEmpty()) {
      rear = null;
    }
    return data;
  }

  public T peek() {

    return front.data;
  }
}

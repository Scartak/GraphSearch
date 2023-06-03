package nz.ac.auckland.se281.datastructures;

public class LinkedList<T> implements List<T> {
  private Node<T> head;

  public LinkedList() {
    head = null;
  }

  // Key methods of the List interface

  public void prepend(T data) {
    Node<T> n = new Node<T>(data);
    n.setNext(head);
    head = n;
  }

  public void append(T data) {
    Node<T> newNode = new Node<T>(data);

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

  public T fetch(int pos) {
    if (pos < 0 || pos >= size()) {
      throw new IndexOutOfBoundsException("Invalid position");
    }

    Node<T> current = head;
    for (int i = 0; i < pos; i++) {
      current = current.getNext();
    }
    return current.getValue();
  }

  public void insert(int pos, T data) {
    if (pos < 0 || pos > size()) {
      throw new IndexOutOfBoundsException("Invalid position");
    }

    if (pos == 0) {
      prepend(data);
    } else {
      Node<T> newNode = new Node<T>(data);
      Node<T> current = head;
      for (int i = 0; i < pos - 1; i++) {
        current = current.getNext();
      }
      newNode.setNext(current.getNext());
      current.setNext(newNode);
    }
  }

  public void remove(int pos) {
    if (pos < 0 || pos >= size()) {
      throw new IndexOutOfBoundsException("Invalid position");
    }

    if (pos == 0) {
      head = head.getNext();
    } else {
      Node<T> current = head;
      for (int i = 0; i < pos - 1; i++) {
        current = current.getNext();
      }
      current.setNext(current.getNext().getNext());
    }
  }

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

package nz.ac.auckland.se281.datastructures;

import java.util.ArrayList;
import java.util.List;

public class Queue<T> {

  List<T> queueList = new ArrayList<T>();

  public Queue() {}

  public void enqueue(T data) {
    queueList.add(data);
  }

  public T dequeue() {
    T ans = queueList.get(0);
    queueList.remove(0);
    return ans;
  }

  public T peek() {
    return queueList.get(0);
  }

  public boolean isEmpty() {
    if (queueList.size() == 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "" + queueList + "";
  }
}

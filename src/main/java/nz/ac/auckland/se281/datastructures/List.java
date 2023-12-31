package nz.ac.auckland.se281.datastructures;

/**
 * List Interface to be implemented by singly and Doubly Linked Lists.
 *
 * @author Partha Roop
 */
public interface List<T> {
  public void append(T item);

  public void prepend(T item);

  public T fetch(int pos);

  public void insert(int pos, T data);

  public void remove(int pos);

  public int size();
}

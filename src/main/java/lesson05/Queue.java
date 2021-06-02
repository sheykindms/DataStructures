package lesson05;

import java.util.*;

public class Queue<T> {

  private final List<T> queue;

  public Queue() {
    queue = new ArrayList<>();
  }

  public void enqueue(T item) {
    queue.add(item);
  }

  public T dequeue() {
    if (queue.isEmpty()) {
      return null;
    }
    T element = queue.get(0);
    queue.remove(0);
    return element;
  }

  public int size() {
    return queue.size();
  }
}

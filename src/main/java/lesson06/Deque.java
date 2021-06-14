package lesson06;

import java.util.*;

public class Deque<T> {

  private final List<T> deque;

  public Deque() {
    deque = new ArrayList<>();
  }

  public void addFront(T item) {
    deque.add(0, item);
  }

  public void addTail(T item) {
    deque.add(item);
  }

  public T removeFront() {
    if (deque.isEmpty()) {
      return null;
    }
    var elementToRemove = deque.get(0);
    deque.remove(elementToRemove);
    return elementToRemove;
  }

  public T removeTail() {
    if (deque.isEmpty()) {
      return null;
    }
    var elementToRemove = deque.get(deque.size() - 1);
    deque.remove(elementToRemove);
    return elementToRemove;
  }

  public int size() {
    return deque.size();
  }
}

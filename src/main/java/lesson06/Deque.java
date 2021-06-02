package lesson06;

import java.util.*;

public class Deque<T> {

  List<T> deque;

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
    T element = deque.get(0);
    deque.remove(element);
    return element;
  }

  public T removeTail() {
    if (deque.isEmpty()) {
      return null;
    }
    T element = deque.get(deque.size() - 1);
    deque.remove(element);
    return element;
  }

  public int size() {
    return deque.size();
  }
}

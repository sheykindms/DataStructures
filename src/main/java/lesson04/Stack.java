package lesson04;

import java.util.*;

public class Stack<T> {
  private final List<T> stack;

  public Stack() {
    stack = new ArrayList<>();
  }

  public int size() {
    return stack.size();
  }

  public T pop() {
    if (stack.isEmpty()) return null;
    final var elementToRemove = stack.get(0);
    stack.remove(0);
    return elementToRemove;
  }

  public void push(T val) {
    stack.add(0, val);
  }

  public T peek() {
    return stack.isEmpty() ? null : stack.get(0);
  }
}

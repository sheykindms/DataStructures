package lesson04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
  private Stack<Integer> stack;

  @BeforeEach
  void setUp() {
    stack = new Stack<>();
  }

  private void fillStackWithOneElement() {
    stack.push(10);
  }

  private void fillStackWithManyElements() {
    stack.push(10);
    stack.push(20);
    stack.push(30);
    stack.push(30);
    stack.push(40);
    stack.push(50);
  }

  @Test
  void sizeWhenEmpty() {
    assertEquals(0, stack.size());
  }

  @Test
  void sizeWhenOneElement() {
    fillStackWithOneElement();
    assertEquals(1, stack.size());
  }

  @Test
  void sizeWhenManyElements() {
    fillStackWithManyElements();
    assertEquals(6, stack.size());
  }

  @Test
  void popWhenEmpty() {
    assertNull(stack.pop());
  }

  @Test
  void popWhenOneElement() {
    fillStackWithOneElement();
    assertEquals(10, stack.pop());
  }

  @Test
  void popWhenManyElements() {
    fillStackWithManyElements();
    assertEquals(50, stack.pop());
    assertEquals(40, stack.pop());
    assertEquals(30, stack.pop());
    assertEquals(30, stack.pop());
    assertEquals(20, stack.pop());
    assertEquals(10, stack.pop());
  }

  @Test
  void peekWhenEmpty() {
    assertNull(stack.peek());
  }

  @Test
  void peekWhenOneElementInStackAndCount() {
    fillStackWithOneElement();
    assertEquals(10, stack.peek());
    assertEquals(1, stack.size());
  }

  @Test
  void peekWhenManyElementsInStackAndCount() {
    fillStackWithManyElements();
    assertEquals(50, stack.peek());
    assertEquals(6, stack.size());
  }

  @Test
  void pushTwoThenPopTwoThenCount() {
    stack.push(1);
    stack.push(2);
    assertEquals(2, stack.peek());
    assertEquals(2, stack.pop());
    assertEquals(1, stack.pop());
    assertEquals(0, stack.size());
  }
}

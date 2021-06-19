package lesson06;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {
  private Deque<Integer> deque;

  @BeforeEach
  void setUp() {
    deque = new Deque<>();
  }

  @Test
  void addFrontAndTailAndCount() {
    deque.addFront(1);
    deque.addFront(2);
    deque.addFront(3);
    deque.addTail(4);
    deque.addTail(5);
    deque.addTail(6);
    assertEquals(6, deque.size());
  }

  @Test
  void removeFrontAndTail() {
    deque.addTail(1);
    deque.addTail(2);
    deque.addTail(3);
    deque.addFront(4);
    deque.addFront(5);
    deque.addFront(6);
    assertEquals(6, deque.removeFront());
    assertEquals(3, deque.removeTail());
    assertEquals(4, deque.size());
  }

  @Test
  void removeWhenEmptyDeque() {
    assertNull(deque.removeFront());
    assertNull(deque.removeTail());
  }
}

package lesson05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

  private Queue<Integer> queue;

  @BeforeEach
  void setUp() {
    queue = new Queue<>();
  }

  void fillWithManyElements() {
    queue.enqueue(1);
    queue.enqueue(2);
    queue.enqueue(3);
    queue.enqueue(4);
    queue.enqueue(5);
    queue.enqueue(6);
  }

  @Test
  void test() {
    fillWithManyElements();
    assertEquals(6, queue.size());
    assertEquals(1, queue.dequeue());
    assertEquals(2, queue.dequeue());
    assertEquals(3, queue.dequeue());
    assertEquals(4, queue.dequeue());
    assertEquals(5, queue.dequeue());
    assertEquals(6, queue.dequeue());
    assertNull(queue.dequeue());
    assertEquals(0, queue.size());
  }
}

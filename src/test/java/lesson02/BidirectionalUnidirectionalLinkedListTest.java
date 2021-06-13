package lesson02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BidirectionalUnidirectionalLinkedListTest {

  private BidirectionalLinkedList list;

  @BeforeEach
  void setUp() {
    list = new BidirectionalLinkedList();
  }

  private void fillWithOneElement() {
    list.addInTail(new Node(1729));
  }

  private void fillWithManyElements() {
    list.addInTail(new Node(2));
    list.addInTail(new Node(11));
    list.addInTail(new Node(17));
    list.addInTail(new Node(17));
    list.addInTail(new Node(29));
    list.addInTail(new Node(41));
  }

  @Test
  void findWhenEmptyList() {
    assertNull(list.get(1729));
    assertNull(list.get(2));
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void findWhenOneElementList() {
    fillWithOneElement();
    assertEquals(1729, list.get(1729).value);
    assertEquals(list.head, list.tail);
    assertNull(list.get(1729).next);
    assertNull(list.get(1729).prev);
  }

  @Test
  void findWhenManyElements() {
    fillWithManyElements();
    assertEquals(2, list.get(2).value);
    assertEquals(17, list.get(17).value);
    assertEquals(41, list.get(41).value);
  }

  @Test
  void findAllWhenListEmpty() {
    assertEquals(0, list.getAll(1729).size());
    assertEquals(0, list.getAll(17).size());
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void findAllWhenOneElement() {
    fillWithOneElement();
    assertEquals(1, list.getAll(1729).size());
    assertEquals(list.head, list.tail);
    assertNull(list.head.prev);
    assertNull(list.head.next);
  }

  @Test
  void findAllWhenManyElements() {
    fillWithManyElements();
    assertEquals(1, list.getAll(2).size());
    assertEquals(2, list.getAll(17).size());
    assertEquals(17, list.getAll(17).get(0).value);
  }

  @Test
  void removeWhenListEmpty() {
    assertFalse(list.remove(2));
    assertFalse(list.remove(1729));
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void removeWhenOneElement() {
    fillWithOneElement();
    assertFalse(list.remove(2));
    assertTrue(list.remove(1729));
    assertEquals(0, list.count());
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void removeAllOneByOneWhenManyElements() {
    fillWithManyElements();
    assertTrue(list.remove(2));
    assertTrue(list.remove(41));
    assertTrue(list.remove(11));
    assertTrue(list.remove(29));
    assertEquals(list.head.value, list.tail.value);
    assertEquals(2, list.count());
    assertTrue(list.remove(17));
    assertTrue(list.remove(17));
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void removeWhenManyElements() {
    fillWithManyElements();
    assertTrue(list.remove(2));
    assertFalse(list.remove(1729));
  }

  @Test
  void removeFirstWhenTwoElementsInList() {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    list.addInTail(node1);
    list.addInTail(node2);
    list.remove(1);
    assertNull(list.head.prev);
    assertNull(list.head.next);
    assertNull(list.tail.next);
    assertNull(list.tail.prev);
    assertEquals(list.head, list.tail);
    assertEquals(1, list.count());
  }

  @Test
  void removeAllWhenListEmpty() {
    list.removeAll(2);
    list.removeAll(1729);
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void removeAllWhenOneElement() {
    fillWithOneElement();
    list.removeAll(1729);
    assertNull(list.head);
    assertNull(list.tail);
    assertEquals(0, list.count());
  }

  @Test
  void removeAllWhenManyElements() {
    fillWithManyElements();
    list.removeAll(17);
    assertNull(list.get(17));
    assertEquals(4, list.count());
  }

  @Test
  void clearWhenListEmpty() {
    list.clear();
    assertNull(list.head);
    assertNull(list.tail);
    assertEquals(0, list.count());
  }

  @Test
  void clearWhenOneElement() {
    fillWithOneElement();
    list.clear();
    assertNull(list.head);
    assertNull(list.tail);
    assertEquals(0, list.count());
  }

  @Test
  void clearWhenManyElements() {
    fillWithManyElements();
    list.clear();
    assertNull(list.head);
    assertNull(list.tail);
    assertEquals(0, list.count());
  }

  @Test
  void countWhenListEmpty() {
    assertEquals(0, list.count());
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void countWhenOneElement() {
    fillWithOneElement();
    assertEquals(1, list.count());
    assertEquals(list.head, list.tail);
    assertNull(list.head.prev);
    assertNull(list.head.next);
  }

  @Test
  void countWhenManyElements() {
    fillWithManyElements();
    assertEquals(6, list.count());
    assertNull(list.head.prev);
    assertNull(list.tail.next);
  }

  @Test
  void insertAfterWhenListEmpty() {
    list.insertAfter(null, new Node(200));
    assertEquals(1, list.count());
    assertEquals(list.head, list.tail);
    assertNull(list.head.prev);
    assertNull(list.head.next);
    assertNull(list.tail.prev);
    assertNull(list.tail.next);
  }

  @Test
  void insertAfterWhenOneElement() {
    fillWithOneElement();
    Node nodeToInsert = new Node(200);
    list.insertAfter(list.get(1729), nodeToInsert);
    assertEquals(2, list.count());
    assertEquals(list.head.next, list.tail);
    assertEquals(list.tail.prev, list.head);
    assertNull(list.tail.next);
    assertNull(list.head.prev);
  }

  @Test
  void insertAfterAsFirstWhenManyElements() {
    fillWithManyElements();
    list.insertAfter(null, new Node(200));
    assertEquals(7, list.count());
    assertEquals(200, list.head.value);
    assertEquals(2, list.head.next.value);
    assertNull(list.head.prev);
    assertNull(list.tail.next);
  }

  @Test
  void insertAfterAsLastWhenManyElements() {
    fillWithManyElements();
    list.insertAfter(list.get(41), new Node(200));
    assertEquals(7, list.count());
    assertEquals(200, list.tail.value);
    assertEquals(41, list.tail.prev.value);
    assertNull(list.head.prev);
    assertNull(list.tail.next);
  }

  @Test
  void insertAfterFourElementRemoveFirstAndLastAndCount() {
    fillWithOneElement();
    Node one = new Node(1);
    Node two = new Node(2);
    Node three = new Node(3);
    Node four = new Node(4);
    list.insertAfter(list.get(1729), one);
    list.insertAfter(null, two);
    list.insertAfter(one, three);
    list.insertAfter(one, four);

    assertEquals(four, list.tail.prev);
    assertEquals(three, list.tail);
    assertNull(three.next);
    assertEquals(two, list.head);
    assertNull(two.prev);
    assertEquals(four, three.prev);
    assertEquals(list.get(1729), two.next);
    assertEquals(one, list.get(1729).next);
    assertEquals(four, one.next);
    assertEquals(one, four.prev);

    assertTrue(list.remove(two.value));
    assertEquals(list.get(1729), list.head);
    assertTrue(list.remove(three.value));
    assertEquals(four, list.tail);
    assertNull(four.next);
    assertNull(list.get(1729).prev);
  }
}

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
    list.addNodeInTail(new Node(1729));
  }

  private void fillWithManyElements() {
    list.addNodeInTail(new Node(2));
    list.addNodeInTail(new Node(11));
    list.addNodeInTail(new Node(17));
    list.addNodeInTail(new Node(17));
    list.addNodeInTail(new Node(29));
    list.addNodeInTail(new Node(41));
  }

  @Test
  void findWhenEmptyList() {
    assertNull(list.getNodeByValue(1729));
    assertNull(list.getNodeByValue(2));
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void findWhenOneElementList() {
    fillWithOneElement();
    assertEquals(1729, list.getNodeByValue(1729).value);
    assertEquals(list.head, list.tail);
    assertNull(list.getNodeByValue(1729).next);
    assertNull(list.getNodeByValue(1729).prev);
  }

  @Test
  void findWhenManyElements() {
    fillWithManyElements();
    assertEquals(2, list.getNodeByValue(2).value);
    assertEquals(17, list.getNodeByValue(17).value);
    assertEquals(41, list.getNodeByValue(41).value);
  }

  @Test
  void findAllWhenListEmpty() {
    assertEquals(0, list.getAllNodesByValue(1729).size());
    assertEquals(0, list.getAllNodesByValue(17).size());
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void findAllWhenOneElement() {
    fillWithOneElement();
    assertEquals(1, list.getAllNodesByValue(1729).size());
    assertEquals(list.head, list.tail);
    assertNull(list.head.prev);
    assertNull(list.head.next);
  }

  @Test
  void findAllWhenManyElements() {
    fillWithManyElements();
    assertEquals(1, list.getAllNodesByValue(2).size());
    assertEquals(2, list.getAllNodesByValue(17).size());
    assertEquals(17, list.getAllNodesByValue(17).get(0).value);
  }

  @Test
  void removeWhenListEmpty() {
    assertFalse(list.removeNodeByValue(2));
    assertFalse(list.removeNodeByValue(1729));
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void removeWhenOneElement() {
    fillWithOneElement();
    assertFalse(list.removeNodeByValue(2));
    assertTrue(list.removeNodeByValue(1729));
    assertEquals(0, list.countNodes());
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void removeAllOneByOneWhenManyElements() {
    fillWithManyElements();
    assertTrue(list.removeNodeByValue(2));
    assertTrue(list.removeNodeByValue(41));
    assertTrue(list.removeNodeByValue(11));
    assertTrue(list.removeNodeByValue(29));
    assertEquals(list.head.value, list.tail.value);
    assertEquals(2, list.countNodes());
    assertTrue(list.removeNodeByValue(17));
    assertTrue(list.removeNodeByValue(17));
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void removeWhenManyElements() {
    fillWithManyElements();
    assertTrue(list.removeNodeByValue(2));
    assertFalse(list.removeNodeByValue(1729));
  }

  @Test
  void removeFirstWhenTwoElementsInList() {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    list.addNodeInTail(node1);
    list.addNodeInTail(node2);
    list.removeNodeByValue(1);
    assertNull(list.head.prev);
    assertNull(list.head.next);
    assertNull(list.tail.next);
    assertNull(list.tail.prev);
    assertEquals(list.head, list.tail);
    assertEquals(1, list.countNodes());
  }

  @Test
  void removeAllWhenListEmpty() {
    list.removeAllNodesByValue(2);
    list.removeAllNodesByValue(1729);
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void removeAllWhenOneElement() {
    fillWithOneElement();
    list.removeAllNodesByValue(1729);
    assertNull(list.head);
    assertNull(list.tail);
    assertEquals(0, list.countNodes());
  }

  @Test
  void removeAllWhenManyElements() {
    fillWithManyElements();
    list.removeAllNodesByValue(17);
    assertNull(list.getNodeByValue(17));
    assertEquals(4, list.countNodes());
  }

  @Test
  void clearWhenListEmpty() {
    list.clearList();
    assertNull(list.head);
    assertNull(list.tail);
    assertEquals(0, list.countNodes());
  }

  @Test
  void clearWhenOneElement() {
    fillWithOneElement();
    list.clearList();
    assertNull(list.head);
    assertNull(list.tail);
    assertEquals(0, list.countNodes());
  }

  @Test
  void clearWhenManyElements() {
    fillWithManyElements();
    list.clearList();
    assertNull(list.head);
    assertNull(list.tail);
    assertEquals(0, list.countNodes());
  }

  @Test
  void countWhenListEmpty() {
    assertEquals(0, list.countNodes());
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void countWhenOneElement() {
    fillWithOneElement();
    assertEquals(1, list.countNodes());
    assertEquals(list.head, list.tail);
    assertNull(list.head.prev);
    assertNull(list.head.next);
  }

  @Test
  void countWhenManyElements() {
    fillWithManyElements();
    assertEquals(6, list.countNodes());
    assertNull(list.head.prev);
    assertNull(list.tail.next);
  }

  @Test
  void insertAfterWhenListEmpty() {
    list.insertAfter(null, new Node(200));
    assertEquals(1, list.countNodes());
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
    list.insertAfter(list.getNodeByValue(1729), nodeToInsert);
    assertEquals(2, list.countNodes());
    assertEquals(list.head.next, list.tail);
    assertEquals(list.tail.prev, list.head);
    assertNull(list.tail.next);
    assertNull(list.head.prev);
  }

  @Test
  void insertAfterAsFirstWhenManyElements() {
    fillWithManyElements();
    list.insertAfter(null, new Node(200));
    assertEquals(7, list.countNodes());
    assertEquals(200, list.head.value);
    assertEquals(2, list.head.next.value);
    assertNull(list.head.prev);
    assertNull(list.tail.next);
  }

  @Test
  void insertAfterAsLastWhenManyElements() {
    fillWithManyElements();
    list.insertAfter(list.getNodeByValue(41), new Node(200));
    assertEquals(7, list.countNodes());
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
    list.insertAfter(list.getNodeByValue(1729), one);
    list.insertAfter(null, two);
    list.insertAfter(one, three);
    list.insertAfter(one, four);

    assertEquals(four, list.tail.prev);
    assertEquals(three, list.tail);
    assertNull(three.next);
    assertEquals(two, list.head);
    assertNull(two.prev);
    assertEquals(four, three.prev);
    assertEquals(list.getNodeByValue(1729), two.next);
    assertEquals(one, list.getNodeByValue(1729).next);
    assertEquals(four, one.next);
    assertEquals(one, four.prev);

    assertTrue(list.removeNodeByValue(two.value));
    assertEquals(list.getNodeByValue(1729), list.head);
    assertTrue(list.removeNodeByValue(three.value));
    assertEquals(four, list.tail);
    assertNull(four.next);
    assertNull(list.getNodeByValue(1729).prev);
  }
}

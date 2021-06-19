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
    assertNull(list.getHead());
    assertNull(list.getTail());
  }

  @Test
  void findWhenOneElementList() {
    fillWithOneElement();
    assertEquals(1729, list.getNodeByValue(1729).getValue());
    assertEquals(list.getHead(), list.getTail());
    assertNull(list.getNodeByValue(1729).getNext());
    assertNull(list.getNodeByValue(1729).getPrev());
  }

  @Test
  void findWhenManyElements() {
    fillWithManyElements();
    assertEquals(2, list.getNodeByValue(2).getValue());
    assertEquals(17, list.getNodeByValue(17).getValue());
    assertEquals(41, list.getNodeByValue(41).getValue());
  }

  @Test
  void findAllWhenListEmpty() {
    assertEquals(0, list.getAllNodesByValue(1729).size());
    assertEquals(0, list.getAllNodesByValue(17).size());
    assertNull(list.getHead());
    assertNull(list.getTail());
  }

  @Test
  void findAllWhenOneElement() {
    fillWithOneElement();
    assertEquals(1, list.getAllNodesByValue(1729).size());
    assertEquals(list.getHead(), list.getTail());
    assertNull(list.getHead().getPrev());
    assertNull(list.getHead().getNext());
  }

  @Test
  void findAllWhenManyElements() {
    fillWithManyElements();
    assertEquals(1, list.getAllNodesByValue(2).size());
    assertEquals(2, list.getAllNodesByValue(17).size());
    assertEquals(17, list.getAllNodesByValue(17).get(0).getValue());
  }

  @Test
  void removeWhenListEmpty() {
    assertFalse(list.removeNodeByValue(2));
    assertFalse(list.removeNodeByValue(1729));
    assertNull(list.getHead());
    assertNull(list.getTail());
  }

  @Test
  void removeWhenOneElement() {
    fillWithOneElement();
    assertFalse(list.removeNodeByValue(2));
    assertTrue(list.removeNodeByValue(1729));
    assertEquals(0, list.countNodes());
    assertNull(list.getHead());
    assertNull(list.getTail());
  }

  @Test
  void removeAllOneByOneWhenManyElements() {
    fillWithManyElements();
    assertTrue(list.removeNodeByValue(2));
    assertTrue(list.removeNodeByValue(41));
    assertTrue(list.removeNodeByValue(11));
    assertTrue(list.removeNodeByValue(29));
    assertEquals(list.getHead().getValue(), list.getTail().getValue());
    assertEquals(2, list.countNodes());
    assertTrue(list.removeNodeByValue(17));
    assertTrue(list.removeNodeByValue(17));
    assertNull(list.getHead());
    assertNull(list.getTail());
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
    assertNull(list.getHead().getPrev());
    assertNull(list.getHead().getNext());
    assertNull(list.getTail().getNext());
    assertNull(list.getTail().getPrev());
    assertEquals(list.getHead(), list.getTail());
    assertEquals(1, list.countNodes());
  }

  @Test
  void removeAllWhenListEmpty() {
    list.removeAllNodesByValue(2);
    list.removeAllNodesByValue(1729);
    assertNull(list.getHead());
    assertNull(list.getTail());
  }

  @Test
  void removeAllWhenOneElement() {
    fillWithOneElement();
    list.removeAllNodesByValue(1729);
    assertNull(list.getHead());
    assertNull(list.getTail());
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
    assertNull(list.getHead());
    assertNull(list.getTail());
    assertEquals(0, list.countNodes());
  }

  @Test
  void clearWhenOneElement() {
    fillWithOneElement();
    list.clearList();
    assertNull(list.getHead());
    assertNull(list.getTail());
    assertEquals(0, list.countNodes());
  }

  @Test
  void clearWhenManyElements() {
    fillWithManyElements();
    list.clearList();
    assertNull(list.getHead());
    assertNull(list.getTail());
    assertEquals(0, list.countNodes());
  }

  @Test
  void countWhenListEmpty() {
    assertEquals(0, list.countNodes());
    assertNull(list.getHead());
    assertNull(list.getTail());
  }

  @Test
  void countWhenOneElement() {
    fillWithOneElement();
    assertEquals(1, list.countNodes());
    assertEquals(list.getHead(), list.getTail());
    assertNull(list.getHead().getPrev());
    assertNull(list.getHead().getNext());
  }

  @Test
  void countWhenManyElements() {
    fillWithManyElements();
    assertEquals(6, list.countNodes());
    assertNull(list.getHead().getPrev());
    assertNull(list.getTail().getNext());
  }

  @Test
  void insertAfterWhenListEmpty() {
    list.insertAfter(null, new Node(200));
    assertEquals(1, list.countNodes());
    assertEquals(list.getHead(), list.getTail());
    assertNull(list.getHead().getPrev());
    assertNull(list.getHead().getNext());
    assertNull(list.getTail().getPrev());
    assertNull(list.getTail().getNext());
  }

  @Test
  void insertAfterWhenOneElement() {
    fillWithOneElement();
    Node nodeToInsert = new Node(200);
    list.insertAfter(list.getNodeByValue(1729), nodeToInsert);
    assertEquals(2, list.countNodes());
    assertEquals(list.getHead().getNext(), list.getTail());
    assertEquals(list.getTail().getPrev(), list.getHead());
    assertNull(list.getTail().getNext());
    assertNull(list.getHead().getPrev());
  }

  @Test
  void insertAfterAsFirstWhenManyElements() {
    fillWithManyElements();
    list.insertAfter(null, new Node(200));
    assertEquals(7, list.countNodes());
    assertEquals(200, list.getHead().getValue());
    assertEquals(2, list.getHead().getNext().getValue());
    assertNull(list.getHead().getPrev());
    assertNull(list.getTail().getNext());
  }

  @Test
  void insertAfterAsLastWhenManyElements() {
    fillWithManyElements();
    list.insertAfter(list.getNodeByValue(41), new Node(200));
    assertEquals(7, list.countNodes());
    assertEquals(200, list.getTail().getValue());
    assertEquals(41, list.getTail().getPrev().getValue());
    assertNull(list.getHead().getPrev());
    assertNull(list.getTail().getNext());
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

    assertEquals(four, list.getTail().getPrev());
    assertEquals(three, list.getTail());
    assertNull(three.getNext());
    assertEquals(two, list.getHead());
    assertNull(two.getPrev());
    assertEquals(four, three.getPrev());
    assertEquals(list.getNodeByValue(1729), two.getNext());
    assertEquals(one, list.getNodeByValue(1729).getNext());
    assertEquals(four, one.getNext());
    assertEquals(one, four.getPrev());

    assertTrue(list.removeNodeByValue(two.getValue()));
    assertEquals(list.getNodeByValue(1729), list.getHead());
    assertTrue(list.removeNodeByValue(three.getValue()));
    assertEquals(four, list.getTail());
    assertNull(four.getNext());
    assertNull(list.getNodeByValue(1729).getPrev());
  }
}

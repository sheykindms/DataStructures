package lesson01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnidirectionalLinkedListTest {

  UnidirectionalLinkedList list;

  @BeforeEach
  void setUp() {
    list = new UnidirectionalLinkedList();
  }

  private void fillListWithOneElement() {
    list.addNodeInTail(new Node(1729));
  }

  private void fillListWithManyElements() {
    list.addNodeInTail(new Node(2));
    list.addNodeInTail(new Node(11));
    list.addNodeInTail(new Node(17));
    list.addNodeInTail(new Node(17));
    list.addNodeInTail(new Node(29));
    list.addNodeInTail(new Node(41));
  }

  @Test
  void removeFromListWithOneNode() {
    fillListWithOneElement();
    list.removeNodeByValue(1729);
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void removeFromListWithOneNodeIfNotExists() {
    fillListWithOneElement();
    assertFalse(list.removeNodeByValue(1));
    assertEquals(list.head, list.getNodeByValue(1729));
    assertEquals(list.tail, list.getNodeByValue(1729));
  }

  @Test
  void removeFromListWithManyNodes() {
    fillListWithManyElements();
    list.removeNodeByValue(2);
    list.removeNodeByValue(41);
    list.removeNodeByValue(17);
    assertEquals(list.getNodeByValue(11), list.head);
    assertEquals(list.getNodeByValue(29), list.tail);
  }

  @Test
  void removeAllFromListWithOneNode() {
    fillListWithOneElement();
    list.removeAllNodesByValue(1729);
    assertNull(list.getNodeByValue(1729));
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void removeAllFromListWithManyNodes() {
    fillListWithManyElements();
    list.removeAllNodesByValue(17);
    list.removeAllNodesByValue(41);
    assertEquals(list.getNodeByValue(2), list.head);
    assertEquals(list.getNodeByValue(29), list.tail);
    assertNull(list.getNodeByValue(17));
  }

  @Test
  void removeFromListIfListEmpty() {
    assertFalse(list.removeNodeByValue(10));
  }

  @Test
  void removeFromList() {
    fillListWithManyElements();
    assertTrue(list.removeNodeByValue(17));
    assertTrue(list.removeNodeByValue(2));
    assertTrue(list.removeNodeByValue(29));
  }

  @Test
  void removeFromListIfElementNotExists() {
    fillListWithManyElements();
    list.removeNodeByValue(67);
    assertEquals(list.getNodeByValue(2), list.head);
    assertEquals(list.getNodeByValue(41), list.tail);
    assertEquals(6, list.countNodes());
  }

  @Test
  void removeAllFromListIfElementNotExists() {
    fillListWithManyElements();
    list.removeAllNodesByValue(67);
    assertEquals(list.getNodeByValue(2), list.head);
    assertEquals(list.getNodeByValue(41), list.tail);
    assertEquals(6, list.countNodes());
  }

  @Test
  void removeAllFromListFromHeadAndTail() {
    fillListWithManyElements();
    list.addNodeInTail(new Node(2));
    list.removeAllNodesByValue(2);
    assertEquals(list.getNodeByValue(11), list.head);
    assertEquals(list.getNodeByValue(41), list.tail);
  }

  @Test
  void removeAllFromListWithTwoElements() {
    list.addNodeInTail(new Node(2));
    list.addNodeInTail(new Node(2));
    list.removeAllNodesByValue(2);
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void removeAllFromListIfListEmpty() {
    assertFalse(list.removeNodeByValue(10));
  }

  @Test
  void clear() {
    fillListWithManyElements();
    list.clearList();
    assertNull(list.head);
    assertNull(list.tail);
    assertEquals(0, list.countNodes());
  }

  @Test
  void clearEmptyList() {
    list.clearList();
    assertNull(list.head);
    assertNull(list.tail);
  }

  @Test
  void countWhenSixElements() {
    fillListWithManyElements();
    assertEquals(6, list.countNodes());
  }

  @Test
  void countWhenOneElement() {
    fillListWithOneElement();
    assertEquals(1, list.countNodes());
  }

  @Test
  void countWhenListEmpty() {
    assertEquals(0, list.countNodes());
  }

  @Test
  void countWhenAddTwoMoreElements() {
    fillListWithOneElement();
    Node toInsertInTail = new Node(17);
    Node toInsertAfter = new Node(29);
    list.addNodeInTail(toInsertInTail);
    list.insertAfter(toInsertInTail, toInsertAfter);
    assertEquals(list.getNodeByValue(1729), list.head);
    assertEquals(list.getNodeByValue(29), list.tail);
    assertEquals(3, list.countNodes());
  }

  @Test
  void insertAfterLast() {
    fillListWithManyElements();
    Node toInsert = new Node(59);
    Node after = list.getNodeByValue(41);
    list.insertAfter(after, toInsert);
    assertEquals(toInsert, list.tail);
  }

  @Test
  void insertAfterWhenListEmpty() {
    Node toInsert = new Node(1);
    list.insertAfter(null, toInsert);
    assertEquals(toInsert, list.head);
    assertEquals(toInsert, list.tail);
  }

  @Test
  void findAll() {
    fillListWithManyElements();
    assertEquals(2, list.getAllNodesByValue(17).size());
    assertEquals(1, list.getAllNodesByValue(41).size());
    assertEquals(0, list.getAllNodesByValue(67).size());
  }

  @Test
  void insertAfterFourElementsAndCount() {
    fillListWithOneElement();
    Node one = new Node(1);
    Node two = new Node(2);
    Node three = new Node(3);
    Node four = new Node(4);
    list.insertAfter(list.getNodeByValue(1729), one);
    assertEquals(list.getNodeByValue(1729), list.head);
    assertEquals(one, list.tail);
    list.insertAfter(one, two);
    assertEquals(two, list.tail);
    list.insertAfter(one, three);
    list.insertAfter(two, four);
    assertEquals(list.getNodeByValue(1729), list.head);
    assertEquals(four, list.tail);
    assertEquals(5, list.countNodes());
  }
}

package lesson02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BidirectionalUnidirectionalLinkedListWithDummyNodeTest {

  private BidirectionalLinkedListWithDummyNode<Integer> list;

  @BeforeEach
  void setUp() {
    list = new BidirectionalLinkedListWithDummyNode<>();
  }

  private void fillWithOneElement() {
    list.addNodeInTail(new DNode<>(10));
  }

  private void fillWithManyElements() {
    list.addNodeInTail(new DNode<>(10));
    list.addNodeInTail(new DNode<>(20));
    list.addNodeInTail(new DNode<>(30));
    list.addNodeInTail(new DNode<>(30));
    list.addNodeInTail(new DNode<>(40));
    list.addNodeInTail(new DNode<>(50));
  }

  @Test
  void findWhenOneElementInList() {
    fillWithOneElement();
    assertEquals(10, list.getNodeByValue(10).value);
  }

  @Test
  void findWhenManyElementsInList() {
    fillWithManyElements();
    assertEquals(10, list.getNodeByValue(10).value);
    assertEquals(30, list.getNodeByValue(30).value);
    assertEquals(50, list.getNodeByValue(50).value);
  }

  @Test
  void findAllWhenOneElementInList() {
    fillWithOneElement();
    assertEquals(10, list.getAllNodeByValue(10).get(0).value);
    assertEquals(1, list.getAllNodeByValue(10).size());
  }

  @Test
  void findAllWhenManyElementsInList() {
    fillWithManyElements();
    assertEquals(30, list.getAllNodeByValue(30).get(0).value);
    assertEquals(2, list.getAllNodeByValue(30).size());
  }

  @Test
  void removeWhenOneElementInList() {
    fillWithOneElement();
    assertTrue(list.removeNodeByValue(10));
  }

  @Test
  void removeFirstAndLastWhenManyElementsInListAndCount() {
    fillWithManyElements();
    assertTrue(list.removeNodeByValue(10));
    assertTrue(list.removeNodeByValue(50));
    assertEquals(4, list.countNodes());
  }

  @Test
  void removeAllWhenManyElements() {
    fillWithManyElements();
    list.removeAllNodesByValue(30);
    assertNull(list.getNodeByValue(30));
    assertEquals(4, list.countNodes());
  }

  @Test
  void clearWhenManyElements() {
    fillWithManyElements();
    list.clearList();
    assertNull(list.getNodeByValue(10));
    assertNull(list.getNodeByValue(50));
    assertEquals(0, list.countNodes());
  }

  @Test
  void countWhenSixElementsInList() {
    fillWithManyElements();
    assertEquals(6, list.countNodes());
  }

  @Test
  void insertAfterWhenFilledWithElementsAndCheckLinks() {
    DNode<Integer> node1 = new DNode<>(1);
    DNode<Integer> node2 = new DNode<>(2);
    DNode<Integer> node3 = new DNode<>(3);
    list.insertAfter(null, node1);
    list.insertAfter(node1, node2);
    list.insertAfter(node2, node3);

    assertEquals(3, list.countNodes());
    assertEquals(node1, list.getNodeByValue(node1.value));
    assertEquals(node2, list.getNodeByValue(node2.value));
    assertEquals(node3, list.getNodeByValue(node3.value));

    assertEquals(node1.next, node2);
    assertEquals(node2.next, node3);
    assertEquals(node3.prev, node2);
    assertEquals(node2.prev, node1);
  }
}

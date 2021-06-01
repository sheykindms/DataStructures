package lesson02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedList2WithDummyNodeTest {

  private LinkedList2WithDummyNode<Integer> list;

  @BeforeEach
  void setUp() {
    list = new LinkedList2WithDummyNode<>();
  }

  private void fillWithOneElement() {
    list.addInTail(new DNode<>(10));
  }

  private void fillWithManyElements() {
    list.addInTail(new DNode<>(10));
    list.addInTail(new DNode<>(20));
    list.addInTail(new DNode<>(30));
    list.addInTail(new DNode<>(30));
    list.addInTail(new DNode<>(40));
    list.addInTail(new DNode<>(50));
  }

  @Test
  void findWhenOneElementInList() {
    fillWithOneElement();
    assertEquals(10, list.find(10).value);
  }

  @Test
  void findWhenManyElementsInList() {
    fillWithManyElements();
    assertEquals(10, list.find(10).value);
    assertEquals(30, list.find(30).value);
    assertEquals(50, list.find(50).value);
  }

  @Test
  void findAllWhenOneElementInList() {
    fillWithOneElement();
    assertEquals(10, list.findAll(10).get(0).value);
    assertEquals(1, list.findAll(10).size());
  }

  @Test
  void findAllWhenManyElementsInList() {
    fillWithManyElements();
    assertEquals(30, list.findAll(30).get(0).value);
    assertEquals(2, list.findAll(30).size());
  }

  @Test
  void removeWhenOneElementInList() {
    fillWithOneElement();
    assertTrue(list.remove(10));
  }

  @Test
  void removeFirstAndLastWhenManyElementsInListAndCount() {
    fillWithManyElements();
    assertTrue(list.remove(10));
    assertTrue(list.remove(50));
    assertEquals(4, list.count());
  }

  @Test
  void removeAllWhenManyElements() {
    fillWithManyElements();
    list.removeAll(30);
    assertNull(list.find(30));
    assertEquals(4, list.count());
  }

  @Test
  void clearWhenManyElements() {
    fillWithManyElements();
    list.clear();
    assertNull(list.find(10));
    assertNull(list.find(50));
    assertEquals(0, list.count());
  }

  @Test
  void countWhenSixElementsInList() {
    fillWithManyElements();
    assertEquals(6, list.count());
  }

  @Test
  void insertAfterWhenFilledWithElementsAndCheckLinks() {
    DNode<Integer> node1 = new DNode<>(1);
    DNode<Integer> node2 = new DNode<>(2);
    DNode<Integer> node3 = new DNode<>(3);
    list.insertAfter(null, node1);
    list.insertAfter(node1, node2);
    list.insertAfter(node2, node3);

    assertEquals(3, list.count());
    assertEquals(node1, list.find(node1.value));
    assertEquals(node2, list.find(node2.value));
    assertEquals(node3, list.find(node3.value));

    assertEquals(node1.next, node2);
    assertEquals(node2.next, node3);
    assertEquals(node3.prev, node2);
    assertEquals(node2.prev, node1);
  }
}

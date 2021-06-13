package lesson07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedBidirectionalLinkedListTest {

  OrderedBidirectionalLinkedList<Integer> listAsc;
  OrderedBidirectionalLinkedList<Integer> listDesc;

  @BeforeEach
  void setUp() {
    listAsc = new OrderedBidirectionalLinkedList<>(true);
    listDesc = new OrderedBidirectionalLinkedList<>(false);
  }

  @Test
  void compare() {
    assertEquals(1, listAsc.compare(2, 1));
    assertEquals(-1, listDesc.compare(1, 2));
    assertEquals(0, listDesc.compare(2, 2));
    assertEquals(0, listAsc.compare(2, 2));
  }

  @Test
  void findInAscAndValidateAllLinksBetweenElements() {
    listAsc.addValue(1);
    listAsc.addValue(100);
    listAsc.addValue(10);

    assertEquals(listAsc.head.next, listAsc.getNodeByValue(10));
    assertEquals(listAsc.tail.prev, listAsc.getNodeByValue(10));
    assertEquals(listAsc.head, listAsc.getNodeByValue(10).prev);
    assertEquals(listAsc.tail, listAsc.getNodeByValue(10).next);

    assertEquals(listAsc.head, listAsc.getNodeByValue(1));
    assertEquals(listAsc.tail, listAsc.getNodeByValue(100));
    assertEquals(3, listAsc.countNodes());
    assertNull(listAsc.getNodeByValue(1).prev);
    assertNull(listAsc.getNodeByValue(100).next);
  }

  @Test
  void findInDescAndValidateAllLinksBetweenElements() {
    listDesc.addValue(1);
    listDesc.addValue(100);
    listDesc.addValue(10);

    assertEquals(listDesc.head.next, listDesc.getNodeByValue(10));
    assertEquals(listDesc.tail.prev, listDesc.getNodeByValue(10));
    assertEquals(listDesc.head, listDesc.getNodeByValue(10).prev);
    assertEquals(listDesc.tail, listDesc.getNodeByValue(10).next);

    assertEquals(listDesc.head, listDesc.getNodeByValue(100));
    assertEquals(listDesc.tail, listDesc.getNodeByValue(1));
    assertEquals(3, listDesc.countNodes());
    assertNull(listDesc.getNodeByValue(100).prev);
    assertNull(listDesc.getNodeByValue(1).next);
  }

  @Test
  void deleteInAscOneByOneAndCount() {
    listAsc.addValue(1);
    listAsc.addValue(100);
    listAsc.addValue(10);
    listAsc.removeNodeByValue(1);
    assertEquals(2, listAsc.countNodes());

    listAsc.removeNodeByValue(10);
    listAsc.removeNodeByValue(100);
    listAsc.removeNodeByValue(1000);
    assertEquals(0, listAsc.countNodes());

  }

  @Test
  void deleteInDescOneByOneAndCount() {
    listDesc.addValue(1);
    listDesc.addValue(100);
    listDesc.addValue(10);
    listDesc.removeNodeByValue(1);
    assertEquals(10, listDesc.tail.value);

    listDesc.removeNodeByValue(100);
    assertEquals(10, listDesc.head.value);
    assertEquals(1, listDesc.countNodes());
  }

  @Test
  void clearAfterAddingThreeElements() {
    listAsc.addValue(1);
    listAsc.addValue(2);
    listAsc.addValue(3);
    listAsc.clearList(true);
    assertNull(listAsc.head);
    assertNull(listAsc.tail);
    assertEquals(0, listAsc.countNodes());
  }
}

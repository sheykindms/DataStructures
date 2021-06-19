package lesson07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedBidirectionalLinkedListTest {
  private OrderedBidirectionalLinkedList<Integer> listAsc;
  private OrderedBidirectionalLinkedList<Integer> listDesc;

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

    assertEquals(listAsc.getHead().getNext(), listAsc.getNodeByValue(10));
    assertEquals(listAsc.getTail().getPrev(), listAsc.getNodeByValue(10));
    assertEquals(listAsc.getHead(), listAsc.getNodeByValue(10).getPrev());
    assertEquals(listAsc.getTail(), listAsc.getNodeByValue(10).getNext());

    assertEquals(listAsc.getHead(), listAsc.getNodeByValue(1));
    assertEquals(listAsc.getTail(), listAsc.getNodeByValue(100));
    assertEquals(3, listAsc.countNodes());
    assertNull(listAsc.getNodeByValue(1).getPrev());
    assertNull(listAsc.getNodeByValue(100).getNext());
  }

  @Test
  void findInDescAndValidateAllLinksBetweenElements() {
    listDesc.addValue(1);
    listDesc.addValue(100);
    listDesc.addValue(10);

    assertEquals(listDesc.getHead().getNext(), listDesc.getNodeByValue(10));
    assertEquals(listDesc.getTail().getPrev(), listDesc.getNodeByValue(10));
    assertEquals(listDesc.getHead(), listDesc.getNodeByValue(10).getPrev());
    assertEquals(listDesc.getTail(), listDesc.getNodeByValue(10).getNext());

    assertEquals(listDesc.getHead(), listDesc.getNodeByValue(100));
    assertEquals(listDesc.getTail(), listDesc.getNodeByValue(1));
    assertEquals(3, listDesc.countNodes());
    assertNull(listDesc.getNodeByValue(100).getPrev());
    assertNull(listDesc.getNodeByValue(1).getNext());
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
    assertEquals(10, listDesc.getTail().getValue());

    listDesc.removeNodeByValue(100);
    assertEquals(10, listDesc.getHead().getValue());
    assertEquals(1, listDesc.countNodes());
  }

  @Test
  void clearAfterAddingThreeElements() {
    listAsc.addValue(1);
    listAsc.addValue(2);
    listAsc.addValue(3);
    listAsc.clearList(true);
    assertNull(listAsc.getHead());
    assertNull(listAsc.getTail());
    assertEquals(0, listAsc.countNodes());
  }
}

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
    listAsc.add(1);
    listAsc.add(100);
    listAsc.add(10);

    assertEquals(listAsc.head.next, listAsc.get(10));
    assertEquals(listAsc.tail.prev, listAsc.get(10));
    assertEquals(listAsc.head, listAsc.get(10).prev);
    assertEquals(listAsc.tail, listAsc.get(10).next);

    assertEquals(listAsc.head, listAsc.get(1));
    assertEquals(listAsc.tail, listAsc.get(100));
    assertEquals(3, listAsc.count());
    assertNull(listAsc.get(1).prev);
    assertNull(listAsc.get(100).next);
  }

  @Test
  void findInDescAndValidateAllLinksBetweenElements() {
    listDesc.add(1);
    listDesc.add(100);
    listDesc.add(10);

    assertEquals(listDesc.head.next, listDesc.get(10));
    assertEquals(listDesc.tail.prev, listDesc.get(10));
    assertEquals(listDesc.head, listDesc.get(10).prev);
    assertEquals(listDesc.tail, listDesc.get(10).next);

    assertEquals(listDesc.head, listDesc.get(100));
    assertEquals(listDesc.tail, listDesc.get(1));
    assertEquals(3, listDesc.count());
    assertNull(listDesc.get(100).prev);
    assertNull(listDesc.get(1).next);
  }

  @Test
  void deleteInAscOneByOneAndCount() {
    listAsc.add(1);
    listAsc.add(100);
    listAsc.add(10);
    listAsc.remove(1);
    assertEquals(2, listAsc.count());

    listAsc.remove(10);
    listAsc.remove(100);
    listAsc.remove(1000);
    assertEquals(0, listAsc.count());

  }

  @Test
  void deleteInDescOneByOneAndCount() {
    listDesc.add(1);
    listDesc.add(100);
    listDesc.add(10);
    listDesc.remove(1);
    assertEquals(10, listDesc.tail.value);

    listDesc.remove(100);
    assertEquals(10, listDesc.head.value);
    assertEquals(1, listDesc.count());
  }

  @Test
  void clearAfterAddingThreeElements() {
    listAsc.add(1);
    listAsc.add(2);
    listAsc.add(3);
    listAsc.clear(true);
    assertNull(listAsc.head);
    assertNull(listAsc.tail);
    assertEquals(0, listAsc.count());
  }
}

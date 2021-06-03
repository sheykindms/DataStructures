package lesson07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderedListTest {

  OrderedList<Integer> listAsc;
  OrderedList<Integer> listDesc;

  @BeforeEach
  void setUp() {
    listAsc = new OrderedList<>(true);
    listDesc = new OrderedList<>(false);
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

    assertEquals(listAsc.head.next, listAsc.find(10));
    assertEquals(listAsc.tail.prev, listAsc.find(10));
    assertEquals(listAsc.head, listAsc.find(10).prev);
    assertEquals(listAsc.tail, listAsc.find(10).next);

    assertEquals(listAsc.head, listAsc.find(1));
    assertEquals(listAsc.tail, listAsc.find(100));
    assertEquals(3, listAsc.count());
    assertNull(listAsc.find(1).prev);
    assertNull(listAsc.find(100).next);
  }

  @Test
  void findInDescAndValidateAllLinksBetweenElements() {
    listDesc.add(1);
    listDesc.add(100);
    listDesc.add(10);

    assertEquals(listDesc.head.next, listDesc.find(10));
    assertEquals(listDesc.tail.prev, listDesc.find(10));
    assertEquals(listDesc.head, listDesc.find(10).prev);
    assertEquals(listDesc.tail, listDesc.find(10).next);

    assertEquals(listDesc.head, listDesc.find(100));
    assertEquals(listDesc.tail, listDesc.find(1));
    assertEquals(3, listDesc.count());
    assertNull(listDesc.find(100).prev);
    assertNull(listDesc.find(1).next);
  }

  @Test
  void deleteInAscOneByOneAndCount() {
    listAsc.add(1);
    listAsc.add(100);
    listAsc.add(10);
    listAsc.delete(1);
    assertEquals(2, listAsc.count());

    listAsc.delete(10);
    listAsc.delete(100);
    listAsc.delete(1000);
    assertEquals(0, listAsc.count());

  }

  @Test
  void deleteInDescOneByOneAndCount() {
    listDesc.add(1);
    listDesc.add(100);
    listDesc.add(10);
    listDesc.delete(1);
    assertEquals(10, listDesc.tail.value);

    listDesc.delete(100);
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

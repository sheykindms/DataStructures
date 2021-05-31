package lesson03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynArrayTest {

  DynArray<Integer> array;

  @BeforeEach
  void setUp() {
    array = new DynArray<>(Integer.class);
  }

  private void fillDynArrayWithManyElements() {
    array.append(10);
    array.append(20);
    array.append(30);
    array.append(30);
    array.append(40);
    array.append(50);
  }

  private void fillDynArrayWithOneElement() {
    array.append(100);
  }

  @Test
  void getItemWhenOneElement() {
    fillDynArrayWithOneElement();
    assertEquals(100, array.getItem(0));
    assertThrows(IndexOutOfBoundsException.class, () -> array.getItem(1));
  }

  @Test
  void getItemWhenManyElements() {
    fillDynArrayWithManyElements();
    assertEquals(10, array.getItem(0));
    assertEquals(20, array.getItem(1));
    assertEquals(30, array.getItem(2));
    assertEquals(30, array.getItem(3));
    assertEquals(40, array.getItem(4));
    assertEquals(50, array.getItem(5));
  }

  @Test
  void appendWhenEmptyArray() {
    assertEquals(16, array.capacity);
    assertEquals(0, array.count);
    array.append(10);
    array.append(20);
    array.append(30);
    array.append(30);
    array.append(40);
    array.append(50);
    assertEquals(16, array.capacity);
    assertEquals(array.array.length, array.capacity);
    assertEquals(6, array.count);
  }

  @Test
  void insertWhenEmptyArray() {
    array.insert(2, 0);
    assertEquals(2, array.getItem(0));
    array.insert(2, 1);
    assertEquals(2, array.getItem(1));
    assertThrows(IndexOutOfBoundsException.class, () -> array.insert(3, 3));
  }

  @Test
  void insertWhenOneElementInArray() {
    fillDynArrayWithOneElement();
    array.insert(1, 0);
    assertEquals(1, array.getItem(0));
    array.insert(2, 2);
    assertEquals(2, array.getItem(2));
  }

  @Test
  void removeWhenEmptyArray() {
    assertThrows(IndexOutOfBoundsException.class, () -> array.remove(0));
    assertThrows(IndexOutOfBoundsException.class, () -> array.remove(1));
  }

  @Test
  void removeWhenOneElementInArray() {
    fillDynArrayWithOneElement();
    assertThrows(IndexOutOfBoundsException.class, () -> array.remove(1));
    array.remove(0);
    assertEquals(0, array.count);
    assertEquals(16, array.capacity);
  }

  @Test
  void removeWhenManyElementsInArray() {
    fillDynArrayWithManyElements();
    array.remove(0);
    array.remove(0);
    array.remove(0);
    array.remove(0);
    array.remove(0);
    array.remove(0);
    assertEquals(0, array.count);
    assertEquals(16, array.capacity);
  }

  @Test
  void appendTwoElementsThenInsertTwoElementsThenRemoveFourElements() {
    array.append(0);
    array.append(1);
    assertEquals(2, array.count);
    array.insert(2, 0);
    array.insert(3, 3);
    assertEquals(4, array.count);
    assertEquals(0, array.getItem(1));
    assertEquals(1, array.getItem(2));
    assertEquals(3, array.getItem(3));
    array.remove(3);
    array.remove(2);
    array.remove(1);
    array.remove(0);
    assertEquals(0, array.count);
    assertEquals(16, array.capacity);
  }

  @Test
  void appendSixteenElementsThenRemoveOne() {
    int seventeen = 17;
    while (seventeen != 0) {
      array.append(seventeen--);
    }
    assertEquals(32, array.capacity);
    array.remove(0);
    array.remove(0);
    while (array.count != 0) {
      array.remove(0);
    }
    assertEquals(16, array.capacity);
  }

  @Test
  void appendSixtyFourElementsThenRemoveOne() {
    int sixtyFour = 64;
    while (sixtyFour != 0) {
      array.append(sixtyFour--);
    }
    assertEquals(64, array.count);
    assertEquals(64, array.capacity);
    array.insert(0, 0);
    assertEquals(65, array.count);
    assertEquals(128, array.capacity);
    assertThrows(IndexOutOfBoundsException.class, () -> array.remove(65));
    array.remove(64);
    array.remove(63);
    assertEquals(63, array.count);
    assertEquals(85, array.capacity);
  }
}

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
    array.remove(array.count - 1);
    assertEquals(0, array.count);
    assertEquals(16, array.capacity);
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.getItem(16));
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
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.insert(3, 3));
  }

  @Test
  void insertSixteenElementsAndThenOneMoreThenRemoveOne() {
    int sixteen = 16;
    while (sixteen != 0) {
      array.insert(sixteen--, 0);
    }
    assertEquals(16, array.capacity);
    array.insert(17, 16);
    assertEquals(32, array.capacity);
    array.remove(0);
    array.remove(0);
    while (array.count != 0) {
      array.remove(0);
    }
    assertEquals(0, array.count);
    assertEquals(16, array.capacity);
  }

  @Test
  void insertSixtyFourElementsAndThenOneMoreThenRemoveOne() {
    int sixtyFour = 64;
    while (sixtyFour != 0) {
      array.insert(sixtyFour--, 0);
    }
    assertEquals(64, array.capacity);
    assertEquals(64, array.count);
    array.insert(100, 64);
    assertEquals(65, array.count);
    assertEquals(128, array.capacity);
    array.remove(0);
    assertEquals(128, array.capacity);
    array.remove(22);
    assertEquals(85, array.capacity);
  }

  @Test
  void removeWhenEmptyArray() {
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.remove(0));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.remove(1));
  }

  @Test
  void removeWhenOneElementInArray() {
    fillDynArrayWithOneElement();
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.remove(1));
    array.remove(0);
    assertEquals(0, array.count);
    assertEquals(16, array.capacity);
  }

  @Test
  void removeWhenManyElementsInArray() {
    fillDynArrayWithManyElements();
    System.out.println(array);
    assertEquals(6, array.count);
    array.remove(array.count - 1);
    System.out.println(array);
    array.remove(array.count - 1);
    array.remove(array.count - 1);
    array.remove(array.count - 1);
    array.remove(array.count - 1);
    array.remove(array.count - 1);
    assertEquals(0, array.count);
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.remove(array.count - 1));
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
  void appendSixtyFourElementsThenInsertOneThenRemoveTwo() {
    int sixtyFour = 64;
    while (sixtyFour != 0) {
      array.append(sixtyFour--);
    }
    assertEquals(64, array.count);
    assertEquals(64, array.capacity);
    array.insert(0, 0);
    assertEquals(65, array.count);
    assertEquals(128, array.capacity);
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.remove(65));
    array.remove(64);
    array.remove(63);
    assertEquals(63, array.count);
    assertEquals(85, array.capacity);
  }

  @Test
  void makeArrayFillWithManyElementsAndRemoveThem() {
    fillDynArrayWithManyElements();
    array.makeArray(5);
    assertEquals(6, array.count);
    assertEquals(16, array.capacity);
    array.remove(array.count - 1);
    array.remove(array.count - 1);
    array.remove(array.count - 1);
    array.remove(array.count - 1);
    array.remove(array.count - 1);
    array.remove(array.count - 1);
    System.out.println(array);
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.remove(array.count - 1));
  }

  @Test
  void insertAndAppend100ElementsThenRemoveThemAll() {
    int a = 100;
    while (a != 0) {
      array.insert(a--, 0);
      array.append(a--);
    }
    assertEquals(100, array.count);
    assertEquals(128, array.capacity);
    while (array.count != 0) {
      array.remove(array.count - 1);
    }
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.getItem(0));
    assertEquals(16, array.capacity);
  }
}

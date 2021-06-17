package lesson03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynArrayTest {

  private DynArray<Integer> array;

  @BeforeEach
  void setUp() {
    array = DynArray.ofClass(Integer.class);
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
    assertEquals(100, array.getValueByIndex(0));
    array.removeByIndex(array.getCount() - 1);
    assertEquals(0, array.getCount());
    assertEquals(16, array.getCapacity());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.getValueByIndex(16));
  }

  @Test
  void getItemWhenManyElements() {
    fillDynArrayWithManyElements();
    assertEquals(10, array.getValueByIndex(0));
    assertEquals(20, array.getValueByIndex(1));
    assertEquals(30, array.getValueByIndex(2));
    assertEquals(30, array.getValueByIndex(3));
    assertEquals(40, array.getValueByIndex(4));
    assertEquals(50, array.getValueByIndex(5));
  }

  @Test
  void appendWhenEmptyArray() {
    assertEquals(16, array.getCapacity());
    assertEquals(0, array.getCount());
    array.append(10);
    array.append(20);
    array.append(30);
    array.append(30);
    array.append(40);
    array.append(50);
    assertEquals(16, array.getCapacity());
    assertEquals(array.getArray().length, array.getCapacity());
    assertEquals(6, array.getCount());
  }

  @Test
  void insertWhenEmptyArray() {
    array.insertValueByIndex(2, 0);
    assertEquals(2, array.getValueByIndex(0));
    array.insertValueByIndex(2, 1);
    assertEquals(2, array.getValueByIndex(1));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.insertValueByIndex(3, 3));
  }

  @Test
  void insertSixteenElementsAndThenOneMoreThenRemoveOne() {
    int sixteen = 16;
    while (sixteen != 0) {
      array.insertValueByIndex(sixteen--, 0);
    }
    assertEquals(16, array.getCapacity());
    array.insertValueByIndex(17, 16);
    assertEquals(32, array.getCapacity());
    array.removeByIndex(0);
    array.removeByIndex(0);
    while (array.getCount() != 0) {
      array.removeByIndex(0);
    }
    assertEquals(0, array.getCount());
    assertEquals(16, array.getCapacity());
  }

  @Test
  void insertSixtyFourElementsAndThenOneMoreThenRemoveOne() {
    int sixtyFour = 64;
    while (sixtyFour != 0) {
      array.insertValueByIndex(sixtyFour--, 0);
    }
    assertEquals(64, array.getCapacity());
    assertEquals(64, array.getCount());
    array.insertValueByIndex(100, 64);
    assertEquals(65, array.getCount());
    assertEquals(128, array.getCapacity());
    array.removeByIndex(0);
    assertEquals(128, array.getCapacity());
    array.removeByIndex(22);
    assertEquals(85, array.getCapacity());
  }

  @Test
  void removeWhenEmptyArray() {
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.removeByIndex(0));
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.removeByIndex(1));
  }

  @Test
  void removeWhenOneElementInArray() {
    fillDynArrayWithOneElement();
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.removeByIndex(1));
    array.removeByIndex(0);
    assertEquals(0, array.getCount());
    assertEquals(16, array.getCapacity());
  }

  @Test
  void removeWhenManyElementsInArray() {
    fillDynArrayWithManyElements();
    assertEquals(6, array.getCount());
    array.removeByIndex(array.getCount() - 1);
    array.removeByIndex(array.getCount() - 1);
    array.removeByIndex(array.getCount() - 1);
    array.removeByIndex(array.getCount() - 1);
    array.removeByIndex(array.getCount() - 1);
    array.removeByIndex(array.getCount() - 1);
    assertEquals(0, array.getCount());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.removeByIndex(array.getCount() - 1));
    assertEquals(0, array.getCount());
    assertEquals(16, array.getCapacity());
  }

  @Test
  void appendTwoElementsThenInsertTwoElementsThenRemoveFourElements() {
    array.append(0);
    array.append(1);
    assertEquals(2, array.getCount());
    array.insertValueByIndex(2, 0);
    array.insertValueByIndex(3, 3);
    assertEquals(4, array.getCount());
    assertEquals(0, array.getValueByIndex(1));
    assertEquals(1, array.getValueByIndex(2));
    assertEquals(3, array.getValueByIndex(3));
    array.removeByIndex(3);
    array.removeByIndex(2);
    array.removeByIndex(1);
    array.removeByIndex(0);
    assertEquals(0, array.getCount());
    assertEquals(16, array.getCapacity());
  }

  @Test
  void appendSixteenElementsThenRemoveOne() {
    int seventeen = 17;
    while (seventeen != 0) {
      array.append(seventeen--);
    }
    assertEquals(32, array.getCapacity());
    array.removeByIndex(0);
    array.removeByIndex(0);
    while (array.getCount() != 0) {
      array.removeByIndex(0);
    }
    assertEquals(16, array.getCapacity());
  }

  @Test
  void appendSixtyFourElementsThenInsertOneThenRemoveTwo() {
    int sixtyFour = 64;
    while (sixtyFour != 0) {
      array.append(sixtyFour--);
    }
    assertEquals(64, array.getCount());
    assertEquals(64, array.getCapacity());
    array.insertValueByIndex(0, 0);
    assertEquals(65, array.getCount());
    assertEquals(128, array.getCapacity());
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.removeByIndex(65));
    array.removeByIndex(64);
    array.removeByIndex(63);
    assertEquals(63, array.getCount());
    assertEquals(85, array.getCapacity());
  }

  @Test
  void makeArrayFillWithManyElementsAndRemoveThem() {
    fillDynArrayWithManyElements();
    array.instantiateArrayWithNewCapacity(5);
    assertEquals(6, array.getCount());
    assertEquals(16, array.getCapacity());
    array.removeByIndex(array.getCount() - 1);
    array.removeByIndex(array.getCount() - 1);
    array.removeByIndex(array.getCount() - 1);
    array.removeByIndex(array.getCount() - 1);
    array.removeByIndex(array.getCount() - 1);
    array.removeByIndex(array.getCount() - 1);
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.removeByIndex(array.getCount() - 1));
  }

  @Test
  void insertAndAppend100ElementsThenRemoveThemAll() {
    int a = 100;
    while (a != 0) {
      array.insertValueByIndex(a--, 0);
      array.append(a--);
    }
    assertEquals(100, array.getCount());
    assertEquals(128, array.getCapacity());
    while (array.getCount() != 0) {
      array.removeByIndex(array.getCount() - 1);
    }
    assertThrows(ArrayIndexOutOfBoundsException.class, () -> array.getValueByIndex(0));
    assertEquals(16, array.getCapacity());
  }

  @Test
  void appendSixteenElementsThenRemove() {
    int sixteen = 16;
    while (sixteen != 0) {
      array.append(sixteen--);
    }
    assertEquals(16, array.getCapacity());
    assertEquals(16, array.getCount());
    array.removeByIndex(array.getCount() - 1);
    assertEquals(16, array.getCapacity());
    assertEquals(15, array.getCount());
    array.removeByIndex(array.getCount() - 1);
    assertEquals(16, array.getCapacity());
    assertEquals(14, array.getCount());
    while (array.getCount() != 0) {
      array.removeByIndex(array.getCount() - 1);
    }
    assertEquals(0, array.getCount());
    assertEquals(16, array.getCapacity());
  }
}

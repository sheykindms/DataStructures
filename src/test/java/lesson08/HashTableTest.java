package lesson08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
  private HashTable ht;

  @BeforeEach
  void setUp() {
    //+4 because in our tests we need to put four elements into HashTable
    //so the min capacity of ht should be at least 4
    int size = new Random().nextInt(100) + 4;
    int step = new Random().nextInt(100) + 4;
    ht = new HashTable(size, step);
  }

  @Test
  void putFourElementsAndCompareHashFunToIndexInArray() {
    assertNotEquals(-1, ht.put("test1"));
    assertNotEquals(-1, ht.put("test2"));
    assertNotEquals(-1, ht.put("test3"));
    assertNotEquals(-1, ht.put("test4"));
    assertEquals("test1", ht.getSlots()[ht.findIndexByValue("test1")]);
    assertEquals(-1, ht.findIndexByValue("test5"));
  }

  @Test
  void findIfNotExist() {
    ht.put("test1");
    ht.put("test2");
    ht.put("test3");
    ht.put("test4");
    assertEquals(-1, ht.findIndexByValue("test10"));
    assertNotEquals(-1, ht.findIndexByValue("test1"));
  }

  @Test
  void findIfEmpty() {
    assertEquals(-1, ht.findIndexByValue("test10"));
    assertEquals(-1, ht.findIndexByValue("test1"));
    assertEquals(-1, ht.findIndexByValue(""));
  }

  @Test
  void putFiveThenCompareByIndexesThenFindThemThenTryToAddMore() {
    HashTable ht = new HashTable(5, 5);
    assertNotEquals(-1, ht.put("test1"));
    assertNotEquals(-1, ht.put("test2"));
    assertNotEquals(-1, ht.put("test3"));
    assertNotEquals(-1, ht.put("test4"));
    assertNotEquals(-1, ht.put("test5"));

    assertEquals("test1", ht.getSlots()[3]);
    assertEquals("test2", ht.getSlots()[4]);
    assertEquals("test3", ht.getSlots()[0]);
    assertEquals("test4", ht.getSlots()[1]);
    assertEquals("test5", ht.getSlots()[2]);

    assertEquals(3, ht.findIndexByValue("test1"));
    assertEquals(4, ht.findIndexByValue("test2"));
    assertEquals(0, ht.findIndexByValue("test3"));
    assertEquals(1, ht.findIndexByValue("test4"));
    assertEquals(2, ht.findIndexByValue("test5"));

    assertEquals(-1, ht.put("test6"));
    assertEquals(-1, ht.put("test7"));

    assertEquals(-1, ht.findIndexByValue("test6"));
    assertEquals(-1, ht.findIndexByValue("test7"));
  }
}

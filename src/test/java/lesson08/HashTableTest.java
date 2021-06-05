package lesson08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

  HashTable ht;

  @BeforeEach
  void setUp() {
    ht = new HashTable(5, 3);
  }

  @Test
  void hashFunConsistency() {
    assertEquals(ht.hashFun("testtest1"), ht.hashFun("testtest1"));
    assertEquals(ht.hashFun("ABCDEFG"), ht.hashFun("ABCDEFG"));
  }

  @Test
  void putFourElementsAndCompareHashFunToIndexInArray() {
    assertNotEquals(-1, ht.put("test1"));
    assertNotEquals(-1, ht.put("test2"));
    assertNotEquals(-1, ht.put("test3"));
    assertNotEquals(-1, ht.put("test4"));
    assertEquals("test1", ht.slots[ht.find("test1")]);
    assertEquals(ht.hashFun("test1"), ht.find("test1"));
    assertEquals(-1, ht.find("test5"));
  }

  @Test
  void findIfNotExist() {
    ht.put("test1");
    ht.put("test2");
    ht.put("test3");
    ht.put("test4");
    assertEquals(-1, ht.find("test10"));
  }

  @Test
  void findIfEmpty() {
    assertEquals(-1, ht.find("test10"));
    assertEquals(-1, ht.find("test1"));
  }

  @Test
  void putSameElements() {
    assertNotEquals(-1, ht.put("test1"));
    assertNotEquals(-1, ht.put("test1"));
  }

  @Test
  void putWhenFullHashTable() {
    assertNotEquals(-1, ht.put("test1"));
    assertNotEquals(-1, ht.put("test2"));
    assertNotEquals(-1, ht.put("test3"));
    assertNotEquals(-1, ht.put("test4"));
    assertNotEquals(-1, ht.put("test5"));

    assertEquals("test1", ht.slots[3]);
    assertEquals("test2", ht.slots[4]);
    assertEquals("test3", ht.slots[0]);
    assertEquals("test4", ht.slots[1]);
    assertEquals("test5", ht.slots[2]);

    assertEquals(-1, ht.put("test6"));
    assertEquals(-1, ht.put("test7"));
  }
}

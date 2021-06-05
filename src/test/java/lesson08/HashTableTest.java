package lesson08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

  HashTable ht;

  @BeforeEach
  void setUp() {
    ht = new HashTable(17, 3);
  }

  @Test
  void putFourElementsAndCompareHashFunToIndexInArray() {
    ht.put("test1");
    ht.put("test2");
    ht.put("test3");
    ht.put("test4");
    assertEquals("test1", ht.slots[ht.find("test1")]);
    assertEquals(ht.hashFun("test1"), ht.find("test1"));
    assertEquals(-1, ht.find("test5"));
  }
}

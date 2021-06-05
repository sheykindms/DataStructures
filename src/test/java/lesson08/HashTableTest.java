package lesson08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

  HashTable ht;

  @BeforeEach
  void setUp() {
    int size = new Random().nextInt(100);
    int step = new Random().nextInt(100);
    ht = new HashTable(size, step);
  }

  @Test
  void hashFunConsistency() {
    String test1 = generateRandomString();
    assertEquals(ht.hashFun(test1), ht.hashFun(test1));

    String test2 = generateRandomString();
    assertEquals(ht.hashFun(test2), ht.hashFun(test2));

    // Classic
    assertEquals(ht.hashFun("AaAa"), ht.hashFun("BBBB"));
    assertEquals(ht.hashFun("AaBB"), ht.hashFun("BBAa"));
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
    assertNotEquals(-1, ht.find("test1"));
  }

  @Test
  void findIfEmpty() {
    assertEquals(-1, ht.find("test10"));
    assertEquals(-1, ht.find("test1"));
    assertEquals(-1, ht.find(""));
  }

  @Test
  void putFiveThenCompareByIndexesThenFindThemThenTryToAddMore() {
    HashTable ht = new HashTable(5, 5);
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

    assertEquals(3, ht.find("test1"));
    assertEquals(4, ht.find("test2"));
    assertEquals(0, ht.find("test3"));
    assertEquals(1, ht.find("test4"));
    assertEquals(2, ht.find("test5"));

    assertEquals(-1, ht.put("test6"));
    assertEquals(-1, ht.put("test7"));

    assertEquals(-1, ht.find("test6"));
    assertEquals(-1, ht.find("test7"));
  }

  @Test
  void seekSlotWhenEmptyHashTable() {
    assertNotEquals(-1, ht.seekSlot("value1"));
    assertNotEquals(-1, ht.seekSlot("value2"));
    assertNotEquals(-1, ht.seekSlot("value3"));
    assertNotEquals(-1, ht.seekSlot("value4"));
    assertNotEquals(-1, ht.seekSlot("value5"));
    assertNotEquals(-1, ht.seekSlot("value6"));
    assertNotEquals(-1, ht.seekSlot(""));
  }

  @Test
  void seekSlotWhenFullHashTable() {
    HashTable ht = new HashTable(5, 3);
    ht.put("value1");
    ht.put("value2");
    ht.put("value3");
    ht.put("value4");
    ht.put("value5");

    String random = generateRandomString();
    String empty = "";

    assertEquals(-1, ht.seekSlot(random));
    assertEquals(-1, ht.seekSlot(empty));

    assertNotEquals(-1, ht.seekSlot("value1"));
    assertNotEquals(-1, ht.seekSlot("value2"));
  }

  private String generateRandomString() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 10;
    Random random = new Random();

    return random
            .ints(leftLimit, rightLimit + 1)
            .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
            .limit(targetStringLength)
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
  }
}

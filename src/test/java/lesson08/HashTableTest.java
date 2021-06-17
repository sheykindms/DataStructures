package lesson08;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

  private HashTable ht;

  @BeforeEach
  void setUp() {
    int size = new Random().nextInt(100);
    int step = new Random().nextInt(100);
    ht = new HashTable(size, step);
  }

  @Test
  void hashFunConsistency() {
    String test1 = generateRandomString();
    assertEquals(ht.getIndexAsHashFun(test1), ht.getIndexAsHashFun(test1));

    String test2 = generateRandomString();
    assertEquals(ht.getIndexAsHashFun(test2), ht.getIndexAsHashFun(test2));

    // Classic
    assertEquals(ht.getIndexAsHashFun("AaAa"), ht.getIndexAsHashFun("BBBB"));
    assertEquals(ht.getIndexAsHashFun("AaBB"), ht.getIndexAsHashFun("BBAa"));
  }

  @Test
  void putFourElementsAndCompareHashFunToIndexInArray() {
    assertNotEquals(-1, ht.put("test1"));
    assertNotEquals(-1, ht.put("test2"));
    assertNotEquals(-1, ht.put("test3"));
    assertNotEquals(-1, ht.put("test4"));
    assertEquals("test1", ht.getSlots()[ht.findIndexByValue("test1")]);
    assertEquals(ht.getIndexAsHashFun("test1"), ht.findIndexByValue("test1"));
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

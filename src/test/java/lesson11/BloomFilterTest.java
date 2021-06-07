package lesson11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloomFilterTest {
  BloomFilter bf;

  @BeforeEach
  void setUp() {
    bf = new BloomFilter(32);
  }

  @Test
  void hashIsConsistent() {
    String test1 = "this is";
    String test2 = "simple test";
    String test3 = "example of hash consistency";
    assertEquals(bf.hash1(test1), bf.hash1(test1));
    assertEquals(bf.hash1(test2), bf.hash1(test2));
    assertEquals(bf.hash1(test3), bf.hash1(test3));
    assertEquals(bf.hash2(test1), bf.hash2(test1));
    assertEquals(bf.hash2(test2), bf.hash2(test2));
    assertEquals(bf.hash2(test3), bf.hash2(test3));
  }

  @Test
  void addThenGet() {
    bf.add("test1");
    bf.add("test2");
    bf.add("test3");
    bf.add("test4");
    bf.add("test5");
    assertTrue(bf.isValue("test1"));
    assertTrue(bf.isValue("test2"));
    assertTrue(bf.isValue("test3"));
    assertTrue(bf.isValue("test4"));
    assertTrue(bf.isValue("test5"));
  }
}

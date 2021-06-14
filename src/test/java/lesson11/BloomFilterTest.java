package lesson11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloomFilterTest {
  BloomFilter bf;

  @BeforeEach
  void setUp() {
    bf = BloomFilter.withLength(32);
  }

  @Test
  void hashIsConsistent() {
    String test1 = "this is";
    String test2 = "simple test";
    String test3 = "example of hash consistency";
    assertEquals(bf.firstHashFun(test1), bf.firstHashFun(test1));
    assertEquals(bf.firstHashFun(test2), bf.firstHashFun(test2));
    assertEquals(bf.firstHashFun(test3), bf.firstHashFun(test3));
    assertEquals(bf.secondHashFun(test1), bf.secondHashFun(test1));
    assertEquals(bf.secondHashFun(test2), bf.secondHashFun(test2));
    assertEquals(bf.secondHashFun(test3), bf.secondHashFun(test3));
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

package lesson11;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BloomFilterTest {
  private BloomFilter bf;

  @BeforeEach
  void setUp() {
    bf = BloomFilter.withLength(32);
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

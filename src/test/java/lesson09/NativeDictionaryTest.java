package lesson09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class NativeDictionaryTest {
  private NativeDictionary<Integer> dict;

  @BeforeEach
  void setUp() {
    int size = new Random().nextInt(95);
    int min = 5;
    dict = new NativeDictionary<>(size + min, Integer.class);
  }

  @Test
  void isKey() {
    dict.put("test1", 1);
    dict.put("test2", 2);
    dict.put("test3", 3);
    assertTrue(dict.isKey("test1"));
    assertTrue(dict.isKey("test2"));
    assertTrue(dict.isKey("test3"));
    assertFalse(dict.isKey("test6"));
    assertFalse(dict.isKey(""));
  }

  @Test
  void putTwoWithSameKeyAndVerify() {
    dict.put("key", 2);
    dict.put("key", 3);
    assertEquals(3, dict.getValueByKey("key"));
  }

  @Test
  void putThenGet() {
    dict.put("test1", 1);
    dict.put("test2", 2);
    dict.put("test3", 3);
    dict.put("test4", 4);
    dict.put("test5", 5);
    assertEquals(5, dict.getValueByKey("test5"));
    assertEquals(1, dict.getValueByKey("test1"));
    assertNull(dict.getValueByKey("test6"));
  }

  @Test
  void putFiveAsStringsThenTryToPutOneMore() {
    NativeDictionary<String> nd = new NativeDictionary<>(5, String.class);
    nd.put("test1", "val1");
    nd.put("test2", "val2");
    nd.put("test3", "val3");
    nd.put("test4", "val4");
    nd.put("test5", "val5");

    nd.put("test6", "val6");
    assertNull(nd.getValueByKey("test6"));
  }
}

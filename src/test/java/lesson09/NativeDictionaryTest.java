package lesson09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class NativeDictionaryTest {

  NativeDictionary<Integer> dict;

  @BeforeEach
  void setUp() {
    int size = new Random().nextInt(95);
    int min = 5;
    dict = new NativeDictionary<>(size + min, Integer.class);
  }

  @Test
  void hashFun() {
    String test1 = generateRandomString();
    assertEquals(dict.hashFun(test1), dict.hashFun(test1));

    String test2 = generateRandomString();
    assertEquals(dict.hashFun(test2), dict.hashFun(test2));

    // Classic
    assertEquals(dict.hashFun("AaAa"), dict.hashFun("BBBB"));
    assertEquals(dict.hashFun("AaBB"), dict.hashFun("BBAa"));
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
    assertEquals(3, dict.get("key"));
  }

  @Test
  void putThenGet() {
    dict.put("test1", 1);
    dict.put("test2", 2);
    dict.put("test3", 3);
    dict.put("test4", 4);
    dict.put("test5", 5);
    assertEquals(5, dict.get("test5"));
    assertEquals(1, dict.get("test1"));
    assertNull(dict.get("test6"));
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
    assertNull(nd.get("test6"));
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

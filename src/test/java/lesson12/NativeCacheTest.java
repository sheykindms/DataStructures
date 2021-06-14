package lesson12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NativeCacheTest {

  NativeCache<String> cache;

  @BeforeEach
  void setUp() {
    cache = NativeCache.withSizeAndClass(3, String.class);
  }

  @Test
  void putThreeThenGetTwoThenPutOneThenValidate() {
    cache.put("test1", "val1");
    cache.put("test2", "val2");
    cache.put("test3", "val3");

    assertEquals("val1", cache.get("test1"));
    assertEquals("val1", cache.get("test1"));
    assertEquals("val2", cache.get("test2"));

    cache.put("new", "element");
    assertEquals("element", cache.get("new"));
    assertNull(cache.get("test3"));

    cache.put("last", "one");
    assertEquals("one", cache.get("last"));
    assertNull(cache.get("test2"));
  }
}

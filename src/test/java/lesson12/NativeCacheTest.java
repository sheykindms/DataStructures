package lesson12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NativeCacheTest {
  private NativeCache<String> cache;

  @BeforeEach
  void setUp() {
    cache = NativeCache.withSizeAndClass(3, String.class);
  }

  @Test
  void putThreeThenGetTwoThenPutOneThenValidate() {
    cache.put("test1", "val1");
    cache.put("test2", "val2");
    cache.put("test3", "val3");

    assertEquals("val1", cache.getByKey("test1"));
    assertEquals("val1", cache.getByKey("test1"));
    assertEquals("val2", cache.getByKey("test2"));

    cache.put("new", "element");
    assertEquals("element", cache.getByKey("new"));
    assertNull(cache.getByKey("test3"));

    cache.put("last", "one");
    assertEquals("one", cache.getByKey("last"));
    assertNull(cache.getByKey("test2"));
  }
}

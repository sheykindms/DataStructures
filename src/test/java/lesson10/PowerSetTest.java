package lesson10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PowerSetTest {

  PowerSet set1;
  PowerSet set2;

  @BeforeEach
  void setUp() {
    set1 = new PowerSet();
    set2 = new PowerSet();
  }

  @Test
  void intersection() {
    set1.put("test1");
    set1.put("test2");
    set1.put("test3");

    set2.put("test3");
    set2.put("test4");
    set2.put("test5");

    PowerSet intersection = set1.intersect(set2);

    assertTrue(intersection.get("test3"));
    assertFalse(intersection.get("test1"));
    assertFalse(intersection.get("test5"));
  }

  @Test
  void union() {
    set1.put("test1");
    set1.put("test2");
    set1.put("test3");

    set2.put("test3");
    set2.put("test4");
    set2.put("test5");

    PowerSet union = set1.unite(set2);
    assertEquals(5, union.size());
    assertTrue(union.get("test3"));
    assertTrue(union.get("test1"));
    assertTrue(union.get("test5"));
  }

  @Test
  void difference() {
    set1.put("test1");
    set1.put("test2");
    set1.put("test3");
    set1.put("test4");
    set1.put("test5");

    set2.put("test3");
    set2.put("test4");
    set2.put("test5");

    PowerSet difference = set1.diff(set2);
    assertTrue(difference.get("test1"));
    assertTrue(difference.get("test2"));
    assertFalse(difference.get("test3"));
    assertFalse(difference.get("test4"));
    assertFalse(difference.get("test5"));
  }

  @Test
  void isSubset() {
    set1.put("test1");
    set1.put("test2");
    set1.put("test3");
    set1.put("test4");
    set1.put("test5");

    set2.put("test3");
    set2.put("test4");
    set2.put("test5");

    assertTrue(set1.isSubset(set2));
  }
}

package lesson10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PowerSetTest {
  private PowerSet set1;
  private PowerSet set2;

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

    assertTrue(intersection.getByValue("test3"));
    assertFalse(intersection.getByValue("test1"));
    assertFalse(intersection.getByValue("test5"));
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
    assertTrue(union.getByValue("test3"));
    assertTrue(union.getByValue("test1"));
    assertTrue(union.getByValue("test5"));
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
    assertTrue(difference.getByValue("test1"));
    assertTrue(difference.getByValue("test2"));
    assertFalse(difference.getByValue("test3"));
    assertFalse(difference.getByValue("test4"));
    assertFalse(difference.getByValue("test5"));
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

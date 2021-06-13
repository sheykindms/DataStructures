package lesson10;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {

  private final List<String> set;

  public PowerSet() {
    set = new ArrayList<>(20000);
  }

  public int size() {
    return set.size();
  }

  public void put(String value) {
    if (!set.contains(value)) {
      set.add(value);
    }
  }

  public boolean get(String value) {
    return set.contains(value);
  }

  public boolean remove(String value) {
    return set.remove(value);
  }

  public PowerSet intersection(PowerSet secondSet) {
    var intersectedSet = new PowerSet();
    for (String s : set) {
      if (secondSet.get(s)) {
        intersectedSet.put(s);
      }
    }
    return intersectedSet;
  }

  public PowerSet union(PowerSet secondSet) {
    var unitedSet = new PowerSet();
    for (String s : set) {
      unitedSet.put(s);
    }
    for (var i = 0; i < secondSet.size(); i++) {
      if (!unitedSet.get(secondSet.getElement(i))) {
        unitedSet.put(secondSet.getElement(i));
      }
    }
    return unitedSet;
  }

  public PowerSet difference(PowerSet secondSet) {
    var diffSet = new PowerSet();
    for (String s : set) {
      if (!secondSet.get(s)) {
        diffSet.put(s);
      }
    }
    return diffSet;
  }

  public boolean isSubset(PowerSet set2) {
    for (var i = 0; i < set2.size(); i++) {
      if (!get(set2.getElement(i))) {
        return false;
      }
    }
    return true;
  }

  private String getElement(int index) {
    return set.get(index);
  }
}

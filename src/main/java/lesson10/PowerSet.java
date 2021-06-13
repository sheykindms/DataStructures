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

  public PowerSet intersection(PowerSet set2) {
    var powerSet = new PowerSet();
    for (String s : set) {
      if (set2.get(s)) {
        powerSet.put(s);
      }
    }
    return powerSet;
  }

  public PowerSet union(PowerSet set2) {
    var powerSet = new PowerSet();
    for (String s : set) {
      powerSet.put(s);
    }
    for (var i = 0; i < set2.size(); i++) {
      if (!powerSet.get(set2.getElement(i))) {
        powerSet.put(set2.getElement(i));
      }
    }
    return powerSet;
  }

  public PowerSet difference(PowerSet set2) {
    var powerSet = new PowerSet();
    for (String s : set) {
      if (!set2.get(s)) {
        powerSet.put(s);
      }
    }
    return powerSet;
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

package lesson10;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {
  private final List<String> set;

  private static final int INIT_CAPACITY = 20000;

  public PowerSet() {
    set = new ArrayList<>(INIT_CAPACITY);
  }

  /**
   * Counts the number of elements in PowerSet
   *
   * @return int value
   */
  public int size() {
    return set.size();
  }

  /**
   * Puts the given value into the PowerSet
   *
   * @param value to be added
   */
  public void put(String value) {
    if (!set.contains(value)) {
      set.add(value);
    }
  }

  /**
   * Checks if the given value was found in the PowerSet instance
   *
   * @param value to be found
   * @return true if the value is present in set, false otherwise
   */
  public boolean getByValue(String value) {
    return set.contains(value);
  }

  /**
   * Removes the given value from the PowerSet instance
   *
   * @param value to be removed
   * @return true if value was removed, false otherwise
   */
  public boolean removeByValue(String value) {
    return set.remove(value);
  }

  /**
   * Retrieves values common to two sets
   *
   * @param secondSet to be intersected
   * @return the PowerSet instance
   */
  public PowerSet intersect(PowerSet secondSet) {
    final var intersectedSet = new PowerSet();
    for (String s : set) {
      if (secondSet.getByValue(s)) {
        intersectedSet.put(s);
      }
    }
    return intersectedSet;
  }

  /**
   * Unites the given subset with "this" instance, ignoring duplicated values
   *
   * @param secondSet to be united with
   * @return the PowerSet instance
   */
  public PowerSet unite(PowerSet secondSet) {
    final var unitedSet = new PowerSet();
    for (String s : set) {
      unitedSet.put(s);
    }
    for (var i = 0; i < secondSet.size(); i++) {
      if (!unitedSet.getByValue(secondSet.getElementByIndex(i))) {
        unitedSet.put(secondSet.getElementByIndex(i));
      }
    }
    return unitedSet;
  }

  /**
   * Subtracts all the values, contained in the given PowerSet from the "this" instance
   *
   * @param secondSet to be extracted from "this"
   * @return the PowerSet instance
   */
  public PowerSet diff(PowerSet secondSet) {
    final var diffSet = new PowerSet();
    for (String s : set) {
      if (!secondSet.getByValue(s)) {
        diffSet.put(s);
      }
    }
    return diffSet;
  }

  /**
   * Checks if the given PowerSet instance is a subset of "this"
   *
   * @param secondSet to be verified
   * @return true if subset, false otherwise
   */
  public boolean isSubset(PowerSet secondSet) {
    for (var i = 0; i < secondSet.size(); i++) {
      if (!getByValue(secondSet.getElementByIndex(i))) {
        return false;
      }
    }
    return true;
  }

  private String getElementByIndex(int index) {
    return set.get(index);
  }
}

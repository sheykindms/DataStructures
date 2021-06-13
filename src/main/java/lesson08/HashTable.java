package lesson08;

public class HashTable {
  /** Number of buckets */
  public int size;

  public int step;
  public String[] slots;

  public HashTable(int sz, int stp) {
    size = sz;
    step = stp;
    slots = new String[size];
    for (var i = 0; i < size; i++) slots[i] = null;
  }

  /**
   * Hash function
   *
   * @param value
   * @return index in array
   */
  public int getIndexAsHashFun(String value) {
    final var multiplier = 31;
    if (value.isEmpty()) {
      return 0;
    }
    var hash = 1;
    for (char c : value.toCharArray()) {
      hash = hash * multiplier + c;
    }
    return Math.abs(hash) % size;
  }

  /**
   * Finds a slot according to hash value of input string
   *
   * @param value
   * @return index in array or -1
   */
  public int seekSlot(String value) {
    int foundIndex = getIndexAsHashFun(value);
    var iterations = 0;
    while (slots[foundIndex] != null) {
      if (slots[foundIndex].equals(value)) {
        return foundIndex;
      }
      foundIndex += step;
      if (foundIndex >= size && iterations < step) {
        foundIndex = iterations;
        iterations++;
      }
      if (foundIndex >= size && iterations >= step) {
        return -1;
      }
    }
    return foundIndex;
  }

  /**
   * Puts an element into HashTable
   *
   * @param value
   * @return index of element in array or -1
   */
  public int put(String value) {
    int foundIndex = seekSlot(value);
    boolean found = foundIndex != -1;
    if (found) {
      slots[foundIndex] = value;
    }
    return foundIndex;
  }

  /**
   * Finds index in HashTable
   *
   * @param value
   * @return index of element or -1
   */
  public int findIndexByValue(String value) {
    int foundIndex = seekSlot(value);
    boolean found = foundIndex != -1 && slots[foundIndex] != null;
    if (!found) {
      return -1;
    }
    return foundIndex;
  }
}

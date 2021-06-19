package lesson08;

public class HashTable {

  private final int size;
  private final int step;
  private final String[] slots;

  private static final int HASH_FUN_MULTIPLIER = 31;

  public HashTable(int size, int step) {
    this.size = size;
    this.step = step;
    slots = new String[this.size];
    for (var i = 0; i < this.size; i++) slots[i] = null;
  }

  /**
   * Puts an element into the HashTable
   *
   * @param value to be added
   * @return the index of the element in array or -1 if no element was found
   */
  public int put(String value) {
    final var foundIndex = seekSlot(value);
    boolean found = foundIndex != -1;
    if (found) {
      slots[foundIndex] = value;
    }
    return foundIndex;
  }

  /**
   * Finds the index in HashTable by the given value
   *
   * @param value to be found
   * @return the index of element or -1 if no such element was found
   */
  public int findIndexByValue(String value) {
    final var foundIndex = seekSlot(value);
    final boolean found = foundIndex != -1 && slots[foundIndex] != null;
    if (!found) {
      return -1;
    }
    return foundIndex;
  }

  private int seekSlot(String value) {
    var foundIndex = getIndexAsHashFun(value);
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

  private int getIndexAsHashFun(String value) {
    if (value.isEmpty()) {
      return 0;
    }
    var hash = 1;
    for (char c : value.toCharArray()) {
      hash = hash * HASH_FUN_MULTIPLIER + c;
    }
    return Math.abs(hash) % size;
  }

  int getSize() {
    return size;
  }

  int getStep() {
    return step;
  }

  String[] getSlots() {
    return slots;
  }
}

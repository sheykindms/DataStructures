package lesson08;

public class HashTable {

  public int size;
  public int step;
  public String[] slots;

  static final int HASH_FUN_MULTIPLIER = 31;

  public HashTable(int size, int step) {
    this.size = size;
    this.step = step;
    slots = new String[this.size];
    for (var i = 0; i < this.size; i++) slots[i] = null;
  }

  /**
   * Hash function
   *
   * @param value
   * @return index in array
   */
  public int getIndexAsHashFun(String value) {
    if (value.isEmpty()) {
      return 0;
    }
    var hash = 1;
    for (char c : value.toCharArray()) {
      hash = hash * HASH_FUN_MULTIPLIER + c;
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

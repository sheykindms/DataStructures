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
  public int hashFun(String value) {
    if (value.isEmpty()) {
      return 0;
    }
    var hash = 1;
    for (char c : value.toCharArray()) {
      hash = hash * 31 + c;
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
    int index = hashFun(value);
    var iterations = 0;
    while (slots[index] != null) {
      if (slots[index].equals(value)) {
        return index;
      }
      index += step;
      if (index >= size && iterations < step) {
        index = iterations;
        iterations++;
      }
      if (index >= size && iterations >= step) {
        return -1;
      }
    }
    return index;
  }

  /**
   * Puts an element into HashTable
   *
   * @param value
   * @return index of element in array or -1
   */
  public int put(String value) {
    int index = seekSlot(value);
    if (index != -1) {
      slots[index] = value;
    }
    return index;
  }

  /**
   * Finds index in HashTable
   *
   * @param value
   * @return index of element or -1
   */
  public int find(String value) {
    int index = seekSlot(value);
    if (index == -1 || slots[index] == null) {
      return -1;
    }
    return index;
  }
}

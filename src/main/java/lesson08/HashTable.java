package lesson08;

public class HashTable {
  public int size; // число бакетов (слотов)
  public int step; // длина шага для поиска свободного слота
  public String[] slots;

  public HashTable(int sz, int stp) {
    size = sz;
    step = stp;
    slots = new String[size];
    for (int i = 0; i < size; i++) slots[i] = null;
  }

  /**
   * Hash-function
   *
   * @param value
   * @return index in array
   */
  public int hashFun(String value) {
    byte[] arr = value.getBytes();
    var sumOfBytes = 0;
    for (byte b : arr) {
      sumOfBytes += b;
    }
    return sumOfBytes % size;
  }

  /**
   * Finds a slot according to hash value of input string
   *
   * @param value
   * @return index in array or -1
   */
  public int seekSlot(String value) {
    int index = hashFun(value);
    int iterations = 0;
    while (slots[index] != null) {
      if (slots[index].equals(value)) {
        return index;
      }
      index += step;
      if (index >= size && iterations < step) {
        index = iterations++;
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
    int slot = seekSlot(value);
    if (slot != -1) {
      slots[slot] = value;
      return slot;
    }
    return -1;
  }

  /**
   * Finds index in HashTable
   *
   * @param value
   * @return index of element or -1
   */
  public int find(String value) {
    int index = seekSlot(value);
    if (slots[index] != null) {
      return index;
    }
    return -1;
  }
}

package lesson09;

import java.lang.reflect.Array;

public class NativeDictionary<T> {
  private final int size;
  private final String[] slots;
  private final T[] values;

  private static final int HASH_FUN_MULTIPLIER = 31;

  public NativeDictionary(int size, Class<T> clazz) {
    this.size = size;
    slots = new String[this.size];
    values = (T[]) Array.newInstance(clazz, this.size);
  }

  /**
   * Checks if the given value is a valid key, stored in NativeDictionary instance
   *
   * @param key to check
   * @return true if key was found, false otherwise
   */
  public boolean isKey(String key) {
    final var foundIndex = seekSlot(key);
    final boolean found = foundIndex != -1;
    if (!found) {
      return false;
    }
    return slots[foundIndex] != null;
  }

  /**
   * Adds the given key-value pair into the NativeCache. If the key was already stored, replaces its
   * value with the new one.
   *
   * @param key to store
   * @param value to store
   */
  public void put(String key, T value) {
    final var foundIndex = seekSlot(key);
    final boolean found = foundIndex != -1;
    if (!found) {
      return;
    }
    slots[foundIndex] = key;
    values[foundIndex] = value;
  }

  /**
   * Gets the value from NativeDictionary by its key
   *
   * @param key to found the value
   * @return the value, corresponding to the given key, null if the key wasn't found
   */
  public T getValueByKey(String key) {
    final var foundIndex = seekSlot(key);
    final boolean found = foundIndex != -1 && slots[foundIndex] != null;
    if (!found) {
      return null;
    }
    return values[foundIndex];
  }

  private int getIndexAsHashFun(String key) {
    if (key.isEmpty()) {
      return 0;
    }
    var hash = 1;
    for (char c : key.toCharArray()) {
      hash = hash * HASH_FUN_MULTIPLIER + c;
    }
    return Math.abs(hash) % size;
  }

  private int seekSlot(String value) {
    var index = getIndexAsHashFun(value);
    var step = 1;
    var iterations = 0;
    /*
    In the loop, we want to either find an already existing value or find a place to add a value.
    We iterate over the array, adding a value equal to step at each iteration to foundIndex value.
    When we reach the end of the array, we go back to the beginning and run with the same step until
    he number of iterations is >= step. This is how we ensure optimal coverage of the entire array.
    */
    while (slots[index] != null) {
      if (slots[index].equals(value)) {
        return index;
      }
      index += step;
      if (index >= size && iterations < step) {
        index = iterations;
        iterations++;
      }
      if (index >= size) {
        return -1;
      }
    }
    return index;
  }
}

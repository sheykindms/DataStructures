package lesson12;

import java.lang.reflect.Array;

public class NativeCache<T> {
  private final int size;
  private final String[] slots;
  private final T[] values;
  private final int[] hits;

  private static final int HASH_FUN_MULTIPLIER = 31;

  private NativeCache(int size, Class<T> clazz) {
    this.size = size;
    slots = new String[size];
    values = (T[]) Array.newInstance(clazz, size);
    hits = new int[size];
  }

  /**
   * Static factory creates an instance of Native Cache class
   *
   * @param size sets the constant length of the structure
   * @param clazz sets the reference type of stored data
   * @return instance of NativeCache.class
   */
  public static NativeCache withSizeAndClass(int size, Class clazz) {
    return new NativeCache<>(size, clazz);
  }

  /**
   * Checks if the given value is a valid key, stored in NativeCache instance
   *
   * @param key to check
   * @return true if key was found, false otherwise
   */
  public boolean isKey(String key) {
    final var foundIndex = seekSlot(key);
    return foundIndex != -1 && slots[foundIndex] != null;
  }

  /**
   * Adds the given key-value pair into the NativeCache and increments the value of the number of
   * requests for an item. If the key was already stored, replaces its value with the new one.
   *
   * @param key to store
   * @param value to store
   */
  public void put(String key, T value) {
    final var foundIndex = seekSlot(key);
    final boolean found = foundIndex != -1;
    if (!found) {
      replaceWithNew(key, value);
    } else {
      slots[foundIndex] = key;
      values[foundIndex] = value;
      hits[foundIndex]++;
    }
  }

  /**
   * Gets the value from NativeCache by its key
   *
   * @param key to found the value
   * @return the value, corresponding to the given key, null if the key wasn't found
   */
  public T getByKey(String key) {
    final var foundIndex = seekSlot(key);
    final boolean found = foundIndex != -1 && slots[foundIndex] != null;
    if (!found) {
      return null;
    }
    hits[foundIndex]++;
    return values[foundIndex];
  }

  private void replaceWithNew(String key, T value) {
    int minValue = hits[0];
    var indexOfElementToReplace = 0;
    for (var i = 0; i < hits.length; i++) {
      if (minValue > hits[i]) {
        minValue = hits[i];
        indexOfElementToReplace = i;
      }
    }
    slots[indexOfElementToReplace] = key;
    values[indexOfElementToReplace] = value;
    hits[indexOfElementToReplace] = 1;
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
    var expectedIndex = getIndexAsHashFun(value);
    var step = 1;
    var iterations = 0;
    /*
    In the loop, we want to either find an already existing value or find a place to add a value.
    We iterate over the array, adding a value equal to step at each iteration to foundIndex value.
    When we reach the end of the array, we go back to the beginning and run with the same step until
    he number of iterations is >= step. This is how we ensure optimal coverage of the entire array.
    */
    while (slots[expectedIndex] != null) {
      if (slots[expectedIndex].equals(value)) {
        return expectedIndex;
      }
      expectedIndex += step;
      if (expectedIndex >= size && iterations < step) {
        expectedIndex = iterations;
        iterations++;
      }
      if (expectedIndex >= size) {
        return -1;
      }
    }
    return expectedIndex;
  }
}

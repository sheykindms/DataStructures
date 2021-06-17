package lesson12;

import java.lang.reflect.Array;

public class NativeCache<T> {

  private final int size;
  private final String[] slots;
  private final T[] values;
  private final int[] hits;

  static final int HASH_FUN_MULTIPLIER = 31;

  private NativeCache(int size, Class<T> clazz) {
    this.size = size;
    slots = new String[size];
    values = (T[]) Array.newInstance(clazz, size);
    hits = new int[size];
  }

  public static NativeCache withSizeAndClass(int size, Class clazz) {
    return new NativeCache<>(size, clazz);
  }

  public int getIndexAsHashFun(String key) {
    if (key.isEmpty()) {
      return 0;
    }
    var hash = 1;
    for (char c : key.toCharArray()) {
      hash = hash * HASH_FUN_MULTIPLIER + c;
    }
    return Math.abs(hash) % size;
  }

  public boolean isKey(String key) {
    final var foundIndex = seekSlot(key);
    return foundIndex != -1 && slots[foundIndex] != null;
  }

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

  private int seekSlot(String value) {
    var expectedIndex = getIndexAsHashFun(value);
    var step = 1;
    var iterations = 0;
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

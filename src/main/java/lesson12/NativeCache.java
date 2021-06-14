package lesson12;

import java.lang.reflect.Array;

public class NativeCache<T> {

  public final int size;
  public final String[] slots;
  public final T[] values;
  public final int[] hits;

  static final int HASH_FUN_MULTIPLIER = 31;

  private NativeCache(int size, Class clazz) {
    this.size = size;
    slots = new String[size];
    values = (T[]) Array.newInstance(clazz, size);
    hits = new int[size];
  }

  public static NativeCache withSizeAndClass(int size, Class clazz) {
    return new NativeCache(size, clazz);
  }

  public int hashFun(String key) {
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
    var foundIndex = seekSlot(key);
    if (foundIndex == -1) {
      return false;
    }
    return slots[foundIndex] != null;
  }

  public void put(String key, T value) {
    var foundIndex = seekSlot(key);
    boolean found = foundIndex != -1;
    if (!found) {
      replaceWithNew(key, value);
    } else {
      slots[foundIndex] = key;
      values[foundIndex] = value;
      hits[foundIndex]++;
    }
  }

  public T get(String key) {
    var foundIndex = seekSlot(key);
    boolean found = foundIndex != -1 && slots[foundIndex] != null;
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
    var expectedIndex = hashFun(value);
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

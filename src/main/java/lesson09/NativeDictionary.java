package lesson09;

import java.lang.reflect.Array;

class NativeDictionary<T> {
  public int size;
  public String[] slots;
  public T[] values;

  public NativeDictionary(int sz, Class clazz) {
    size = sz;
    slots = new String[size];
    values = (T[]) Array.newInstance(clazz, this.size);
  }

  public int hashFun(String key) {
    final var multiplier = 31;
    if (key.isEmpty()) {
      return 0;
    }
    var hash = 1;
    for (char c : key.toCharArray()) {
      hash = hash * multiplier + c;
    }
    return Math.abs(hash) % size;
  }

  public boolean isKey(String key) {
    var foundIndex = seekSlot(key);
    boolean found = foundIndex != -1;
    if (!found) {
      return false;
    }
    return slots[foundIndex] != null;
  }

  public void put(String key, T value) {
    var foundIndex = seekSlot(key);
    boolean found = foundIndex != -1;
    if (!found) {
      return;
    }
    slots[foundIndex] = key;
    values[foundIndex] = value;
  }

  public T get(String key) {
    var foundIndex = seekSlot(key);
    boolean found = foundIndex != -1 && slots[foundIndex] != null;
    if (!found) {
      return null;
    }
    return values[foundIndex];
  }

  private int seekSlot(String value) {
    var index = hashFun(value);
    var step = 1;
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
      if (index >= size) {
        return -1;
      }
    }
    return index;
  }
}

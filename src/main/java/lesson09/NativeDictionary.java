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
    final boolean found = foundIndex != -1;
    if (!found) {
      return false;
    }
    return slots[foundIndex] != null;
  }

  public void put(String key, T value) {
    final var foundIndex = seekSlot(key);
    final boolean found = foundIndex != -1;
    if (!found) {
      return;
    }
    slots[foundIndex] = key;
    values[foundIndex] = value;
  }

  public T getValueByKey(String key) {
    final var foundIndex = seekSlot(key);
    final boolean found = foundIndex != -1 && slots[foundIndex] != null;
    if (!found) {
      return null;
    }
    return values[foundIndex];
  }

  private int seekSlot(String value) {
    var index = getIndexAsHashFun(value);
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

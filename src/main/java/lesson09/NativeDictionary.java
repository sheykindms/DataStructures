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
    if (key.isEmpty()) {
      return 0;
    }
    var hash = 1;
    for (char c : key.toCharArray()) {
      hash = hash * 31 + c;
    }
    return Math.abs(hash) % size;
  }

  public boolean isKey(String key) {
    var index = seekSlot(key);
    if (index == -1) {
      return false;
    }
    return slots[index] != null;
  }

  public void put(String key, T value) {
    var index = seekSlot(key);
    if (index == -1) {
      return;
    }
    slots[index] = key;
    values[index] = value;
  }

  public T get(String key) {
    var index = seekSlot(key);
    if (index == -1 || slots[index] == null) {
      return null;
    }
    return values[index];
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

package lesson12;

import java.lang.reflect.Array;

public class NativeCache<T> {

  public int size;
  public String[] slots;
  public T[] values;
  public int[] hits;

  public NativeCache(int size, Class clazz) {
    this.size = size;
    slots = new String[size];
    values = (T[]) Array.newInstance(clazz, size);
    hits = new int[size];
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
      replaceWithNew(key, value);
    } else {
      slots[index] = key;
      values[index] = value;
      hits[index]++;
    }
  }

  public T get(String key) {
    var index = seekSlot(key);
    if (index == -1 || slots[index] == null) {
      return null;
    }
    hits[index]++;
    return values[index];
  }

  private void replaceWithNew(String key, T value) {
    // min - minValue
    // Имя для более явного указания, что в переменной хранится минимальное значение массива
    int minValue = hits[0];
    // index - indexOfElementToReplace
    // Индекс, по которому мы будем заменять элемент на новый
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

package lesson03;

import java.lang.reflect.Array;

public class DynArray<T> {
  private T[] array;
  private int count;
  private int capacity;
  private final Class<T> clazz;

  private static final int MIN_CAPACITY = 16;
  private static final double INCREASING_MULTIPLIER = 2.0;
  private static final double DECREASING_MULTIPLIER = 1.5;

  private DynArray(Class<T> clz) {
    clazz = clz;
    count = 0;
    instantiateArrayWithNewCapacity(MIN_CAPACITY);
  }

  public static DynArray ofClass(Class clz) {
    return new DynArray<>(clz);
  }

  public void instantiateArrayWithNewCapacity(int newCapacity) {
    if (capacity != 0) {
      newCapacity = Math.max(newCapacity, MIN_CAPACITY);
      T[] augmentedCapacityArray = (T[]) Array.newInstance(clazz, newCapacity);
      System.arraycopy(array, 0, augmentedCapacityArray, 0, array.length);
      array = augmentedCapacityArray;
    } else {
      array = (T[]) Array.newInstance(clazz, newCapacity);
    }
    capacity = newCapacity;
  }

  public T getValueByIndex(int index) {
    final boolean outOfBounds = index < 0 || index >= count;
    if (outOfBounds) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return array[index];
  }

  public void append(T item) {
    if (capacity > count) {
      array[count++] = item;
    } else if (capacity == count) {
      instantiateArrayWithNewCapacity((int) (capacity * INCREASING_MULTIPLIER));
      array[count++] = item;
    }
  }

  public void insertValueByIndex(T item, int index) {
    if (index < 0 || index > count) {
      throw new ArrayIndexOutOfBoundsException();
    } else if (count == capacity) {
      capacity *= INCREASING_MULTIPLIER;
      T[] augmentedCapacityArray = (T[]) Array.newInstance(clazz, capacity);
      System.arraycopy(array, 0, augmentedCapacityArray, 0, index);
      System.arraycopy(array, index, augmentedCapacityArray, index + 1, count++ - index);
      augmentedCapacityArray[index] = item;
      array = augmentedCapacityArray;
    } else {
      System.arraycopy(array, index, array, index + 1, count++ - index);
      array[index] = item;
    }
  }

  public void removeByIndex(int index) {
    final boolean outOfBounds = index < 0 || index >= count;
    if (outOfBounds) {
      throw new ArrayIndexOutOfBoundsException();
    } else if (count - 1 < capacity / INCREASING_MULTIPLIER) {
      capacity /= DECREASING_MULTIPLIER;
      if (capacity < MIN_CAPACITY) {
        capacity = MIN_CAPACITY;
      }
      T[] augmentedCapacityArray = (T[]) Array.newInstance(clazz, capacity);
      System.arraycopy(array, 0, augmentedCapacityArray, 0, index);
      System.arraycopy(array, index + 1, augmentedCapacityArray, index, count-- - index + 1);
      array = augmentedCapacityArray;
    } else {
      System.arraycopy(array, 0, array, 0, index);
      System.arraycopy(array, index + 1, array, index, count - index - 1);
      array[--count] = null;
    }
  }

  public T[] getArray() {
    return array;
  }

  public int getCount() {
    return count;
  }

  public int getCapacity() {
    return capacity;
  }
}

package lesson03;

import java.lang.reflect.Array;

public class DynArray<T> {

  public T[] array;
  public int count;
  public int capacity;
  Class clazz;

  private static final int MIN_CAPACITY = 16;
  private static final double INCREASING_MULTIPLIER = 2.0;
  private static final double DECREASING_MULTIPLIER = 1.5;

  private DynArray(Class clz) {
    clazz = clz;
    count = 0;
    instantiateArrayWithNewCapacity(16);
  }

  public static DynArray ofClass(Class clz) {
    return new DynArray<>(clz);
  }

  public void instantiateArrayWithNewCapacity(int newCapacity) {
    if (capacity != 0) {
      newCapacity = Math.max(newCapacity, MIN_CAPACITY);
      T[] augmentedCapacityArray = (T[]) Array.newInstance(this.clazz, newCapacity);
      System.arraycopy(array, 0, augmentedCapacityArray, 0, array.length);
      array = augmentedCapacityArray;
    } else {
      array = (T[]) Array.newInstance(this.clazz, newCapacity);
    }
    capacity = newCapacity;
  }

  public T getValueByIndex(int index) {
    if (index < 0 || index >= count) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return array[index];
  }

  public void append(T itm) {
    if (capacity > count) {
      array[count++] = itm;
    } else if (capacity == count) {
      instantiateArrayWithNewCapacity((int) (capacity * INCREASING_MULTIPLIER));
      array[count++] = itm;
    }
  }

  public void insertValueByIndex(T itm, int index) {
    if (index < 0 || index > count) {
      throw new ArrayIndexOutOfBoundsException();
    } else if (count == capacity) {
      capacity *= 2;
      T[] augmentedCapacityArray = (T[]) Array.newInstance(this.clazz, capacity);
      System.arraycopy(array, 0, augmentedCapacityArray, 0, index);
      System.arraycopy(array, index, augmentedCapacityArray, index + 1, count++ - index);
      augmentedCapacityArray[index] = itm;
      array = augmentedCapacityArray;
    } else {
      System.arraycopy(array, index, array, index + 1, count++ - index);
      array[index] = itm;
    }
  }

  public void removeByIndex(int index) {
    if (index < 0 || index >= count) {
      throw new ArrayIndexOutOfBoundsException();
    } else if (count - 1 < capacity / INCREASING_MULTIPLIER) {
      capacity /= DECREASING_MULTIPLIER;
      if (capacity < MIN_CAPACITY) {
        capacity = MIN_CAPACITY;
      }
      T[] augmentedCapacityArray = (T[]) Array.newInstance(this.clazz, capacity);
      System.arraycopy(array, 0, augmentedCapacityArray, 0, index);
      System.arraycopy(array, index + 1, augmentedCapacityArray, index, count - index + 1);
      array = augmentedCapacityArray;
      count--;
    } else {
      System.arraycopy(array, 0, array, 0, index);
      System.arraycopy(array, index + 1, array, index, count - index - 1);
      array[--count] = null;
    }
  }
}

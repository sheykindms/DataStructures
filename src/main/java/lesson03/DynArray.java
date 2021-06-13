package lesson03;

import java.lang.reflect.Array;

public class DynArray<T> {

  public T[] array;
  public int count;
  public int capacity;
  Class clazz;

  public DynArray(Class clz) {
    clazz = clz;
    count = 0;
    instantiateArrayWithNewCapacity(16);
  }

  public void instantiateArrayWithNewCapacity(int new_capacity) {
    if (capacity != 0) {
      new_capacity = Math.max(new_capacity, 16);
      T[] augmentedCapacityArray = (T[]) Array.newInstance(this.clazz, new_capacity);
      System.arraycopy(array, 0, augmentedCapacityArray, 0, array.length);
      array = augmentedCapacityArray;
    } else {
      array = (T[]) Array.newInstance(this.clazz, new_capacity);
    }
    capacity = new_capacity;
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
      instantiateArrayWithNewCapacity(capacity * 2);
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
    } else if (count - 1 < capacity / 2) {
      capacity /= 1.5;
      if (capacity < 16) {
        capacity = 16;
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

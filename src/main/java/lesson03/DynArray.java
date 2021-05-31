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
    makeArray(16);
  }

  public void makeArray(int new_capacity) {
    if (capacity != 0) {
      new_capacity = new_capacity >= 16 ? new_capacity : 16;
      T[] newArray = (T[]) Array.newInstance(this.clazz, new_capacity);
      System.arraycopy(array, 0, newArray, 0, array.length);
      array = newArray;
    } else {
      array = (T[]) Array.newInstance(this.clazz, new_capacity);
    }
    capacity = new_capacity;
  }

  public T getItem(int index) {
    if (index < 0 || index >= count) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return array[index];
  }

  public void append(T itm) {
    if (capacity > count) {
      array[count++] = itm;
    } else if (capacity == count) {
      makeArray(capacity * 2);
      array[count++] = itm;
    }
  }

  public void insert(T itm, int index) {
    if (index < 0 || index > count) {
      throw new ArrayIndexOutOfBoundsException();
    } else if (count == capacity) {
      capacity *= 2;
      T[] newArray = (T[]) Array.newInstance(this.clazz, capacity);
      System.arraycopy(array, 0, newArray, 0, index);
      System.arraycopy(array, index, newArray, index + 1, count++ - index);
      newArray[index] = itm;
      array = newArray;
    } else {
      System.arraycopy(array, index, array, index + 1, count++ - index);
      array[index] = itm;
    }
  }

  public void remove(int index) {
    if (index < 0 || index > count) {
      throw new ArrayIndexOutOfBoundsException();
    } else if (count - 1 < capacity / 2) {
      capacity /= 1.5;
      if (capacity < 16) {
        capacity = 16;
      }
      T[] newArray = (T[]) Array.newInstance(this.clazz, capacity);
      System.arraycopy(array, 0, newArray, 0, count - index - 1);
      System.arraycopy(array, index + 1, newArray, 0, count - index + 1);
      array = newArray;
      count--;
    } else {
      System.arraycopy(array, 0, array, 0, count - index - 1);
      System.arraycopy(array, index + 1, array, 0, count - index + 1);
      count--;
    }
  }
}

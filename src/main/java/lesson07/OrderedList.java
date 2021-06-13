package lesson07;

import java.util.*;

class Node<T> {
  public T value;
  public Node<T> next, prev;

  public Node(T _value) {
    value = _value;
    next = null;
    prev = null;
  }
}

public class OrderedList<T> {
  public Node<T> head, tail;
  private boolean _ascending;

  public OrderedList(boolean asc) {
    head = null;
    tail = null;
    _ascending = asc;
  }

  /**
   * Compares two elements of Integer class or String class
   *
   * @return v1 < v2 = -1; v1 > v2 = 1; v1 == v2 = 0
   */
  public int compare(T v1, T v2) {
    if (v1 instanceof Integer) {
      return Integer.compare(((Integer) v1).compareTo((Integer) v2), 0);
    } else if (v1 instanceof String) {
      return Integer.compare((((String) v1).trim()).compareTo(((String) v2).trim()), 0);
    } else
      throw new UnsupportedOperationException(
          "Comparing is not supported for the class (yet): " + v1.getClass().getSimpleName());
  }

  /**
   * Adds an element in the List by its value
   *
   * @param value to add according to ordering
   */
  public void add(T value) {
    Node<T> newNode = new Node<>(value);
    if (head == null) {
      head = newNode;
      tail = newNode;
      return;
    }
    Node<T> currentNode = head;
    if (_ascending) {
      if (compare(head.value, newNode.value) > 0) {
        newNode.prev = null;
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        return;
      }
      while (true) {
        if (currentNode.next != null && compare(currentNode.next.value, value) > 0) {
          newNode.next = currentNode.next;
          currentNode.next = newNode;
          newNode.prev = currentNode;
          newNode.next.prev = newNode;
          return;
        } else if (currentNode.next == null) {
          newNode.next = null;
          newNode.prev = tail;
          tail.next = newNode;
          tail = newNode;
          return;
        }
        currentNode = currentNode.next;
      }
    } else {
      if (compare(head.value, newNode.value) < 0) {
        newNode.prev = null;
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        return;
      }
      while (true) {
        if (currentNode.next != null && compare(currentNode.next.value, value) < 0) {
          newNode.next = currentNode.next;
          currentNode.next = newNode;
          newNode.prev = currentNode;
          newNode.next.prev = newNode;
          return;
        } else if (currentNode.next == null) {
          newNode.next = null;
          newNode.prev = tail;
          tail.next = newNode;
          tail = newNode;
          return;
        }
        currentNode = currentNode.next;
      }
    }
  }

  /**
   * Finds an element in the List by its value
   *
   * @param val to find
   * @return Node object or null if nothing was found
   */
  public Node<T> find(T val) {
    Node<T> currentNode = head;
    if (_ascending) {
      while (currentNode != null && compare(currentNode.value, val) <= 0) {
        if (currentNode.value.equals(val)) {
          return currentNode;
        }
        currentNode = currentNode.next;
      }
    } else {
      while (currentNode != null && compare(currentNode.value, val) >= 0) {
        if (currentNode.value.equals(val)) {
          return currentNode;
        }
        currentNode = currentNode.next;
      }
    }
    return null;
  }

  /**
   * Deletes fist occurrence of DNone with given value
   *
   * @param val to be found and deleted
   */
  public void delete(T val) {
    var foundNode = find(val);
    if (foundNode != null) {
      if (this.head.value == this.tail.value && this.head.value.equals(val) && count() == 1) {
        clear(_ascending);
      } else if (this.tail.value == val) {
        this.tail = this.tail.prev;
        this.tail.next = null;
      } else if (this.head.value == val) {
        this.head = this.head.next;
        this.head.prev = null;
      } else {
        foundNode.prev.next = foundNode.next;
        foundNode.next.prev = foundNode.prev;
      }
    }
  }

  /**
   * Clears the List
   *
   * @param asc
   */
  public void clear(boolean asc) {
    _ascending = asc;
    head = null;
    tail = null;
  }

  /**
   * Counts number of elements in the List
   *
   * @return int value
   */
  public int count() {
    Node<T> currentNode = head;
    var numberOfNodes = 0;
    while (currentNode != null) {
      numberOfNodes++;
      currentNode = currentNode.next;
    }
    return numberOfNodes;
  }

  /**
   * Creates the instance of ArrayList with all the elements of List
   *
   * @return ArrayList of Node<T> objects
   */
  ArrayList<Node<T>> getAll() {
    ArrayList<Node<T>> foundNodes = new ArrayList<>();
    Node<T> currentNode = head;
    while (currentNode != null) {
      foundNodes.add(currentNode);
      currentNode = currentNode.next;
    }
    return foundNodes;
  }
}

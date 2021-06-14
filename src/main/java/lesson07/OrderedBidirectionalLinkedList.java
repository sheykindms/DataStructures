package lesson07;

import java.util.*;

class Node<T> {
  final T value;
  Node<T> next, prev;

  public Node(T value) {
    this.value = value;
    next = null;
    prev = null;
  }
}

public class OrderedBidirectionalLinkedList<T> {
  public Node<T> head, tail;
  private boolean isAscending;

  public OrderedBidirectionalLinkedList(boolean isAscending) {
    head = null;
    tail = null;
    this.isAscending = isAscending;
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
  public void addValue(T value) {
    Node<T> newNode = new Node<>(value);
    if (head == null) {
      head = newNode;
      tail = newNode;
      return;
    }
    Node<T> currentNode = head;
    if (isAscending) {
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
   * @param value to find
   * @return Node object or null if nothing was found
   */
  public Node<T> getNodeByValue(T value) {
    Node<T> currentNode = head;
    if (isAscending) {
      while (currentNode != null && compare(currentNode.value, value) <= 0) {
        if (currentNode.value.equals(value)) {
          return currentNode;
        }
        currentNode = currentNode.next;
      }
    } else {
      while (currentNode != null && compare(currentNode.value, value) >= 0) {
        if (currentNode.value.equals(value)) {
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
   * @param value to be found and deleted
   */
  public void removeNodeByValue(T value) {
    var foundNode = getNodeByValue(value);
    if (foundNode != null) {
      if (head.value == tail.value && head.value.equals(value) && countNodes() == 1) {
        clearList(isAscending);
      } else if (tail.value == value) {
        tail = tail.prev;
        tail.next = null;
      } else if (head.value == value) {
        head = head.next;
        head.prev = null;
      } else {
        foundNode.prev.next = foundNode.next;
        foundNode.next.prev = foundNode.prev;
      }
    }
  }

  /**
   * Clears the List
   *
   * @param isAscending
   */
  public void clearList(boolean isAscending) {
    this.isAscending = isAscending;
    head = null;
    tail = null;
  }

  /**
   * Counts number of elements in the List
   *
   * @return int value
   */
  public int countNodes() {
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
  public List<Node<T>> getAllNodes() {
    ArrayList<Node<T>> foundNodes = new ArrayList<>();
    Node<T> currentNode = head;
    while (currentNode != null) {
      foundNodes.add(currentNode);
      currentNode = currentNode.next;
    }
    return foundNodes;
  }
}

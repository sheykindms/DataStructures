package lesson07;

import java.util.*;

public class OrderedBidirectionalLinkedList<T> {

  private Node<T> head;
  private Node<T> tail;
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
    final Node<T> newNode = new Node<>(value);
    if (head == null) {
      head = newNode;
      tail = newNode;
      return;
    }
    Node<T> currentNode = head;
    if (isAscending) {
      if (compare(head.getValue(), newNode.getValue()) > 0) {
        newNode.setPrev(null);
        newNode.setNext(head);
        head.setPrev(newNode);
        head = newNode;
        return;
      }
      while (true) {
        if (currentNode.getNext() != null && compare(currentNode.getNext().getValue(), value) > 0) {
          newNode.setNext(currentNode.getNext());
          currentNode.setNext(newNode);
          newNode.setPrev(currentNode);
          newNode.getNext().setPrev(newNode);
          return;
        } else if (currentNode.getNext() == null) {
          newNode.setNext(null);
          newNode.setPrev(tail);
          tail.setNext(newNode);
          tail = newNode;
          return;
        }
        currentNode = currentNode.getNext();
      }
    } else {
      if (compare(head.getValue(), newNode.getValue()) < 0) {
        newNode.setPrev(null);
        newNode.setNext(head);
        head.setPrev(newNode);
        head = newNode;
        return;
      }
      while (true) {
        if (currentNode.getNext() != null && compare(currentNode.getNext().getValue(), value) < 0) {
          newNode.setNext(currentNode.getNext());
          currentNode.setNext(newNode);
          newNode.setPrev(currentNode);
          newNode.getNext().setPrev(newNode);
          return;
        } else if (currentNode.getNext() == null) {
          newNode.setNext(null);
          newNode.setPrev(tail);
          tail.setNext(newNode);
          tail = newNode;
          return;
        }
        currentNode = currentNode.getNext();
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
      while (currentNode != null && compare(currentNode.getValue(), value) <= 0) {
        if (currentNode.getValue().equals(value)) {
          return currentNode;
        }
        currentNode = currentNode.getNext();
      }
    } else {
      while (currentNode != null && compare(currentNode.getValue(), value) >= 0) {
        if (currentNode.getValue().equals(value)) {
          return currentNode;
        }
        currentNode = currentNode.getNext();
      }
    }
    return null;
  }

  /**
   * Deletes fist occurrence of DNone with given value
   *
   * @param value to find and delete
   */
  public void removeNodeByValue(T value) {
    final var foundNode = getNodeByValue(value);
    if (foundNode != null) {
      if (head.getValue() == tail.getValue()
          && head.getValue().equals(value)
          && countNodes() == 1) {
        clearList(isAscending);
      } else if (tail.getValue() == value) {
        tail = tail.getPrev();
        tail.setNext(null);
      } else if (head.getValue() == value) {
        head = head.getNext();
        head.setPrev(null);
      } else {
        foundNode.getPrev().setNext(foundNode.getNext());
        foundNode.getNext().setPrev(foundNode.getPrev());
      }
    }
  }

  /**
   * Clears the List
   *
   * @param isAscending to assign new order of elements
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
      currentNode = currentNode.getNext();
    }
    return numberOfNodes;
  }

  /**
   * Creates the instance of ArrayList with all the elements of List
   *
   * @return ArrayList of Node<T> objects
   */
  public List<Node<T>> getAllNodes() {
    final ArrayList<Node<T>> foundNodes = new ArrayList<>();
    Node<T> currentNode = head;
    while (currentNode != null) {
      foundNodes.add(currentNode);
      currentNode = currentNode.getNext();
    }
    return foundNodes;
  }

  Node<T> getHead() {
    return head;
  }

  Node<T> getTail() {
    return tail;
  }

  boolean isAscending() {
    return isAscending;
  }

}
class Node<T> {
  private final T value;
  private Node<T> next;
  private Node<T> prev;

  public Node(T value) {
    this.value = value;
    next = null;
    prev = null;
  }

  public T getValue() {
    return value;
  }

  public Node<T> getNext() {
    return next;
  }

  public Node<T> getPrev() {
    return prev;
  }

  public void setNext(Node<T> next) {
    this.next = next;
  }

  public void setPrev(Node<T> prev) {
    this.prev = prev;
  }
}

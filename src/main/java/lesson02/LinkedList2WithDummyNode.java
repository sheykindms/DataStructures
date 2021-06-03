package lesson02;

import java.util.*;

/**
 * Self-Made implementation of bidirectional Linked List with "Dummy Node"
 * @param <E>
 */
public class LinkedList2WithDummyNode<E> {
  private final DNode<E> dummyHead;
  private final DNode<E> dummyTail;

  public LinkedList2WithDummyNode() {
    dummyHead = new DNode<>(null);
    dummyTail = new DNode<>(null);
    dummyHead.next = dummyTail;
    dummyTail.prev = dummyHead;
  }

  /**
   * Adds given item in the end of List
   * @param _item to be added
   */
  public void addInTail(DNode<E> _item) {
    dummyTail.prev.next = _item;
    _item.prev = dummyTail.prev;
    dummyTail.prev = _item;
    _item.next = dummyTail;
  }

  /**
   * Finds DNode by its value if exists
   * @param _value to be found
   * @return DNode or null if object has not been found
   */
  public DNode<E> find(E _value) {
    DNode<E> node = dummyHead.next;
    while (node != dummyTail) {
      if (node.value.equals(_value)) {
        return node;
      }
      node = node.next;
    }
    return null;
  }

  /**
   * Finds all the DNodes in List
   * @param _value to be founnd
   * @return instance of List containing founded objects or empty List otherwise
   */
  public List<DNode<E>> findAll(E _value) {
    ArrayList<DNode<E>> dNodes = new ArrayList<>();
    DNode<E> node = dummyHead.next;
    while (node != dummyTail) {
      if (node.value.equals(_value)) {
        dNodes.add(node);
      }
      node = node.next;
    }
    return dNodes;
  }

  /**
   * Removes fist occurrence of DNone with given value
   * @param _value to be found and deleted
   * @return true if element has been found, otherwise returns false
   */
  public boolean remove(E _value) {
    DNode<E> node = dummyHead.next;
    while (node != dummyTail) {
      if (node.value.equals(_value)) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return true;
      }
      node = node.next;
    }
    return false;
  }

  /**
   * Removes all occurrences of DNones with given value
   * @param _value to be found and deleted
   */
  public void removeAll(E _value) {
    while (find(_value) != null) {
      remove(_value);
    }
  }

  /**
   * Clears the List
   */
  public void clear() {
    dummyHead.next = dummyTail;
    dummyTail.prev = dummyHead;
  }

  /**
   * Counts number of the elements in the List
   * @return int value
   */
  public int count() {
    DNode<E> node = dummyHead.next;
    int count = 0;
    while (node != dummyTail) {
      count++;
      node = node.next;
    }
    return count;
  }

  /**
   * Inserts given element after the given Node
   * @param _nodeAfter
   * @param _nodeToInsert to be inserted
   */
  public void insertAfter(DNode<E> _nodeAfter, DNode<E> _nodeToInsert) {
    if (_nodeAfter == null) {
      dummyHead.next.prev = _nodeToInsert;
      _nodeToInsert.next = dummyHead.next;
      _nodeToInsert.prev = dummyHead;
      dummyHead.next = _nodeToInsert;

    } else {
      _nodeAfter.next.prev = _nodeToInsert;
      _nodeToInsert.next = _nodeAfter.next;
      _nodeAfter.next = _nodeToInsert;
      _nodeToInsert.prev = _nodeAfter;
    }
  }
}

class DNode<E> {
  E value;
  DNode<E> next;
  DNode<E> prev;

  public DNode(E value) {
    this.value = value;
  }
}

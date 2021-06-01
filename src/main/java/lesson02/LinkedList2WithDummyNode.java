package lesson02;

import java.util.*;

public class LinkedList2WithDummyNode<E> {
  private final DNode<E> dummyHead;
  private final DNode<E> dummyTail;

  public LinkedList2WithDummyNode() {
    dummyHead = new DNode<>(null);
    dummyTail = new DNode<>(null);
    dummyHead.next = dummyTail;
    dummyTail.prev = dummyHead;
  }

  public void addInTail(DNode<E> _item) {
    dummyTail.prev.next = _item;
    _item.prev = dummyTail.prev;
    dummyTail.prev = _item;
    _item.next = dummyTail;
  }

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

  public void removeAll(E _value) {
    while (find(_value) != null) {
      remove(_value);
    }
  }

  public void clear() {
    dummyHead.next = dummyTail;
    dummyTail.prev = dummyHead;
  }

  public int count() {
    DNode<E> node = dummyHead.next;
    int count = 0;
    while (node != dummyTail) {
      count++;
      node = node.next;
    }
    return count;
  }

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

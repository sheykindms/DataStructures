package lesson02;

import java.util.*;

public class LinkedList2 {
  public Node head;
  public Node tail;

  public LinkedList2() {
    head = null;
    tail = null;
  }

  public void addInTail(Node _item) {
    if (head == null) {
      this.head = _item;
      this.head.next = null;
      this.head.prev = null;
    } else {
      this.tail.next = _item;
      _item.prev = tail;
    }
    this.tail = _item;
  }

  public Node find(int _value) {
    if (this.head != null) {
      Node node = this.head;
      while (node != null) {
        if (node.value == _value) return node;
        node = node.next;
      }
    }
    return null;
  }

  public ArrayList<Node> findAll(int _value) {
    if (this.head != null) {
      ArrayList<Node> nodes = new ArrayList<>();
      Node node = this.head;
      while (node != null) {
        if (node.value == _value) nodes.add(node);
        node = node.next;
      }
      return nodes;
    }
    return new ArrayList<>();
  }

  public boolean remove(int _value) {
    Node foundNode = find(_value);
    if (foundNode != null) {
      if (this.tail.value == this.head.value && this.head.value == _value && count() == 1) {
        clear();
      } else if (this.tail.value == _value) {
        this.tail = this.tail.prev;
        this.tail.next = null;
      } else if (this.head.value == _value) {
        this.head = this.head.next;
        this.head.prev = null;
      } else {
        foundNode.prev.next = foundNode.next;
        foundNode.next.prev = foundNode.prev;
      }
      return true;
    }
    return false;
  }

  public void removeAll(int _value) {
    while (this.find(_value) != null) {
      this.remove(_value);
    }
  }

  public void clear() {
    this.head = null;
    this.tail = null;
  }

  public int count() {
    Node node = this.head;
    int counter = 0;
    while (node != null) {
      counter++;
      node = node.next;
    }
    return counter;
  }

  public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
    if (_nodeAfter == null) {
      _nodeToInsert.next = this.head;
      _nodeToInsert.prev = null;
      this.head = _nodeToInsert;
      if (this.tail == null) {
        this.tail = _nodeToInsert;
      }
    } else {
      if (_nodeAfter != this.tail) {
        _nodeToInsert.next = _nodeAfter.next;
        _nodeAfter.next.prev = _nodeToInsert;
        _nodeAfter.next = _nodeToInsert;
        _nodeToInsert.prev = _nodeAfter;

      } else {
        _nodeAfter.next = _nodeToInsert;
        _nodeToInsert.next = null;
        this.tail = _nodeToInsert;
      }
      _nodeToInsert.prev = _nodeAfter;
    }
  }
}

class Node {
  public int value;
  public Node next;
  public Node prev;

  public Node(int _value) {
    value = _value;
    next = null;
    prev = null;
  }
}

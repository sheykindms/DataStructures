package lesson01;

import java.util.*;

public class LinkedList {
  public Node head;
  public Node tail;

  public LinkedList() {
    head = null;
    tail = null;
  }

  public void addInTail(Node item) {
    if (this.head == null) this.head = item;
    else this.tail.next = item;
    this.tail = item;
  }

  public Node find(int value) {
    Node node = this.head;
    while (node != null) {
      if (node.value == value) return node;
      node = node.next;
    }
    return null;
  }

  public ArrayList<Node> findAll(int _value) {
    ArrayList<Node> nodes = new ArrayList<>();
    Node node = this.head;
    while (node != null) {
      if (node.value == _value) nodes.add(node);
      node = node.next;
    }
    return nodes;
  }

  public boolean remove(int _value) {
    if (this.head != null) {
      Node node = this.head;
      if (node.value == _value) {
        this.head = node.next;
        if (node.next == null) {
          this.tail = null;
        }
        return true;
      } else {
        while (node != null) {
          if (node.next != null && node.next.value == _value) {
            if (node.next.next != null) {
              node.next = node.next.next;
              return true;
            } else {
              node.next = null;
              this.tail = node;
            }
            return true;
          }
          node = node.next;
        }
      }
    }
    return false;
  }

  public void removeAll(int _value) {
    ArrayList<Node> all = findAll(_value);
    for (Node node : all) {
      remove(node.value);
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
      this.head = _nodeToInsert;
      if (this.head.next == null) {
        this.tail = _nodeToInsert;
      }
    } else if (find(_nodeAfter.value) != null) {
      _nodeToInsert.next = _nodeAfter.next;
      _nodeAfter.next = _nodeToInsert;
      if (_nodeToInsert.next == null) {
        this.tail = _nodeToInsert;
      }
    }
  }
}

class Node {
  public int value;
  public Node next;

  public Node(int _value) {
    value = _value;
    next = null;
  }
}

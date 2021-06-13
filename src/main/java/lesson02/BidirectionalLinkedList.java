package lesson02;

import java.util.*;

public class BidirectionalLinkedList {
  public Node head;
  public Node tail;

  public BidirectionalLinkedList() {
    head = null;
    tail = null;
  }

  public void addNodeInTail(Node _item) {
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

  public Node getNodeByValue(int _value) {
    if (this.head != null) {
      var currentNode = this.head;
      while (currentNode != null) {
        if (currentNode.value == _value) return currentNode;
        currentNode = currentNode.next;
      }
    }
    return null;
  }

  public ArrayList<Node> getAllNodesByValue(int _value) {
    if (this.head != null) {
      ArrayList<Node> foundNodes = new ArrayList<>();
      var currentNode = this.head;
      while (currentNode != null) {
        if (currentNode.value == _value) foundNodes.add(currentNode);
        currentNode = currentNode.next;
      }
      return foundNodes;
    }
    return new ArrayList<>();
  }

  public boolean removeNodeByValue(int _value) {
    var foundNode = getNodeByValue(_value);
    if (foundNode != null) {
      if (this.tail.value == this.head.value && this.head.value == _value && countNodes() == 1) {
        clearList();
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

  public void removeAllNodesByValue(int _value) {
    while (getNodeByValue(_value) != null) {
      removeNodeByValue(_value);
    }
  }

  public void clearList() {
    this.head = null;
    this.tail = null;
  }

  public int countNodes() {
    var currentNode = this.head;
    var numberOfNodes = 0;
    while (currentNode != null) {
      numberOfNodes++;
      currentNode = currentNode.next;
    }
    return numberOfNodes;
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

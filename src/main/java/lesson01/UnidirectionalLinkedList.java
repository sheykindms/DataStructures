package lesson01;

import java.util.*;

public class UnidirectionalLinkedList {
  public Node head;
  public Node tail;

  public UnidirectionalLinkedList() {
    head = null;
    tail = null;
  }

  public void addInTail(Node item) {
    if (this.head == null) this.head = item;
    else this.tail.next = item;
    this.tail = item;
  }

  public Node get(int value) {
    var currentNode = this.head;
    while (currentNode != null) {
      if (currentNode.value == value) return currentNode;
      currentNode = currentNode.next;
    }
    return null;
  }

  public ArrayList<Node> getAll(int _value) {
    ArrayList<Node> foundNodes = new ArrayList<>();
    var currentNode = this.head;
    while (currentNode != null) {
      if (currentNode.value == _value) foundNodes.add(currentNode);
      currentNode = currentNode.next;
    }
    return foundNodes;
  }

  public boolean remove(int _value) {
    if (this.head != null) {
      var currentNode = this.head;
      if (currentNode.value == _value) {
        this.head = currentNode.next;
        if (currentNode.next == null) {
          this.tail = null;
        }
        return true;
      } else {
        while (currentNode != null) {
          if (currentNode.next != null && currentNode.next.value == _value) {
            if (currentNode.next.next != null) {
              currentNode.next = currentNode.next.next;
              return true;
            } else {
              currentNode.next = null;
              this.tail = currentNode;
            }
            return true;
          }
          currentNode = currentNode.next;
        }
      }
    }
    return false;
  }

  public void removeAll(int _value) {
    ArrayList<Node> foundNodes = getAll(_value);
    for (Node node : foundNodes) {
      remove(node.value);
    }
  }

  public void clear() {
    this.head = null;
    this.tail = null;
  }

  public int count() {
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
      this.head = _nodeToInsert;
      if (this.head.next == null) {
        this.tail = _nodeToInsert;
      }
    } else if (get(_nodeAfter.value) != null) {
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

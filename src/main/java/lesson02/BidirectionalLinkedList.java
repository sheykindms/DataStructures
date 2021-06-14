package lesson02;

import java.util.*;

public class BidirectionalLinkedList {
  public Node head;
  public Node tail;

  public BidirectionalLinkedList() {
    head = null;
    tail = null;
  }

  public void addNodeInTail(Node item) {
    if (head == null) {
      this.head = item;
      this.head.next = null;
      this.head.prev = null;
    } else {
      this.tail.next = item;
      item.prev = tail;
    }
    this.tail = item;
  }

  public Node getNodeByValue(int value) {
    if (this.head != null) {
      var currentNode = this.head;
      while (currentNode != null) {
        if (currentNode.value == value) return currentNode;
        currentNode = currentNode.next;
      }
    }
    return null;
  }

  public ArrayList<Node> getAllNodesByValue(int value) {
    if (this.head != null) {
      ArrayList<Node> foundNodes = new ArrayList<>();
      var currentNode = this.head;
      while (currentNode != null) {
        if (currentNode.value == value) foundNodes.add(currentNode);
        currentNode = currentNode.next;
      }
      return foundNodes;
    }
    return new ArrayList<>();
  }

  public boolean removeNodeByValue(int value) {
    var foundNode = getNodeByValue(value);
    if (foundNode != null) {
      if (this.tail.value == this.head.value && this.head.value == value && countNodes() == 1) {
        clearList();
      } else if (this.tail.value == value) {
        this.tail = this.tail.prev;
        this.tail.next = null;
      } else if (this.head.value == value) {
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

  public void removeAllNodesByValue(int value) {
    while (getNodeByValue(value) != null) {
      removeNodeByValue(value);
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

  public void insertAfter(Node nodeAfter, Node nodeToInsert) {
    if (nodeAfter == null) {
      nodeToInsert.next = this.head;
      nodeToInsert.prev = null;
      this.head = nodeToInsert;
      if (this.tail == null) {
        this.tail = nodeToInsert;
      }
    } else {
      if (nodeAfter != this.tail) {
        nodeToInsert.next = nodeAfter.next;
        nodeAfter.next.prev = nodeToInsert;
        nodeAfter.next = nodeToInsert;
        nodeToInsert.prev = nodeAfter;

      } else {
        nodeAfter.next = nodeToInsert;
        nodeToInsert.next = null;
        this.tail = nodeToInsert;
      }
      nodeToInsert.prev = nodeAfter;
    }
  }
}

class Node {
  public int value;
  public Node next;
  public Node prev;

  public Node(int value) {
    this.value = value;
    next = null;
    prev = null;
  }
}

package lesson02;

import java.util.*;

public class BidirectionalLinkedList {
  private Node head;
  private Node tail;

  public BidirectionalLinkedList() {
    head = null;
    tail = null;
  }

  public void addNodeInTail(Node item) {
    if (head == null) {
      head = item;
      head.setNext(null);
      head.setPrev(null);
    } else {
      tail.setNext(item);
      item.setPrev(tail);
    }
    tail = item;
  }

  public Node getNodeByValue(int value) {
    if (head != null) {
      var currentNode = head;
      while (currentNode != null) {
        if (currentNode.getValue() == value) return currentNode;
        currentNode = currentNode.getNext();
      }
    }
    return null;
  }

  public List<Node> getAllNodesByValue(int value) {
    final List<Node> foundNodes = new ArrayList<>();
    var currentNode = head;
    while (currentNode != null) {
      if (currentNode.getValue() == value) foundNodes.add(currentNode);
      currentNode = currentNode.getNext();
    }
    return foundNodes;
  }

  public boolean removeNodeByValue(int value) {
    final var foundNode = getNodeByValue(value);
    if (foundNode != null) {
      if (tail.getValue() == head.getValue() && head.getValue() == value && countNodes() == 1) {
        clearList();
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
    head = null;
    tail = null;
  }

  public int countNodes() {
    var currentNode = head;
    var numberOfNodes = 0;
    while (currentNode != null) {
      numberOfNodes++;
      currentNode = currentNode.getNext();
    }
    return numberOfNodes;
  }

  public void insertAfter(Node nodeAfter, Node nodeToInsert) {
    if (nodeAfter == null) {
      nodeToInsert.setNext(head);
      nodeToInsert.setPrev(null);
      head = nodeToInsert;
      if (tail == null) {
        tail = nodeToInsert;
      }
    } else {
      if (nodeAfter != tail) {
        nodeToInsert.setNext(nodeAfter.getNext());
        nodeAfter.getNext().setPrev(nodeToInsert);
        nodeAfter.setNext(nodeToInsert);
        nodeToInsert.setPrev(nodeAfter);
      } else {
        nodeAfter.setNext(nodeToInsert);
        nodeToInsert.setNext(null);
        tail = nodeToInsert;
      }
      nodeToInsert.setPrev(nodeAfter);
    }
  }

  Node getHead() {
    return head;
  }

  Node getTail() {
    return tail;
  }
}

class Node {
  private final int value;
  private Node next;
  private Node prev;

  Node(int value) {
    this.value = value;
    next = null;
    prev = null;
  }

  int getValue() {
    return value;
  }

  Node getNext() {
    return next;
  }

  Node getPrev() {
    return prev;
  }

  void setNext(Node next) {
    this.next = next;
  }

  void setPrev(Node prev) {
    this.prev = prev;
  }
}

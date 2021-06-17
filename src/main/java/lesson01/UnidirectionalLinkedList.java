package lesson01;

import java.util.*;

public class UnidirectionalLinkedList {
  private Node head;
  private Node tail;

  public UnidirectionalLinkedList() {
    head = null;
    tail = null;
  }

  public void addNodeInTail(Node item) {
    if (head == null) head = item;
    else tail.setNext(item);
    tail = item;
  }

  public Node getNodeByValue(int value) {
    var currentNode = head;
    while (currentNode != null) {
      if (currentNode.getValue() == value) return currentNode;
      currentNode = currentNode.getNext();
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
    if (head != null) {
      var currentNode = head;
      if (currentNode.getValue() == value) {
        head = currentNode.getNext();
        if (currentNode.getNext() == null) {
          tail = null;
        }
        return true;
      } else {
        while (currentNode != null) {
          if (currentNode.getNext() != null && currentNode.getNext().getValue() == value) {
            if (currentNode.getNext().getNext() != null) {
              currentNode.setNext(currentNode.getNext().getNext());
              return true;
            } else {
              currentNode.setNext(null);
              tail = currentNode;
            }
            return true;
          }
          currentNode = currentNode.getNext();
        }
      }
    }
    return false;
  }

  public void removeAllNodesByValue(int value) {
    final List<Node> foundNodes = getAllNodesByValue(value);
    for (Node node : foundNodes) {
      removeNodeByValue(node.getValue());
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
      head = nodeToInsert;
      if (head.getNext() == null) {
        tail = nodeToInsert;
      }
    } else if (getNodeByValue(nodeAfter.getValue()) != null) {
      nodeToInsert.setNext(nodeAfter.getNext());
      nodeAfter.setNext(nodeToInsert);
      if (nodeToInsert.getNext() == null) {
        tail = nodeToInsert;
      }
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

  Node(int value) {
    this.value = value;
    next = null;
  }

  int getValue() {
    return value;
  }

  Node getNext() {
    return next;
  }

  void setNext(Node next) {
    this.next = next;
  }
}

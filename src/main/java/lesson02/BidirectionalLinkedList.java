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
      head = item;
      head.next = null;
      head.prev = null;
    } else {
      tail.next = item;
      item.prev = tail;
    }
    tail = item;
  }

  public Node getNodeByValue(int value) {
    if (head != null) {
      var currentNode = head;
      while (currentNode != null) {
        if (currentNode.value == value) return currentNode;
        currentNode = currentNode.next;
      }
    }
    return null;
  }

  public List<Node> getAllNodesByValue(int value) {
    if (head != null) {
      ArrayList<Node> foundNodes = new ArrayList<>();
      var currentNode = head;
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
      if (tail.value == head.value && head.value == value && countNodes() == 1) {
        clearList();
      } else if (tail.value == value) {
        tail = tail.prev;
        tail.next = null;
      } else if (head.value == value) {
        head = head.next;
        head.prev = null;
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
    head = null;
    tail = null;
  }

  public int countNodes() {
    var currentNode = head;
    var numberOfNodes = 0;
    while (currentNode != null) {
      numberOfNodes++;
      currentNode = currentNode.next;
    }
    return numberOfNodes;
  }

  public void insertAfter(Node nodeAfter, Node nodeToInsert) {
    if (nodeAfter == null) {
      nodeToInsert.next = head;
      nodeToInsert.prev = null;
      head = nodeToInsert;
      if (tail == null) {
        tail = nodeToInsert;
      }
    } else {
      if (nodeAfter != tail) {
        nodeToInsert.next = nodeAfter.next;
        nodeAfter.next.prev = nodeToInsert;
        nodeAfter.next = nodeToInsert;
        nodeToInsert.prev = nodeAfter;

      } else {
        nodeAfter.next = nodeToInsert;
        nodeToInsert.next = null;
        tail = nodeToInsert;
      }
      nodeToInsert.prev = nodeAfter;
    }
  }
}

class Node {
  public final int value;
  Node next;
  Node prev;

  public Node(int value) {
    this.value = value;
    next = null;
    prev = null;
  }
}

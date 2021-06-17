package lesson01;

import java.util.*;

public class UnidirectionalLinkedList {
  public Node head;
  public Node tail;

  public UnidirectionalLinkedList() {
    head = null;
    tail = null;
  }

  public void addNodeInTail(Node item) {
    if (head == null) head = item;
    else tail.next = item;
    tail = item;
  }

  public Node getNodeByValue(int value) {
    var currentNode = head;
    while (currentNode != null) {
      if (currentNode.value == value) return currentNode;
      currentNode = currentNode.next;
    }
    return null;
  }

  public List<Node> getAllNodesByValue(int value) {
    final List<Node> foundNodes = new ArrayList<>();
    var currentNode = head;
    while (currentNode != null) {
      if (currentNode.value == value) foundNodes.add(currentNode);
      currentNode = currentNode.next;
    }
    return foundNodes;
  }

  public boolean removeNodeByValue(int value) {
    if (head != null) {
      var currentNode = head;
      if (currentNode.value == value) {
        head = currentNode.next;
        if (currentNode.next == null) {
          tail = null;
        }
        return true;
      } else {
        while (currentNode != null) {
          if (currentNode.next != null && currentNode.next.value == value) {
            if (currentNode.next.next != null) {
              currentNode.next = currentNode.next.next;
              return true;
            } else {
              currentNode.next = null;
              tail = currentNode;
            }
            return true;
          }
          currentNode = currentNode.next;
        }
      }
    }
    return false;
  }

  public void removeAllNodesByValue(int value) {
    final List<Node> foundNodes = getAllNodesByValue(value);
    for (Node node : foundNodes) {
      removeNodeByValue(node.value);
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
      head = nodeToInsert;
      if (head.next == null) {
        tail = nodeToInsert;
      }
    } else if (getNodeByValue(nodeAfter.value) != null) {
      nodeToInsert.next = nodeAfter.next;
      nodeAfter.next = nodeToInsert;
      if (nodeToInsert.next == null) {
        tail = nodeToInsert;
      }
    }
  }
}

class Node {
  final int value;
  Node next;

  public Node(int value) {
    this.value = value;
    next = null;
  }
}

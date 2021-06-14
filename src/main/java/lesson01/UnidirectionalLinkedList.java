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
    if (this.head == null) this.head = item;
    else this.tail.next = item;
    this.tail = item;
  }

  public Node getNodeByValue(int value) {
    var currentNode = this.head;
    while (currentNode != null) {
      if (currentNode.value == value) return currentNode;
      currentNode = currentNode.next;
    }
    return null;
  }

  public ArrayList<Node> getAllNodesByValue(int value) {
    ArrayList<Node> foundNodes = new ArrayList<>();
    var currentNode = this.head;
    while (currentNode != null) {
      if (currentNode.value == value) foundNodes.add(currentNode);
      currentNode = currentNode.next;
    }
    return foundNodes;
  }

  public boolean removeNodeByValue(int value) {
    if (this.head != null) {
      var currentNode = this.head;
      if (currentNode.value == value) {
        this.head = currentNode.next;
        if (currentNode.next == null) {
          this.tail = null;
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

  public void removeAllNodesByValue(int value) {
    ArrayList<Node> foundNodes = getAllNodesByValue(value);
    for (Node node : foundNodes) {
      removeNodeByValue(node.value);
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
      this.head = nodeToInsert;
      if (this.head.next == null) {
        this.tail = nodeToInsert;
      }
    } else if (getNodeByValue(nodeAfter.value) != null) {
      nodeToInsert.next = nodeAfter.next;
      nodeAfter.next = nodeToInsert;
      if (nodeToInsert.next == null) {
        this.tail = nodeToInsert;
      }
    }
  }
}

class Node {
  public int value;
  public Node next;

  public Node(int value) {
    this.value = value;
    next = null;
  }
}

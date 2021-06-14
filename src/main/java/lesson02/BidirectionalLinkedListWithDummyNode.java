package lesson02;

import java.util.*;

/**
 * Self-Made implementation of bidirectional Linked List with "Dummy Node"
 * @param <E>
 */
public class BidirectionalLinkedListWithDummyNode<E> {
  private final DNode<E> dummyHead;
  private final DNode<E> dummyTail;

  public BidirectionalLinkedListWithDummyNode() {
    dummyHead = new DNode<>(null);
    dummyTail = new DNode<>(null);
    dummyHead.next = dummyTail;
    dummyTail.prev = dummyHead;
  }

  /**
   * Adds given item in the end of List
   * @param item to be added
   */
  public void addNodeInTail(DNode<E> item) {
    dummyTail.prev.next = item;
    item.prev = dummyTail.prev;
    dummyTail.prev = item;
    item.next = dummyTail;
  }

  /**
   * Finds DNode by its value if exists
   * @param value to be found
   * @return DNode or null if object has not been found
   */
  public DNode<E> getNodeByValue(E value) {
    DNode<E> currentNode = dummyHead.next;
    while (currentNode != dummyTail) {
      if (currentNode.value.equals(value)) {
        return currentNode;
      }
      currentNode = currentNode.next;
    }
    return null;
  }

  /**
   * Finds all the DNodes in List
   * @param value to be founnd
   * @return instance of List containing founded objects or empty List otherwise
   */
  public List<DNode<E>> getAllNodeByValue(E value) {
    ArrayList<DNode<E>> foundNodes = new ArrayList<>();
    DNode<E> currentNode = dummyHead.next;
    while (currentNode != dummyTail) {
      if (currentNode.value.equals(value)) {
        foundNodes.add(currentNode);
      }
      currentNode = currentNode.next;
    }
    return foundNodes;
  }

  /**
   * Removes fist occurrence of DNone with given value
   * @param value to be found and deleted
   * @return true if element has been found, otherwise returns false
   */
  public boolean removeNodeByValue(E value) {
    DNode<E> currentNode = dummyHead.next;
    while (currentNode != dummyTail) {
      if (currentNode.value.equals(value)) {
        currentNode.prev.next = currentNode.next;
        currentNode.next.prev = currentNode.prev;
        return true;
      }
      currentNode = currentNode.next;
    }
    return false;
  }

  /**
   * Removes all occurrences of DNones with given value
   * @param value to be found and deleted
   */
  public void removeAllNodesByValue(E value) {
    while (getNodeByValue(value) != null) {
      removeNodeByValue(value);
    }
  }

  /**
   * Clears the List
   */
  public void clearList() {
    dummyHead.next = dummyTail;
    dummyTail.prev = dummyHead;
  }

  /**
   * Counts number of the elements in the List
   * @return int value
   */
  public int countNodes() {
    DNode<E> currentNode = dummyHead.next;
    var numberOfNodes = 0;
    while (currentNode != dummyTail) {
      numberOfNodes++;
      currentNode = currentNode.next;
    }
    return numberOfNodes;
  }

  /**
   * Inserts given element after the given Node
   * @param nodeAfter
   * @param nodeToInsert to be inserted
   */
  public void insertAfter(DNode<E> nodeAfter, DNode<E> nodeToInsert) {
    if (nodeAfter == null) {
      dummyHead.next.prev = nodeToInsert;
      nodeToInsert.next = dummyHead.next;
      nodeToInsert.prev = dummyHead;
      dummyHead.next = nodeToInsert;

    } else {
      nodeAfter.next.prev = nodeToInsert;
      nodeToInsert.next = nodeAfter.next;
      nodeAfter.next = nodeToInsert;
      nodeToInsert.prev = nodeAfter;
    }
  }
}

class DNode<E> {
  E value;
  DNode<E> next;
  DNode<E> prev;

  public DNode(E value) {
    this.value = value;
  }
}

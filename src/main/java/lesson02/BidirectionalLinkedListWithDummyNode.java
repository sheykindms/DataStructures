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
    dummyHead.setNext(dummyTail);
    dummyTail.setPrev(dummyHead);
  }

  /**
   * Adds given item in the end of List
   * @param item to be added
   */
  public void addNodeInTail(DNode<E> item) {
    dummyTail.getPrev().setNext(item);
    item.setPrev(dummyTail.getPrev());
    dummyTail.setPrev(item);
    item.setNext(dummyTail);
  }

  /**
   * Finds DNode by its value if exists
   * @param value to be found
   * @return DNode or null if object has not been found
   */
  public DNode<E> getNodeByValue(E value) {
    DNode<E> currentNode = dummyHead.getNext();
    while (currentNode != dummyTail) {
      if (currentNode.getValue().equals(value)) {
        return currentNode;
      }
      currentNode = currentNode.getNext();
    }
    return null;
  }

  /**
   * Finds all the DNodes in List
   * @param value to be founnd
   * @return instance of List containing founded objects or empty List otherwise
   */
  public List<DNode<E>> getAllNodeByValue(E value) {
    final List<DNode<E>> foundNodes = new ArrayList<>();
    DNode<E> currentNode = dummyHead.getNext();
    while (currentNode != dummyTail) {
      if (currentNode.getValue().equals(value)) {
        foundNodes.add(currentNode);
      }
      currentNode = currentNode.getNext();
    }
    return foundNodes;
  }

  /**
   * Removes fist occurrence of DNone with given value
   * @param value to be found and deleted
   * @return true if element has been found, otherwise returns false
   */
  public boolean removeNodeByValue(E value) {
    DNode<E> currentNode = dummyHead.getNext();
    while (currentNode != dummyTail) {
      if (currentNode.getValue().equals(value)) {
        currentNode.getPrev().setNext(currentNode.getNext());
        currentNode.getNext().setPrev(currentNode.getPrev());
        return true;
      }
      currentNode = currentNode.getNext();
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
    dummyHead.setNext(dummyTail);
    dummyTail.setPrev(dummyHead);
  }

  /**
   * Counts number of the elements in the List
   * @return int value
   */
  public int countNodes() {
    DNode<E> currentNode = dummyHead.getNext();
    var numberOfNodes = 0;
    while (currentNode != dummyTail) {
      numberOfNodes++;
      currentNode = currentNode.getNext();
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
      dummyHead.getNext().setPrev(nodeToInsert);
      nodeToInsert.setNext(dummyHead.getNext());
      nodeToInsert.setPrev(dummyHead);
      dummyHead.setNext(nodeToInsert);
    } else {
      nodeAfter.getNext().setPrev(nodeToInsert);
      nodeToInsert.setNext(nodeAfter.getNext());
      nodeAfter.setNext(nodeToInsert);
      nodeToInsert.setPrev(nodeAfter);
    }
  }
}

class DNode<E> {
  private final E value;
  private DNode<E> next;
  private DNode<E> prev;

  DNode(E value) {
    this.value = value;
  }

  E getValue() {
    return value;
  }

  DNode<E> getNext() {
    return next;
  }

  DNode<E> getPrev() {
    return prev;
  }

  void setNext(DNode<E> next) {
    this.next = next;
  }

  void setPrev(DNode<E> prev) {
    this.prev = prev;
  }
}

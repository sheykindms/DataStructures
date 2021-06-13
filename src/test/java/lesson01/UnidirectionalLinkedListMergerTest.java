package lesson01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnidirectionalLinkedListMergerTest {

  private UnidirectionalLinkedList firstList;
  private UnidirectionalLinkedList secondList;

  @BeforeEach
  void setup() {
    firstList = new UnidirectionalLinkedList();
    secondList = new UnidirectionalLinkedList();
  }

  private void fillTheLists() {
    firstList.addNodeInTail(new Node(1));
    firstList.addNodeInTail(new Node(2));
    firstList.addNodeInTail(new Node(3));
    firstList.addNodeInTail(new Node(4));
    secondList.addNodeInTail(new Node(5));
    secondList.addNodeInTail(new Node(6));
    secondList.addNodeInTail(new Node(7));
    secondList.addNodeInTail(new Node(8));
  }

  private void fillWithIncorrectLengths() {
    firstList.addNodeInTail(new Node(1));
    firstList.addNodeInTail(new Node(2));
    firstList.addNodeInTail(new Node(3));
    firstList.addNodeInTail(new Node(4));
    secondList.addNodeInTail(new Node(5));
    secondList.addNodeInTail(new Node(6));
    secondList.addNodeInTail(new Node(7));
  }

  @Test
  void sumOfListsWithIncorrectLengths() {
    fillWithIncorrectLengths();
    assertEquals(0, LinkedListMerger.sumOfLists(firstList, secondList).countNodes());
  }

  @Test
  void sumOfListsWhenBothAreEmpty() {
    assertEquals(0, LinkedListMerger.sumOfLists(firstList, secondList).countNodes());
  }

  @Test
  void sumOfLists() {
    fillTheLists();
    UnidirectionalLinkedList result = LinkedListMerger.sumOfLists(secondList, firstList);
    assertEquals(6, result.head.value);
    assertEquals(12, result.tail.value);
  }
}

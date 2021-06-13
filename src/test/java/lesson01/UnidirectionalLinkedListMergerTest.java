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
    firstList.addInTail(new Node(1));
    firstList.addInTail(new Node(2));
    firstList.addInTail(new Node(3));
    firstList.addInTail(new Node(4));
    secondList.addInTail(new Node(5));
    secondList.addInTail(new Node(6));
    secondList.addInTail(new Node(7));
    secondList.addInTail(new Node(8));
  }

  private void fillWithIncorrectLengths() {
    firstList.addInTail(new Node(1));
    firstList.addInTail(new Node(2));
    firstList.addInTail(new Node(3));
    firstList.addInTail(new Node(4));
    secondList.addInTail(new Node(5));
    secondList.addInTail(new Node(6));
    secondList.addInTail(new Node(7));
  }

  @Test
  void sumOfListsWithIncorrectLengths() {
    fillWithIncorrectLengths();
    assertEquals(0, LinkedListMerger.sumOfLists(firstList, secondList).count());
  }

  @Test
  void sumOfListsWhenBothAreEmpty() {
    assertEquals(0, LinkedListMerger.sumOfLists(firstList, secondList).count());
  }

  @Test
  void sumOfLists() {
    fillTheLists();
    UnidirectionalLinkedList result = LinkedListMerger.sumOfLists(secondList, firstList);
    assertEquals(6, result.head.value);
    assertEquals(12, result.tail.value);
  }
}

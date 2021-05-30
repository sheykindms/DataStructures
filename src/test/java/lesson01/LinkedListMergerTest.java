package lesson01;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListMergerTest {

  private LinkedList firstList;
  private LinkedList secondList;

  @BeforeEach
  void setup() {
    firstList = new LinkedList();
    secondList = new LinkedList();
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
    LinkedList result = LinkedListMerger.sumOfLists(secondList, firstList);
    assertEquals(6, result.head.value);
    assertEquals(12, result.tail.value);
  }
}

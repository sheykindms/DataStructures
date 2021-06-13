package lesson01;

public class LinkedListMerger {

  public static UnidirectionalLinkedList sumOfLists(UnidirectionalLinkedList firstList, UnidirectionalLinkedList secondList) {
    var result = new UnidirectionalLinkedList();
    if (firstList.count() == secondList.count()) {
      var firstListNode = firstList.head;
      var secondListNode = secondList.head;
      for (var i = 0; i < firstList.count(); i++) {
        result.addInTail(new Node(firstListNode.value + secondListNode.value));
        firstListNode = firstListNode.next;
        secondListNode = secondListNode.next;
      }
    }
    return result;
  }
}

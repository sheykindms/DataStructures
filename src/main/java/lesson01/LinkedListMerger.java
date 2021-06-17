package lesson01;

public class LinkedListMerger {

  private LinkedListMerger() {}

  public static UnidirectionalLinkedList sumLists(
      UnidirectionalLinkedList firstList, UnidirectionalLinkedList secondList) {
    final var result = new UnidirectionalLinkedList();
    if (firstList.countNodes() == secondList.countNodes()) {
      var firstListNode = firstList.head;
      var secondListNode = secondList.head;
      for (var i = 0; i < firstList.countNodes(); i++) {
        result.addNodeInTail(new Node(firstListNode.value + secondListNode.value));
        firstListNode = firstListNode.next;
        secondListNode = secondListNode.next;
      }
    } else {
      throw new IllegalArgumentException("Lists have different lengths");
    }
    return result;
  }
}

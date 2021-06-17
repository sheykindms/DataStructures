package lesson01;

public class LinkedListMerger {

  private LinkedListMerger() {}

  public static UnidirectionalLinkedList sumLists(
      UnidirectionalLinkedList firstList, UnidirectionalLinkedList secondList) {
    final var result = new UnidirectionalLinkedList();
    if (firstList.countNodes() == secondList.countNodes()) {
      var firstListNode = firstList.getHead();
      var secondListNode = secondList.getHead();
      for (var i = 0; i < firstList.countNodes(); i++) {
        result.addNodeInTail(new Node(firstListNode.getValue() + secondListNode.getValue()));
        firstListNode = firstListNode.getNext();
        secondListNode = secondListNode.getNext();
      }
    } else {
      throw new IllegalArgumentException("Lists have different lengths");
    }
    return result;
  }
}

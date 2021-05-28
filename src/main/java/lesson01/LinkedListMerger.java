package lesson01;

public class LinkedListMerger {

  public static LinkedList sumOfLists(LinkedList firstList, LinkedList secondList) {
    var result = new LinkedList();
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

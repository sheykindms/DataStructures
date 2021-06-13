package lesson06;

public class DequePalindromeValidator {

  public boolean isPalindrome(String toCheck) {
    Deque<Character> deq = new Deque<>();
    for (int i = 0; i < toCheck.length(); i++) {
      deq.addTail(toCheck.charAt(i));
    }
    int stopIndex = deq.size() % 2 == 0 ? 0 : 1;
    while (deq.size() > stopIndex) {
      if (!deq.removeFront().equals(deq.removeTail())) {
        return false;
      }
    }
    return true;
  }
}

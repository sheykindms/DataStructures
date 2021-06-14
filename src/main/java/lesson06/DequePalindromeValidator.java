package lesson06;

public class DequePalindromeValidator {

  public boolean isPalindrome(String input) {
    Deque<Character> deq = new Deque<>();
    for (var i = 0; i < input.length(); i++) {
      deq.addTail(input.charAt(i));
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

package lesson06;

public class DequePalindromeValidator {

  public static boolean isPalindrome(String input) {
    final Deque<Character> deq = new Deque<>();
    for (var i = 0; i < input.length(); i++) {
      deq.addTail(input.charAt(i));
    }
    //If the string length is even, iterate until the stack size equals 0, if the length is odd - to 1.
    int stopIndex = deq.size() % 2 == 0 ? 0 : 1;
    while (deq.size() > stopIndex) {
      if (!deq.removeFront().equals(deq.removeTail())) {
        return false;
      }
    }
    return true;
  }
}

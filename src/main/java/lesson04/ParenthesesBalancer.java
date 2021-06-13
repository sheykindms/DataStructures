package lesson04;

import java.util.Objects;

public class ParenthesesBalancer {

  public boolean isBalanced(String s) {
    if (!Objects.nonNull(s)
        || !s.contains(")")
        || !s.contains("(")
        || s.isBlank()
        || s.charAt(0) == ')'
        || s.charAt(s.length() - 1) == '(') return false;

    Stack<Character> stack = new Stack<>();
    for (var i = 0; i < s.length(); i++) {
      var currentChar = s.charAt(i);
      if (currentChar == '(') {
        stack.push(currentChar);
      } else if (currentChar == ')') {
        stack.pop();
      }
    }
    return stack.size() == 0;
  }
}

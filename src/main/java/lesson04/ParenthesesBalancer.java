package lesson04;

import java.util.Objects;

public class ParenthesesBalancer {

  public boolean isBalanced(String expression) {
    if (!Objects.nonNull(expression)
        || !expression.contains(")")
        || !expression.contains("(")
        || expression.isBlank()
        || expression.charAt(0) == ')'
        || expression.charAt(expression.length() - 1) == '(') return false;

    Stack<Character> stack = new Stack<>();
    for (var i = 0; i < expression.length(); i++) {
      var currentChar = expression.charAt(i);
      if (currentChar == '(') {
        stack.push(currentChar);
      } else if (currentChar == ')') {
        stack.pop();
      }
    }
    return stack.size() == 0;
  }
}

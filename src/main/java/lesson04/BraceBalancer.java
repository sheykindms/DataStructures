package lesson04;

public class BraceBalancer {

  private static final String CLOSE_BRACKET = ")";
  private static final String OPEN_BRACKET = "(";

  /**
   * Checks if opening and closing parentheses are balanced
   * i.e. "())(" is not balanced, but "(()((())()))" is balanced
   * @param expression to validate
   * @return true if braces are balanced, false otherwise
   */
  public boolean isBalanced(String expression) {
    if (expression == null
        || expression.isBlank()
        || expression.startsWith(CLOSE_BRACKET)
        || expression.endsWith(OPEN_BRACKET)
        || !expression.contains(CLOSE_BRACKET)
        || !expression.contains(OPEN_BRACKET)) return false;

    final Stack<String> stack = new Stack<>();
    String currentChar;
    for (var i = 0; i < expression.length(); i++) {
      currentChar = expression.substring(i, i + 1);
      if (currentChar.equals(OPEN_BRACKET)) {
        stack.push(currentChar);
      } else if (currentChar.equals(CLOSE_BRACKET)) {
        stack.pop();
      }
    }
    return stack.size() == 0;
  }
}

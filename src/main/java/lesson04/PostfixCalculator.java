package lesson04;

public class PostfixCalculator {

  /**
   * Calculates result of the postfix expression using two Stacks
   * i.e. : "1 2 + 3 *" should be 9
   *        "8 2 + 5 * 9 +" should be 59
   * @param expression to be calculated
   * @return Integer result of calculation
   */
  public int calculate(String expression) {
    String numPattern = "[0-9]";
    var expr = new Stack<String>();
    var operands = new Stack<Integer>();

    for (var i = expression.length() - 1; i >= 0; i--) {
      String c = expression.charAt(i) + "";
        String num = c;
        if (c.matches(numPattern)) {
        while(i - 1 >= 0 && (expression.charAt(i - 1)+"").matches(numPattern)) {
          i--;
          String temp = expression.charAt(i) + "";
          num = temp + num;
        }
        expr.push(num);
        }
      else if(c.equals("+") || c.equals("-") || c.equals("/") || c.equals("*")) {
      expr.push(c);
      }
    }

    while (expr.size() > 0) {
      String c = expr.pop();
      if(c.equals("+") || c.equals("-") || c.equals("/") || c.equals("*")) {
        operands.push(processResult(c.charAt(0), operands.pop(), operands.pop()));
      } else {
        operands.push(Integer.valueOf(c));
      }
    }
    return operands.pop();
  }

  /**
   *
   * @param operator to compare and process operation
   * @param second second operand
   * @param first first operand
   * @return result of algebraic operation
   */
  private int processResult(Character operator, Integer second, Integer first) {
    return switch (operator) {
      case '*' -> first * second;
      case '/' -> first / second;
      case '+' -> first + second;
      case '-' -> first - second;
      default -> 0;
    };
  }
}

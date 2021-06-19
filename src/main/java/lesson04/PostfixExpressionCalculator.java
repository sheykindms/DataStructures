package lesson04;

public class PostfixExpressionCalculator {

  private PostfixExpressionCalculator() {
  }

  /**
   * Calculates result of the postfix expression using two Stacks
   * i.e. : "1 2 + 3 *" should be 9
   *        "8 2 + 5 * 9 +" should be 59
   * @param expression to be calculated
   * @return Integer result of calculation
   */
  public static int calculate(String expression) {
    final var data = new Stack<String>();
    String currentElement;
    String number;
    boolean isOperator;
    boolean isOperand;
    //Parse the string by adding elements to a common stack
    for (var currentIndex = expression.length() - 1; currentIndex >= 0; currentIndex--) {
      currentElement = expression.charAt(currentIndex) + "";
      isOperator = Operators.contains(currentElement);
      isOperand = Character.isDigit(currentElement.charAt(0));
      if (isOperand) {
        number = currentElement;
        //concat the current elements into a number as long as the next character exists and it is a digit
        while(isNextCharExistsAndIsDigit(expression, currentIndex)) {
          String nextDigitToAppend = expression.charAt(--currentIndex) + "";
          number = nextDigitToAppend + number;
        }
        data.push(number);
      }
      else if (isOperator) {
        data.push(currentElement);
      }
      else if (!currentElement.isBlank()) {
        throw new IllegalArgumentException("Expression is invalid");
      }
    }

    final var operands = new Stack<Integer>();
    String poppedElement;
    while (data.size() > 0) {
      poppedElement = data.pop();
      isOperator = Operators.contains(poppedElement);
      /*
      If the operand - we put it on the stack for operands, if the operator - we use the "processResult"
      method for the last two elements in the operand stack and put the result back on the operand stack
      */
      if (isOperator) {
        operands.push(processResult(poppedElement, operands.pop(), operands.pop()));
      } else {
        operands.push(Integer.valueOf(poppedElement));
      }
    }
    return operands.pop();
  }

  private static boolean isNextCharExistsAndIsDigit(String expression, int currentIndex) {
    return currentIndex - 1 >= 0 && Character.isDigit(expression.charAt(currentIndex - 1));
  }

  private static int processResult(String operator, Integer secondOperand, Integer firstOperand) {
    return switch (operator) {
      case Operators.MULTIPLY -> firstOperand * secondOperand;
      case Operators.DIVIDE -> firstOperand / secondOperand;
      case Operators.PLUS -> firstOperand + secondOperand;
      case Operators.MINUS -> firstOperand - secondOperand;
      default -> 0;
    };
  }

  private interface Operators {
    String PLUS ="+";
    String MINUS = "-";
    String DIVIDE= "/";
    String MULTIPLY= "*";

    static boolean contains(String value) {
      return value.equals(PLUS)
              || value.equals(MINUS)
              || value.equals(DIVIDE)
              || value.equals(MULTIPLY);
    }
  }
}

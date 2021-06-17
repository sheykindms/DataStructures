package lesson04;

public class PostfixExpressionCalculator {

  private static final String NUM_PATTERN_REGEX = "[0-9]";

  /**
   * Calculates result of the postfix expression using two Stacks
   * i.e. : "1 2 + 3 *" should be 9
   *        "8 2 + 5 * 9 +" should be 59
   * @param expression to be calculated
   * @return Integer result of calculation
   */
  public int calculate(String expression) {
    final var data = new Stack<String>();
    String currentElement;
    String number;
    boolean isOperator;
    boolean isOperand;
    for (var currentIndex = expression.length() - 1; currentIndex >= 0; currentIndex--) {
      currentElement = expression.charAt(currentIndex) + "";
      isOperator = Operators.contains(currentElement);
      isOperand = currentElement.matches(NUM_PATTERN_REGEX);
      if (isOperand) {
        number = currentElement;
        while(nextCharExists(currentIndex) && nextCharIsDigit(expression, currentIndex)) {
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
      if (isOperator) {
        operands.push(processResult(poppedElement, operands.pop(), operands.pop()));
      } else {
        operands.push(Integer.valueOf(poppedElement));
      }
    }
    return operands.pop();
  }

  private boolean nextCharIsDigit(String expression, int index) {
    return (expression.charAt(index - 1) + "").matches(NUM_PATTERN_REGEX);
  }

  private boolean nextCharExists(int index) {
    return index - 1 >= 0;
  }

  private int processResult(String operator, Integer secondOperand, Integer firstOperand) {
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

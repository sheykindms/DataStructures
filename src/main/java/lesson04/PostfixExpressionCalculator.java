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
    var data = new Stack<String>();
    for (var currentIndex = expression.length() - 1; currentIndex >= 0; currentIndex--) {
      String currentElement = expression.charAt(currentIndex) + "";
      String number = currentElement;
      boolean isOperator = Operators.contains(currentElement);
      boolean isOperand = currentElement.matches(NUM_PATTERN_REGEX);
      if (isOperand) {
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

    var operands = new Stack<Integer>();
    while (data.size() > 0) {
      String poppedElement = data.pop();
      boolean isOperator = Operators.contains(poppedElement);
      if (isOperator) {
        operands.push(processResult(poppedElement.charAt(0), operands.pop(), operands.pop()));
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

  private int processResult(Character operator, Integer secondOperand, Integer firstOperand) {
    return switch (operator) {
      case '*' -> firstOperand * secondOperand;
      case '/' -> firstOperand / secondOperand;
      case '+' -> firstOperand + secondOperand;
      case '-' -> firstOperand - secondOperand;
      default -> 0;
    };
  }

  private enum Operators {
    PLUS("+") , MINUS("-"), DIVIDE("/"), MULTIPLY("*");

    private final String operator;

    Operators(String operator) {
      this.operator = operator;
    }

    public String getOperator() {
      return operator;
    }

    public static boolean contains(String value) {
      for (Operators o: Operators.values()) {
        if (o.getOperator().equals(value)) {
          return true;
        }
      }
      return false;
    }
  }
}

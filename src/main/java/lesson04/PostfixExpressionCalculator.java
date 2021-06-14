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
    for (var currentIndexFromEnd = expression.length() - 1; currentIndexFromEnd >= 0; currentIndexFromEnd--) {
      String currentElement = expression.charAt(currentIndexFromEnd) + "";
      String digit = currentElement;
      boolean isOperator = Operators.contains(currentElement);
      if (currentElement.matches(NUM_PATTERN_REGEX)) {
        while(currentIndexFromEnd - 1 >= 0 && (expression.charAt(currentIndexFromEnd - 1) + "").matches(NUM_PATTERN_REGEX)) {
          String nextDigitToAppend = expression.charAt(--currentIndexFromEnd) + "";
          digit = nextDigitToAppend + digit;
        }
        data.push(digit);
      }
      else if(isOperator) {
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
      if(isOperator) {
        operands.push(processResult(poppedElement.charAt(0), operands.pop(), operands.pop()));
      } else {
        operands.push(Integer.valueOf(poppedElement));
      }
    }
    return operands.pop();
  }

  /**
   *
   * @param operator to compare and process operation
   * @param secondOperand second operand
   * @param firstOperand first operand
   * @return result of algebraic operation
   */
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

    private final String operand;

    Operators(String operand) {
      this.operand = operand;
    }

    public String getOperand() {
      return operand;
    }

    public static boolean contains(String value) {
      for(Operators o: Operators.values()) {
        if(o.getOperand().equals(value)) {
          return true;
        }
      }
      return false;
    }
  }
}

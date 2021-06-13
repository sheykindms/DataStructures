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
    var numPattern = "[0-9]";
    var data = new Stack<String>();
    for (var currentIndexFromEnd = expression.length() - 1; currentIndexFromEnd >= 0; currentIndexFromEnd--) {
      // c - currentElement
      //Имя переменной, которое более чётко указывает на то, что мы имеем дело с текущим элементом в цикле
      String currentElement = expression.charAt(currentIndexFromEnd) + "";
      // num - digit
      //Теперь акцент выставлен на то, что это цифра(разряд) числа
      String digit = currentElement;
      if (currentElement.matches(numPattern)) {
        while(currentIndexFromEnd - 1 >= 0 && (expression.charAt(currentIndexFromEnd - 1)+"").matches(numPattern)) {
          String nextDigitToAppend = expression.charAt(--currentIndexFromEnd) + "";
          digit = nextDigitToAppend + digit;
        }
        data.push(digit);
      }
      else if(currentElement.equals("+") || currentElement.equals("-") || currentElement.equals("/") || currentElement.equals("*")) {
        data.push(currentElement);
      }
      else if (currentElement.equals(" ")) {
        continue;
      }
      else {
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

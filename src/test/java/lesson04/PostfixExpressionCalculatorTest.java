package lesson04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostfixExpressionCalculatorTest {

  @Test
  void calculate() {
    String expression4 = "1000 200 / 30 * 15 /";
    assertEquals(10, PostfixExpressionCalculator.calculate(expression4));

    String expression1 = "8 2 + 5 * 9 +";
    assertEquals(59, PostfixExpressionCalculator.calculate(expression1));

    String expression2 = "1    2 + 3 *";
    assertEquals(9, PostfixExpressionCalculator.calculate(expression2));

    String expression3 = "170  2 / 3 * 2 + 7 - 2 / 25 /";
    assertEquals(5, PostfixExpressionCalculator.calculate(expression3));
  }

  @Test
  void calculateWhenIncorrectStatement() {
    String expression1 = "1 2 * 3 + a -";
    assertThrows(IllegalArgumentException.class, ()-> PostfixExpressionCalculator.calculate(expression1));

    String expression2 = "b 2 / 3 -";
    assertThrows(IllegalArgumentException.class, ()-> PostfixExpressionCalculator.calculate(expression2));
  }
}

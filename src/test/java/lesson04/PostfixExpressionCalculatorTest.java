package lesson04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostfixExpressionCalculatorTest {

  PostfixExpressionCalculator pc = new PostfixExpressionCalculator();

  @Test
  void calculate() {
    String expression1 = "8 2 + 5 * 9 +";
    assertEquals(59, pc.calculate(expression1));

    String expression2 = "1    2 + 3 *";
    assertEquals(9, pc.calculate(expression2));

    String expression3 = "170  2 / 3 * 2 + 7 - 2 / 25 /";
    assertEquals(5, pc.calculate(expression3));

    String expression4 = "1000 200 / 30 * 15 /";
    assertEquals(10, pc.calculate(expression4));
  }

  @Test
  void calculateWhenIncorrectStatement() {
    String expression1 = "1 2 * 3 + a -";
    assertThrows(IllegalArgumentException.class, ()-> pc.calculate(expression1));

    String expression2 = "b 2 / 3 -";
    assertThrows(IllegalArgumentException.class, ()-> pc.calculate(expression2));
  }
}

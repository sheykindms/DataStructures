package lesson04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParenthesesBalancerTest {

  ParenthesesBalancer pb = new ParenthesesBalancer();

  @Test
  void balanceWhenFail() {
    String testInput1 = "())(";
    assertFalse(pb.balance(testInput1));

    String testInput2 = "))((";
    assertFalse(pb.balance(testInput2));

    String testInput3 = "((())";
    assertFalse(pb.balance(testInput3));

    String testInput4 = " 2 2";
    assertFalse(pb.balance(testInput4));
  }

  @Test
  void balanceWhenSuccess() {
    String testInput1 = "((()))(()(()))";
    assertTrue(pb.balance(testInput1));

    String testInput2 = "(()((())()))";
    assertTrue(pb.balance(testInput2));
  }
}

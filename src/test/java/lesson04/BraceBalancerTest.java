package lesson04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BraceBalancerTest {
  private final BraceBalancer pb = new BraceBalancer();

  @Test
  void balanceWhenFail() {
    String testInput1 = "())(";
    assertFalse(pb.isBalanced(testInput1));

    String testInput2 = "))((";
    assertFalse(pb.isBalanced(testInput2));

    String testInput3 = "((())";
    assertFalse(pb.isBalanced(testInput3));

    String testInput4 = " 2 2";
    assertFalse(pb.isBalanced(testInput4));
  }

  @Test
  void balanceWhenSuccess() {
    String testInput1 = "((()))(()(()))";
    assertTrue(pb.isBalanced(testInput1));

    String testInput2 = "(()((())()))";
    assertTrue(pb.isBalanced(testInput2));
  }
}

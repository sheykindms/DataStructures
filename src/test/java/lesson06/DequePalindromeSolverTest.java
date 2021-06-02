package lesson06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequePalindromeSolverTest {

  DequePalindromeSolver dqps = new DequePalindromeSolver();

  @Test
  void isPalindrome() {
    assertFalse(dqps.isPalindrome("qwerty"));
    assertTrue(dqps.isPalindrome("qwertyytrewq"));
    assertTrue(dqps.isPalindrome("qwertytrewq"));
  }
}

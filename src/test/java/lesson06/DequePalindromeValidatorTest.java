package lesson06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequePalindromeValidatorTest {

  @Test
  void isPalindrome() {
    assertFalse(DequePalindromeValidator.isPalindrome("qwerty"));
    assertTrue(DequePalindromeValidator.isPalindrome("qwertyytrewq"));
    assertTrue(DequePalindromeValidator.isPalindrome("qwertytrewq"));
  }
}

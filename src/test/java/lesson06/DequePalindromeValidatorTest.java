package lesson06;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DequePalindromeValidatorTest {

  DequePalindromeValidator dpv = new DequePalindromeValidator();

  @Test
  void isPalindrome() {
    assertFalse(dpv.isPalindrome("qwerty"));
    assertTrue(dpv.isPalindrome("qwertyytrewq"));
    assertTrue(dpv.isPalindrome("qwertytrewq"));
  }
}

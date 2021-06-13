package lesson11;

import java.util.BitSet;

public class BloomFilter {

  private final BitSet bits;
  // filter_len - filterLength
  // в соответствии с нейминг конвенцией джавы
  public int filterLength;

  public BloomFilter(int f_len) {
    filterLength = f_len;
    bits = new BitSet(filterLength);
  }

  public int hash1(String str1) {
    final var multiplier = 17;
    var hash = 0;
    for (var i = 0; i < str1.length(); i++) {
      int currentCharAsciiCode = str1.charAt(i);
      hash = hash * multiplier + currentCharAsciiCode;
    }
    return Math.abs(hash) % filterLength;
  }

  public int hash2(String str1) {
    // salt - multiplier
    // Всё-таки salt выполняет в хэшировании несколько иную функцию. Multiplier - более подходящее
    // значение переменной
    final var multiplier = 233;
    var hash = 0;
    var bitShift = 2;
    for (var i = 0; i < str1.length(); i++) {
      // code - currentCharAsciiCode
      // Абстрактное "code" в теле методов hash1 и hash2 класса BloomFilter изменено на более
      // описательное currentCharAsciiCode
      int currentCharAsciiCode = str1.charAt(i);
      hash |= (hash * multiplier + currentCharAsciiCode) << bitShift;
    }
    return Math.abs(hash) % filterLength;
  }

  public void add(String str1) {
    int hash1 = hash1(str1);
    int hash2 = hash2(str1);
    bits.set(hash1);
    bits.set(hash2);
  }

  public boolean isValue(String str1) {
    int hash1 = hash1(str1);
    int hash2 = hash2(str1);
    return bits.get(hash1) && bits.get(hash2);
  }
}

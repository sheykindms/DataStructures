package lesson11;

import java.util.BitSet;

public class BloomFilter {

  private final BitSet bits;
  // filter_len - filterLength
  // в соответствии с нейминг конвенцией джавы
  public final int filterLength;

  public BloomFilter(int f_len) {
    filterLength = f_len;
    bits = new BitSet(filterLength);
  }

  public int firstHashFun(String value) {
    final var multiplier = 17;
    var hash = 0;
    for (var i = 0; i < value.length(); i++) {
      int currentCharAsciiCode = value.charAt(i);
      hash = hash * multiplier + currentCharAsciiCode;
    }
    return Math.abs(hash) % filterLength;
  }

  public int secondHashFun(String value) {
    // salt - multiplier
    // Всё-таки salt выполняет в хэшировании несколько иную функцию. Multiplier - более подходящее
    // значение переменной
    final var multiplier = 233;
    var hash = 0;
    var bitShift = 2;
    for (var i = 0; i < value.length(); i++) {
      // code - currentCharAsciiCode
      // Абстрактное "code" в теле методов hash1 и hash2 класса BloomFilter изменено на более
      // описательное currentCharAsciiCode
      int currentCharAsciiCode = value.charAt(i);
      hash |= (hash * multiplier + currentCharAsciiCode) << bitShift;
    }
    return Math.abs(hash) % filterLength;
  }

  public void add(String value) {
    int firstIndexOfBitToSet = firstHashFun(value);
    int secondIndexOfBitToSet = secondHashFun(value);
    bits.set(firstIndexOfBitToSet);
    bits.set(secondIndexOfBitToSet);
  }

  public boolean isValue(String value) {
    int firstIndexOfBitToCheck = firstHashFun(value);
    int secondIndexOfBitToCheck = secondHashFun(value);
    return bits.get(firstIndexOfBitToCheck) && bits.get(secondIndexOfBitToCheck);
  }
}

package lesson11;

import java.util.BitSet;

public class BloomFilter {
  private final BitSet bits;
  private final int filterLength;

  private static final int FIRST_HASH_FUN_MULTIPLIER = 17;
  private static final int SECOND_HASH_FUN_MULTIPLIER = 233;
  private static final int SECOND_HASH_FUN_BITSHIFT = 2;

  private BloomFilter(int filterLength) {
    this.filterLength = filterLength;
    bits = new BitSet(this.filterLength);
  }

  /**
   * Static factory creates an instance of BloomFilter class
   *
   * @param filterLength sets the constant length of the structure
   * @return instance of BloomFilter.class
   */
  public static BloomFilter withLength(int filterLength) {
    return new BloomFilter(filterLength);
  }

  /**
   * Adds the given value to Bloom Filter
   *
   * @param value to be added
   */
  public void add(String value) {
    final var firstIndexOfBitToSet = getFirstIndexAsHashFun(value);
    final var secondIndexOfBitToSet = getSecondIndexAsHashFun(value);
    bits.set(firstIndexOfBitToSet);
    bits.set(secondIndexOfBitToSet);
  }

  /**
   * Checks if given string present in Bloom Filter
   *
   * @param value to verify
   * @return true if presented, false otherwise
   */
  public boolean isValue(String value) {
    final var firstIndexOfBitToCheck = getFirstIndexAsHashFun(value);
    final var secondIndexOfBitToCheck = getSecondIndexAsHashFun(value);
    return bits.get(firstIndexOfBitToCheck) && bits.get(secondIndexOfBitToCheck);
  }

  private int getFirstIndexAsHashFun(String value) {
    var hash = 0;
    for (var i = 0; i < value.length(); i++) {
      int currentCharAsciiCode = value.charAt(i);
      hash = hash * FIRST_HASH_FUN_MULTIPLIER + currentCharAsciiCode;
    }
    return Math.abs(hash) % filterLength;
  }

  private int getSecondIndexAsHashFun(String value) {
    var hash = 0;
    int currentCharAsciiCode;
    for (var i = 0; i < value.length(); i++) {
      currentCharAsciiCode = value.charAt(i);
      hash |=
          (hash * SECOND_HASH_FUN_MULTIPLIER + currentCharAsciiCode) << SECOND_HASH_FUN_BITSHIFT;
    }
    return Math.abs(hash) % filterLength;
  }
}

package lesson11;

import java.util.BitSet;

public class BloomFilter {

  private final BitSet bits;
  public final int filterLength;

  static final int FIRST_HASH_FUN_MULTIPLIER = 17;
  static final int SECOND_HASH_FUN_MULTIPLIER = 233;

  private BloomFilter(int filterLength) {
    this.filterLength = filterLength;
    bits = new BitSet(this.filterLength);
  }

  public static BloomFilter withLength(int filterLength) {
    return new BloomFilter(filterLength);
  }

  public int getFirstIndexAsHashFun(String value) {
    var hash = 0;
    for (var i = 0; i < value.length(); i++) {
      int currentCharAsciiCode = value.charAt(i);
      hash = hash * FIRST_HASH_FUN_MULTIPLIER + currentCharAsciiCode;
    }
    return Math.abs(hash) % filterLength;
  }

  public int getSecondIndexAsHashFun(String value) {
    var hash = 0;
    var bitShift = 2;
    for (var i = 0; i < value.length(); i++) {
      int currentCharAsciiCode = value.charAt(i);
      hash |= (hash * SECOND_HASH_FUN_MULTIPLIER + currentCharAsciiCode) << bitShift;
    }
    return Math.abs(hash) % filterLength;
  }

  public void add(String value) {
    int firstIndexOfBitToSet = getFirstIndexAsHashFun(value);
    int secondIndexOfBitToSet = getSecondIndexAsHashFun(value);
    bits.set(firstIndexOfBitToSet);
    bits.set(secondIndexOfBitToSet);
  }

  public boolean isValue(String value) {
    int firstIndexOfBitToCheck = getFirstIndexAsHashFun(value);
    int secondIndexOfBitToCheck = getSecondIndexAsHashFun(value);
    return bits.get(firstIndexOfBitToCheck) && bits.get(secondIndexOfBitToCheck);
  }
}

package lesson11;

import java.util.BitSet;

public class BloomFilter {

  private final BitSet bits;
  public final int filterLength;

  private BloomFilter(int filterLength) {
    this.filterLength = filterLength;
    bits = new BitSet(this.filterLength);
  }

  public static BloomFilter withLength(int filterLength) {
    return new BloomFilter(filterLength);
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
    final var multiplier = 233;
    var hash = 0;
    var bitShift = 2;
    for (var i = 0; i < value.length(); i++) {
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

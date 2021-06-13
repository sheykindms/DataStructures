package lesson11;

import java.util.BitSet;

public class BloomFilter {

  private final BitSet bits;
  public int filter_len;

  public BloomFilter(int f_len) {
    filter_len = f_len;
    bits = new BitSet(filter_len);
  }

  public int hash1(String str1) {
    final var multiplier = 17;
    var hash = 0;
    for (var i = 0; i < str1.length(); i++) {
      int currentCharAsciiCode = str1.charAt(i);
      hash = hash * multiplier + currentCharAsciiCode;
    }
    return Math.abs(hash) % filter_len;
  }

  public int hash2(String str1) {
    final var multiplier = 233;
    var hash = 0;
    var bitShift = 2;
    for (var i = 0; i < str1.length(); i++) {
      int currentCharAsciiCode = str1.charAt(i);
      hash |= (hash * multiplier + currentCharAsciiCode) << bitShift;
    }
    return Math.abs(hash) % filter_len;
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

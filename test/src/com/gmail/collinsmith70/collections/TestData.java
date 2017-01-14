package com.gmail.collinsmith70.collections;

public class TestData {

  static final int[] PRIMES = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};

  static final Integer[] WRAPPED_PRIMES;
  static {
    WRAPPED_PRIMES = new Integer[PRIMES.length];
    for (int i = 0; i < PRIMES.length; i++) {
      WRAPPED_PRIMES[i] = PRIMES[i];
    }
  }

  static final int[] SORTING_PRIMES = {29, 17, 11, 3, 7, 19, 5, 2, 23, 13};

  private TestData() {
    //...
  }

}

package com.gmail.collinsmith70.collections;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.gmail.collinsmith70.collections.TestData.PRIMES;
import static com.gmail.collinsmith70.collections.TestData.SORTING_PRIMES;

public class SortingTest {

  @Before
  public void init() {
    Sorting.debug = true;
  }

  @After
  public void cleanup() {
    Sorting.debug = false;
  }

  @Test
  public void testBubbleSort() {
    int[] ints = Arrays.copyOf(SORTING_PRIMES, SORTING_PRIMES.length);
    Sorting.bubbleSort(ints);
    Assert.assertArrayEquals(PRIMES, ints);
  }

}

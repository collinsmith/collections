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

  @Test
  public void testInsertionSort() {
    int[] ints = Arrays.copyOf(SORTING_PRIMES, SORTING_PRIMES.length);
    Sorting.insertionSort(ints);
    Assert.assertArrayEquals(PRIMES, ints);
  }

  @Test
  public void testMergeSort() {
    int[] ints = Arrays.copyOf(SORTING_PRIMES, SORTING_PRIMES.length);
    Sorting.mergeSort(ints);
    Assert.assertArrayEquals(PRIMES, ints);
  }

  @Test
  public void testInPlaceMergeSort() {
    int[] ints = Arrays.copyOf(SORTING_PRIMES, SORTING_PRIMES.length);
    Sorting.mergeSortInPlace(ints);
    Assert.assertArrayEquals(PRIMES, ints);
  }

  @Test
  public void testSelectionSort() {
    int[] ints = Arrays.copyOf(SORTING_PRIMES, SORTING_PRIMES.length);
    Sorting.selectionSort(ints);
    Assert.assertArrayEquals(PRIMES, ints);
  }

  @Test
  public void testShellSort() {
    int[] ints = Arrays.copyOf(SORTING_PRIMES, SORTING_PRIMES.length);
    Sorting.shellSort(ints);
    Assert.assertArrayEquals(PRIMES, ints);
  }

  @Test
  public void testQuickSort() {
    int[] ints = Arrays.copyOf(SORTING_PRIMES, SORTING_PRIMES.length);
    Sorting.quickSort(ints);
    Assert.assertArrayEquals(PRIMES, ints);
  }

  @Test
  public void testHeapSort() {
    int[] ints = Arrays.copyOf(SORTING_PRIMES, SORTING_PRIMES.length);
    Sorting.heapSort(ints);
    Assert.assertArrayEquals(PRIMES, ints);
  }

  @Test
  public void testLSDRadixSort() {
    // This test uses different input that others because it contains far more
    // even numbers, and is available as an example here:
    // http://www.cs.princeton.edu/courses/archive/spr02/cs226/lectures/radix.4up.pdf
    int[] ints = {
        0b00001,
        0b10011,
        0b01111,
        0b10010,
        0b10100,
        0b01001,
        0b01110,
        0b00111,
        0b00101,
        0b11000,
        0b00001,
        0b01101,
        0b10000,
        0b01100,
        0b00101
    };

    Sorting.radixSortLSD(ints);

    int[] sorted = Arrays.copyOf(ints, ints.length);
    Arrays.sort(sorted);

    Assert.assertArrayEquals(sorted, ints);
  }

  @Test
  public void testMSDRadixSort() {
    // This test uses different input that others because it contains far more
    // even numbers, and is available as an example here:
    // http://www.cs.princeton.edu/courses/archive/spr02/cs226/lectures/radix.4up.pdf
    int[] ints = {
        0b00001,
        0b10011,
        0b01111,
        0b10010,
        0b10100,
        0b01001,
        0b01110,
        0b00111,
        0b00101,
        0b11000,
        0b00001,
        0b01101,
        0b10000,
        0b01100,
        0b00101
    };

    Sorting.radixSortMSD(ints);

    int[] sorted = Arrays.copyOf(ints, ints.length);
    Arrays.sort(sorted);

    Assert.assertArrayEquals(sorted, ints);
  }

}

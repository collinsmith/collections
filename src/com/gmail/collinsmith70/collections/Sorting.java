package com.gmail.collinsmith70.collections;

import java.util.*;
import java.util.ArrayList;

public class Sorting {

  static boolean debug = false;

  private Sorting() {

  }

  /**
   * Best:  O(n)
   * Worst: O(n^2)
   * Avg:   O(n^2)
   *
   * Space: O(n)
   */
  public static void bubbleSort(int[] array) {
    int pass = 1;
    boolean swap;
    do {
      if (debug) {
        System.out.printf("Pass %d: %s%n", pass++, Arrays.toString(array));
      }

      swap = false;
      for (int i = 1; i < array.length; i++) {
        if (array[i-1] > array[i]) {
          swap(array, i-1, i);
          swap = true;
          if (debug) {
            System.out.println(Arrays.toString(array));
          }
        }
      }
    } while (swap);
  }

  /**
   * Best:  O(n)
   * Worst: O(n^2)
   * Avg:   O(n^2)
   *
   * Space: O(n)
   */
  public static void insertionSort(int[] array) {
    for (int i = 1; i < array.length; i++) {
      if (debug) {
        System.out.printf("Pass %d: %s at %d%n", i, Arrays.toString(array), array[i]);
      }

      for (int j = i - 1; j >= 0 && array[j] > array[j + 1]; j--) {
        swap(array, j, j + 1);
        if (debug) {
          System.out.println(Arrays.toString(array));
        }
      }
    }
  }

  /**
   * Best:  O(n^2)
   * Worst: O(n^2)
   * Avg:   O(n^2)
   *
   * Space: O(1)
   */
  public static void selectionSort(int[] array) {
    if (debug) {
      System.out.println(Arrays.toString(array));
    }

    int min, minId;
    for (int i = 0; i < array.length; i++) {
      minId = Integer.MIN_VALUE;
      min = Integer.MAX_VALUE;
      for (int j = i; j < array.length; j++) {
        if (array[j] < min) {
          min = array[j];
          minId = j;
          if (debug) {
            System.out.printf("min=%d%n", min);
          }
        }
      }

      if (i != minId) {
        swap(array, i, minId);
        if (debug) {
          System.out.printf("swapping %d with %d%n", i, minId);
          System.out.println(Arrays.toString(array));
        }
      }
    }
  }

  /**
   * Best:  O(n*logn)
   * Worst: O(n*logn)
   * Avg:   O(n*logn)
   *
   * Space: O(n)
   */
  public static void mergeSort(int[] array) {
    int[] sorted = mergeSort(array, array);
    System.arraycopy(sorted, 0, array, 0, array.length);
  }

  private static int[] mergeSort(int[] array, int[] fork) {
    switch (fork.length) {
      case 0:
      case 1:
        return fork;
      case 2:
        if (fork[1] < fork[0]) {
          final int[] before = Arrays.copyOf(fork, fork.length);
          swap(fork, 0, 1);
          if (debug) {
            System.out.println(Arrays.toString(before) + " -> " + Arrays.toString(fork));
          }
        }

        return fork;
      default:
        final int middle = fork.length >>> 1;
        int[] left = Arrays.copyOfRange(fork, 0, middle);
        int[] right = Arrays.copyOfRange(fork, middle, fork.length);
        if (debug) {
          System.out.printf("fork %s as %s and %s%n",
              Arrays.toString(fork),
              Arrays.toString(left),
              Arrays.toString(right));
        }

        left = mergeSort(array, left);
        right = mergeSort(array, right);
        ArrayList<Integer> joined = new ArrayList(fork.length);
        if (debug) {
          System.out.printf("join %s %s %s%n",
              joined,
              Arrays.toString(left),
              Arrays.toString(right));
        }

        int s = 0, l = 0, r = 0;
        while (l < left.length && r < right.length) {
          fork[s++] = left[l] <= right[r] ? left[l++] : right[r++];
          if (debug) {
            joined.add(fork[s-1]);
            System.out.printf("%s %s %s%n",
                joined,
                Arrays.toString(Arrays.copyOfRange(left, Math.min(l, left.length), left.length)),
                Arrays.toString(Arrays.copyOfRange(right, Math.min(r, right.length), right.length)));
          }
        }

        while (l < left.length) {
          fork[s++] = left[l++];
          if (debug) {
            joined.add(fork[s - 1]);
            System.out.printf("%s %s %s%n",
                joined,
                Arrays.toString(Arrays.copyOfRange(left, Math.min(l, left.length), left.length)),
                Arrays.toString(Arrays.copyOfRange(right, Math.min(r, right.length), right.length)));
          }
        }

        while (r < right.length) {
          fork[s++] = right[r++];
          if (debug) {
            joined.add(fork[s - 1]);
            System.out.printf("%s %s %s%n",
                joined,
                Arrays.toString(Arrays.copyOfRange(left, Math.min(l, left.length), left.length)),
                Arrays.toString(Arrays.copyOfRange(right, Math.min(r, right.length), right.length)));
          }
        }

        return fork;
    }
  }

  /**
   * Best:  O(n*logn)
   * Worst: O(n*logn)
   * Avg:   O(n*logn)
   *
   * Space: O(1)
   */
  public static void mergeSortInPlace(int[] array) {
    mergeSortInPlace(array, 0, array.length);
  }

  private static void mergeSortInPlace(int[] array, final int i, final int j) {
    final int length = j - i;
    switch (length) {
      case 0:
      case 1:
        return;
      case 2:
        if (array[i + 1] < array[i]) {
          final int[] before = Arrays.copyOfRange(array, i, j);
          swap(array, i, i + 1);
          if (debug) {
            System.out.println(
                Arrays.toString(before) + " -> " + Arrays.toString(Arrays.copyOfRange(array, i, j)));
          }
        }

        return;
      default:
        final int middle = ((j - i) >>> 1) + i;
        if (debug) {
          System.out.printf("fork %s as %s and %s%n",
              Arrays.toString(Arrays.copyOfRange(array, i, j)),
              Arrays.toString(Arrays.copyOfRange(array, i, middle)),
              Arrays.toString(Arrays.copyOfRange(array, middle, j)));
        }

        mergeSortInPlace(array, i, middle);
        mergeSortInPlace(array, middle, j);
        ArrayList<Integer> joined = new ArrayList(length);
        if (debug) {
          System.out.printf("join %s %s %s%n",
              joined,
              Arrays.toString(Arrays.copyOfRange(array, i, middle)),
              Arrays.toString(Arrays.copyOfRange(array, middle, j)));
        }

        int s = i;
        int l = i, l_length = middle - l, r = middle;
        while (l < r && r < j) {
          if (array[l] <= array[r]) {
            array[s++] = array[l++];
            l_length--;
          } else {
            int temp = array[r++];
            System.arraycopy(array, l, array, l + 1, l_length);
            l++;
            array[s++] = temp;
          }

          if (debug) {
            joined.add(array[s - 1]);
            System.out.printf("%s %s %s%n",
                joined,
                l < l+l_length ? Arrays.toString(Arrays.copyOfRange(array, l, l + l_length)) : "[]",
                Arrays.toString(Arrays.copyOfRange(array, Math.min(r, j), j)));
          }
        }

        while (l < r) {
          array[s++] = array[l++];
          l_length--;

          if (debug) {
            joined.add(array[s - 1]);
            System.out.printf("%s %s %s%n",
                joined,
                l < l + l_length ? Arrays.toString(Arrays.copyOfRange(array, l, l + l_length)) : "[]",
                Arrays.toString(Arrays.copyOfRange(array, Math.min(r, j), j)));
          }
        }

        while (r < j) {
          array[s++] = array[r++];

          if (debug) {
            joined.add(array[s - 1]);
            System.out.printf("%s %s %s%n",
                joined,
                l < l + l_length ? Arrays.toString(Arrays.copyOfRange(array, l, l + l_length)) :
                    "[]",
                Arrays.toString(Arrays.copyOfRange(array, Math.min(r, j), j)));
          }
        }
    }
  }

  /**
   * Best:  O(n*logn)
   * Worst: O(n*log^2(n))
   * Avg:   O(n*log^2(n))
   *
   * Space: O(1)
   */
  public static void shellSort(int[] array) {
    if (debug) {
      System.out.println(Arrays.toString(array));
    }

    final int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};

    boolean swap;
    for (int gap : gaps) {
      if (debug) {
        System.out.println("gap=" + gap);
      }

      for (int j, i = gap; i < array.length; i++) {
        swap = false;
        int temp = array[i];
        for (j = i; gap <= j && temp < array[j - gap]; j -= gap) {
          if (debug) {
            System.out.printf("swapping %d with %d%n", array[j - gap], array[j]);
            swap = true;
          }

          array[j] = array[j - gap];
        }

        array[j] = temp;
        if (debug && swap) {
          System.out.println(Arrays.toString(array));
        }
      }
    }
  }

  /**
   * w = word size (highest bit in largest sequence)
   * N = number of keys (array.length)
   * Best:  O(wN)
   * Worst: O(wN)
   * Avg:   O(wN)
   *
   * Space: O(w + N) -> O(1) in this implementation
   */
  public static void radixSortLSD(int[] array) {
    radixSortLSD(array, 1, -1);
    if (debug) {
      System.out.println(Arrays.toString(array));
    }
  }

  private static void radixSortLSD(int[] array, int radix, int highestOneBit) {
    if (debug && highestOneBit < 0) {
      System.out.println(Arrays.toString(array));
      System.out.println(toBinaryString(array));
    }

    int ones = 0;
    int max = Integer.MIN_VALUE;
    for (int i = 0; i < array.length; i++) {
      if (max < array[i]) {
        max = array[i];
      }

      if ((array[i] & radix) == 0) {
        int temp = array[i];
        System.arraycopy(array, ones, array, ones + 1, i - ones);
        array[ones++] = temp;
      }
    }

    if (highestOneBit < 0) {
      highestOneBit = Integer.highestOneBit(max);
    }

    if (debug) {
      System.out.println("radix=" + radix);
      System.out.println(toBinaryString(array));
    }

    if (radix <= highestOneBit) {
      radixSortLSD(array, radix << 1, highestOneBit);
    }
  }

  /**
   * w = word size (highest bit in largest value), 31 in this case (no optimizations for finding
   *         this value first and then looping down from it).
   * N = number of keys (array.length)
   * Best:  O(wN)
   * Worst: O(wN)
   * Avg:   O(wN)
   *
   * Space: O(w + N) -> O(1) in this implementation
   */
  public static void radixSortMSD(int[] array) {
    radixSortMSD(array, 1 << 30, 0, array.length);
    if (debug) {
      System.out.println(Arrays.toString(array));
      System.out.println(toBinaryString(array));
    }
  }

  private static void radixSortMSD(int[] array, final int radix, final int from, final int to) {
    int ones = to;
    for (int i = to - 1; from <= i; i--) {
      if ((array[i] & radix) == radix) {
        int temp = array[i];
        System.arraycopy(array, i + 1, array, i, ones - i - 1);
        array[--ones] = temp;
      }
    }

    if (debug && ones < to) {
      System.out.println("radix=" + radix);
      System.out.println(toBinaryString(array, from, to));
    }

    if (1 < radix) {
      if (ones == to) {
        radixSortMSD(array, radix >>> 1, from, to);
      } else {
        if (debug) {
          System.out.printf("fork %s as %s and %s%n",
              toLetterString(Arrays.copyOfRange(array, from, to)),
              toLetterString(Arrays.copyOfRange(array, from, ones)),
              toLetterString(Arrays.copyOfRange(array, ones, to)));
        }

        radixSortMSD(array, radix >>> 1, from, ones);
        radixSortMSD(array, radix >>> 1, ones, to);
      }
    }
  }

  private static String toLetterString(int[] array) {
    StringBuilder sb = new StringBuilder(32);
    sb.append('[');
    for (int i : array) {
      sb.append(String.format("%c, ", 'A' + (i - 1)));
    }

    if (array.length > 0)  {
      sb.delete(sb.length()-2, sb.length());
    }
    sb.append(']');
    return sb.toString();
  }

  private static String toBinaryString(int[] array) {
    return toBinaryString(array, 0, array.length);
  }

  private static String toBinaryString(int[] array, int from, int to) {
    StringBuilder sb = new StringBuilder(32);
    int maxBit = Integer.MIN_VALUE;
    for (int i : array) {
      if (i >= maxBit) {
        maxBit = i;
      }
    }

    maxBit = valueToBit(maxBit);
    String format = String.format("%%%ds %%c", maxBit + 1);
    for (int i = from; i < to; i++) {
      sb.append(String.format(format, Integer.toBinaryString(array[i]), 'A' + (array[i] - 1)));
      sb.append("\n");
    }

    if ((to - from) > 0) {
      sb.deleteCharAt(sb.length()-1);
    }

    return sb.toString();
  }

  private static int valueToBit(int i) {
    int j = 0;
    final int highestBit = 30;
    final int highestOneBit = 1 << highestBit;
    while (true) {
      if ((i & (highestOneBit >> j)) > 0) {
        return highestBit - j;
      }

      j++;
    }
  }

  public static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

}

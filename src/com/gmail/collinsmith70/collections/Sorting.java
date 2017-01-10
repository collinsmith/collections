package com.gmail.collinsmith70.collections;

import java.util.*;
import java.util.ArrayList;

public class Sorting {

  static boolean debug = false;

  private Sorting() {

  }

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

  public static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

}

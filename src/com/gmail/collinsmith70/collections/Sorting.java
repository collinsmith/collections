package com.gmail.collinsmith70.collections;

import java.util.Arrays;

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
    int[] sorted = mergeSort(array, 0, array.length);
    System.arraycopy(sorted, 0, array, 0, array.length);
  }

  public static int[] mergeSort(int[] array, int i, int j) {
    if (debug) {
      System.out.printf("split %s from %d to %d%n", Arrays.toString(array), i, j);
    }

    int[] sorter;
    switch (j - i) {
      case 0:
      case 1:
        return Arrays.copyOfRange(array, i, j);
      case 2:
        sorter = Arrays.copyOfRange(array, i, j);
        if (sorter[1] < sorter[0]) {
          swap(sorter, 0, 1);
        }

        return sorter;
      default:
        final int middle = ((j - i) >>> 1) + i;
        int[] left = mergeSort(array, i, middle);
        int[] right = mergeSort(array, middle, j);
        sorter = new int[j - i];
        if (debug) {
          System.out.printf("Sorting: %s %d, %d%n",
              Arrays.toString(Arrays.copyOfRange(array, i, j)), i, j);
          System.out.printf("%s %s %s%n",
              Arrays.toString(sorter),
              Arrays.toString(left),
              Arrays.toString(right));
        }

        int s = 0, l = 0, r = 0;
        while (l < left.length && r < right.length) {
          sorter[s++] = left[l] <= right[r] ? left[l++] : right[r++];
          System.out.printf("%s %s %s%n",
              Arrays.toString(sorter),
              Arrays.toString(Arrays.copyOfRange(left, Math.min(l, left.length), left.length)),
              Arrays.toString(Arrays.copyOfRange(right, Math.min(r, right.length), right.length)));
        }

        while (l < left.length) {
          sorter[s++] = left[l++];
          System.out.printf("%s %s %s%n",
              Arrays.toString(sorter),
              Arrays.toString(Arrays.copyOfRange(left, Math.min(l, left.length), left.length)),
              Arrays.toString(Arrays.copyOfRange(right, Math.min(r, right.length), right.length)));
        }

        while (r < right.length) {
          sorter[s++] = right[r++];
          System.out.printf("%s %s %s%n",
              Arrays.toString(sorter),
              Arrays.toString(Arrays.copyOfRange(left, Math.min(l, left.length), left.length)),
              Arrays.toString(Arrays.copyOfRange(right, Math.min(r, right.length), right.length)));
        }

        return sorter;
    }
  }

  public static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

}

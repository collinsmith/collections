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

  public static void swap(int[] array, int i, int j) {
    int temp = array[i];
    array[i] = array[j];
    array[j] = temp;
  }

}

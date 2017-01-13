package com.gmail.collinsmith70.collections;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayHeap {

  public static void heapSort(int[] elements) {
    ArrayHeap heap = heapify(elements);
    for (int i = 0; i < elements.length; i++) {
      elements[i] = heap.removeLowest();
    }
  }

  public static ArrayHeap heapify(int[] elements) {
    return new ArrayHeap(elements).heapify();
  }

  int[] elements;
  int size;

  public ArrayHeap() {
    this(16);
  }

  public ArrayHeap(int initialCapacity) {
    this.elements = new int[initialCapacity];
  }

  private ArrayHeap(int[] elements) {
    this.elements = Arrays.copyOf(elements, elements.length);
    this.size = elements.length;
  }

  private ArrayHeap heapify() {
    for (int i = (size() >>> 1) - 1; 0 <= i; i--) {
      siftDown(i);
    }

    return this;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  private void siftUp(int index) {
    int child = elements[index];
    int parent;
    while (0 < index) {
      int parentIndex = Node.getParent(index);
      parent = elements[parentIndex];
      if (child >= parent) {
        break;
      }

      elements[index] = parent;
      index = parentIndex;
    }

    elements[index] = child;
  }

  private void siftDown(int index) {
    int root = elements[index];
    while (index < size()) {
      int minIndex;
      int min;

      int leftIndex = Node.getLeft(index);
      int rightIndex = Node.getRight(index);
      if (!isValidId(leftIndex) && !isValidId(rightIndex)) {
        break;
      } else if (isValidId(leftIndex) && isValidId(rightIndex)) {
        if (elements[leftIndex] < elements[rightIndex]) {
          minIndex = leftIndex;
          min = elements[leftIndex];
        } else {
          minIndex = rightIndex;
          min = elements[rightIndex];
        }
      } else if (isValidId(leftIndex)) {
        minIndex = leftIndex;
        min = elements[leftIndex];
      } else { // if (isValidId(rightIndex))
        minIndex = rightIndex;
        min = elements[rightIndex];
      }

      if (root <= min) {
        break;
      }

      elements[index] = min;
      index = minIndex;
    }

    elements[index] = root;
  }

  private boolean isValidId(int index) {
    return 0 <= index && index < size();
  }

  public void add(int element) {
    elements[size++] = element;
    siftUp(size() - 1);
  }

  public int removeLowest() {
    switch (size()) {
      case 0:
        throw new NoSuchElementException();
      default:
        int element = elements[0];
        elements[0] = elements[--size];
        siftDown(0);
        return element;
    }
  }

  private static class Node {

    public static int getParent(int index) {
      return (index - 1) >>> 1;
    }

    public static int getLeft(int index) {
      return (index << 1) + 1;
    }

    public static int getRight(int index) {
      return (index << 1) + 2;
    }

  }

}

package com.gmail.collinsmith70.collections;

import java.util.Arrays;

public class ArrayHeap<E extends Comparable<? super E>> {

  public static <E extends Comparable<? super E>> ArrayHeap<E> heapify(E[] elements) {
    ArrayHeap<E> heap = new ArrayHeap<>(elements);
    heap.heapify();
    return heap;
  }

  private E[] elements;
  private int size;

  public ArrayHeap() {
    this(16);
  }

  public ArrayHeap(int initialCapacity) {
    this.elements = (E[])new Object[initialCapacity];
  }

  private ArrayHeap(E[] elements) {
    this.elements = Arrays.copyOf(elements, elements.length);
    this.size = elements.length;
  }

  private void heapify() {
    for (int i = (elements.length >>> 1) - 1; 0 <= i; i--) {
      siftDown(i);
    }
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean checkAndGrow(int by) {
    if (size() - 1 + by < elements.length) {
      return false;
    }

    elements = Arrays.copyOf(elements, elements.length << 1);
    return true;
  }

  private void siftUp(int i) {
    E child = elements[i];
    E parent;
    while (0 < i) {
      int j = Node.getParent(i);
      parent = elements[j];
      if (child.compareTo(parent) >= 0) {
        break;
      }

      elements[i] = parent;
      i = j;
    }

    elements[i] = child;
  }

  private void siftDown(int i) {
    E root = elements[i];
    while (i < size()) {
      int minId;
      E min;

      int leftId = Node.getLeft(i);
      E left = isValidId(leftId) ? elements[leftId] : null;

      int rightId = Node.getRight(i);
      E right = isValidId(rightId) ? elements[rightId] : null;
      if (left == null && right == null) {
        break;
      } else if (left != null && right != null) {
        if (left.compareTo(right) < 0) {
          minId = leftId;
          min = left;
        } else {
          minId = rightId;
          min = right;
        }
      } else if (left != null) {
        minId = leftId;
        min = left;
      } else { // if (right != null)
        minId = rightId;
        min = right;
      }

      if (root.compareTo(min) <= 0) {
        break;
      }

      elements[i] = min;
      i = minId;
    }

    elements[i] = root;
  }

  private boolean isValidId(int i) {
    return 0 <= i && i < size();
  }

  public void add(E element) {
    checkAndGrow(1);
    elements[size++] = element;
    siftUp(size() - 1);
  }

  public E removeLowest() {
    if (isEmpty()) {
      return null;
    }

    size--;
    E element = elements[0];
    elements[0] = elements[size()];
    elements[size()] = null;
    siftDown(0);
    return element;
  }

  private static class Node {

    public static int getParent(int n) {
      return (n - 1) >>> 1;
    }

    public static int getLeft(int n) {
      return (n << 1) + 1;
    }

    public static int getRight(int n) {
      return (n << 1) + 2;
    }

  }

}

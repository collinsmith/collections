package com.gmail.collinsmith70.collections;

public class ArrayHeap<E extends Comparable<? super E>> {

  public static <E extends Comparable<? super E>> ArrayHeap<E> heapify(E[] elements) {
    ArrayHeap<E> heap = new ArrayHeap<>(elements);
    heap.heapify();
    return heap;
  }

  private ArrayList<E> elements;

  public ArrayHeap() {
    this(16);
  }

  public ArrayHeap(int initialCapacity) {
    this.elements = new ArrayList<>(initialCapacity);
  }

  private ArrayHeap(E[] elements) {
    this.elements = new ArrayList<>(elements);
  }

  private void heapify() {
    for (int i = (size() >>> 1) - 1; 0 <= i; i--) {
      siftDown(i);
    }
  }

  public int size() {
    return elements.size();
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  private void siftUp(int i) {
    E child = elements.get(i);
    E parent;
    while (0 < i) {
      int j = Node.getParent(i);
      parent = elements.get(j);
      if (child.compareTo(parent) >= 0) {
        break;
      }

      elements.set(i, parent);
      i = j;
    }

    elements.set(i, child);
  }

  private void siftDown(int i) {
    E root = elements.get(i);
    while (i < size()) {
      int minId;
      E min;

      int leftId = Node.getLeft(i);
      E left = isValidId(leftId) ? elements.get(leftId) : null;

      int rightId = Node.getRight(i);
      E right = isValidId(rightId) ? elements.get(rightId) : null;
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

      elements.set(i, min);
      i = minId;
    }

    elements.set(i, root);
  }

  private boolean isValidId(int i) {
    return 0 <= i && i < size();
  }

  public void add(E element) {
    elements.addLast(element);
    siftUp(size() - 1);
  }

  public E removeLowest() {
    switch (size()) {
      case 0:
        return null;
      case 1:
        return elements.removeLast();
      default:
        E element = elements.get(0);
        elements.set(0, elements.removeLast());
        siftDown(0);
        return element;
    }
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

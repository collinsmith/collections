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

  private void siftUp(int index) {
    E child = elements.get(index);
    E parent;
    while (0 < index) {
      int parentIndex = Node.getParent(index);
      parent = elements.get(parentIndex);
      if (child.compareTo(parent) >= 0) {
        break;
      }

      elements.set(index, parent);
      index = parentIndex;
    }

    elements.set(index, child);
  }

  private void siftDown(int index) {
    E root = elements.get(index);
    while (index < size()) {
      int minIndex;
      E min;

      int leftIndex = Node.getLeft(index);
      E left = isValidId(leftIndex) ? elements.get(leftIndex) : null;

      int rightIndex = Node.getRight(index);
      E right = isValidId(rightIndex) ? elements.get(rightIndex) : null;
      if (left == null && right == null) {
        break;
      } else if (left != null && right != null) {
        if (left.compareTo(right) < 0) {
          minIndex = leftIndex;
          min = left;
        } else {
          minIndex = rightIndex;
          min = right;
        }
      } else if (left != null) {
        minIndex = leftIndex;
        min = left;
      } else { // if (right != null)
        minIndex = rightIndex;
        min = right;
      }

      if (root.compareTo(min) <= 0) {
        break;
      }

      elements.set(index, min);
      index = minIndex;
    }

    elements.set(index, root);
  }

  private boolean isValidId(int index) {
    return 0 <= index && index < size();
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

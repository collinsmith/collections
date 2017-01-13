package com.gmail.collinsmith70.collections;

public class MaxPriorityQueue<E extends Comparable<? super E>> {

  public static <E extends Comparable<? super E>> void heapSort(E[] elements) {
    MaxPriorityQueue<E> heap = heapify(elements);
    for (int i = elements.length-1; 0 <= i; i--) {
      elements[i] = heap.removeHighest();
    }
  }

  public static <E extends Comparable<? super E>> MaxPriorityQueue<E> heapify(E[] elements) {
    return new MaxPriorityQueue<>(elements).heapify();
  }

  ArrayList<E> elements;

  public MaxPriorityQueue() {
    this(16);
  }

  public MaxPriorityQueue(int initialCapacity) {
    this.elements = new ArrayList<>(initialCapacity);
  }

  private MaxPriorityQueue(E[] elements) {
    this.elements = new ArrayList<>(elements);
  }

  private MaxPriorityQueue<E> heapify() {
    for (int i = (size() >>> 1) - 1; 0 <= i; i--) {
      siftDown(i);
    }

    return this;
  }

  public int size() {
    return elements.size();
  }

  public boolean isEmpty() {
    return elements.isEmpty();
  }

  private void siftUp(int index) {
    E child = elements.get(index);
    E parent;
    while (0 < index) {
      int parentIndex = Node.getParent(index);
      parent = elements.get(parentIndex);
      if (child.compareTo(parent) <= 0) {
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
      int maxIndex;
      E max;

      int leftIndex = Node.getLeft(index);
      E left = isValidId(leftIndex) ? elements.get(leftIndex) : null;

      int rightIndex = Node.getRight(index);
      E right = isValidId(rightIndex) ? elements.get(rightIndex) : null;
      if (left == null && right == null) {
        break;
      } else if (left != null && right != null) {
        if (left.compareTo(right) > 0) {
          maxIndex = leftIndex;
          max = left;
        } else {
          maxIndex = rightIndex;
          max = right;
        }
      } else if (left != null) {
        maxIndex = leftIndex;
        max = left;
      } else { // if (right != null)
        maxIndex = rightIndex;
        max = right;
      }

      if (root.compareTo(max) >= 0) {
        break;
      }

      elements.set(index, max);
      index = maxIndex;
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

  public E removeHighest() {
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

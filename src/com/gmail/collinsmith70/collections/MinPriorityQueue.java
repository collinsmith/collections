package com.gmail.collinsmith70.collections;

import java.util.Arrays;

public class MinPriorityQueue<E extends Comparable<? super E>> {

  static boolean debug = false;

  private static final int ROOT = 0;

  public static <E extends Comparable<? super E>> void heapSort(E[] elements) {
    if (debug) {
      System.out.println(Arrays.toString(elements));
    }

    MinPriorityQueue<E> heap = heapify(elements);

    for (int i = 0; i < elements.length; i++) {
      elements[i] = heap.removeLowest();
      if (debug) {
        System.out.printf("%s %s%n",
            Arrays.toString(Arrays.copyOfRange(elements, 0, i + 1)),
            heap.elements);
      }
    }
  }

  public static <E extends Comparable<? super E>> MinPriorityQueue<E> heapify(E[] elements) {
    MinPriorityQueue<E> queue = new MinPriorityQueue<>(elements).heapify();
    if (debug) {
      System.out.println("heapify: " + queue.elements);
    }

    return queue;
  }

  ArrayList<E> elements;

  public MinPriorityQueue() {
    this(16);
  }

  public MinPriorityQueue(int initialCapacity) {
    this.elements = new ArrayList<>(initialCapacity);
  }

  private MinPriorityQueue(E[] elements) {
    this.elements = new ArrayList<>(elements);
  }

  private MinPriorityQueue<E> heapify() {
    for (int i = (size() >>> 1) - 1; ROOT <= i; i--) {
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
    while (ROOT < index) {
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
        E element = elements.get(ROOT);
        elements.set(ROOT, elements.removeLast());
        siftDown(ROOT);
        return element;
    }
  }

  public void update(int index) {
    if (!isValidId(index)) {
      return;
    }

    if (debug) {
      System.out.printf("update [%d](%s) in %s%n", index, elements.get(index), elements);
    }

    if (index == ROOT) {
      siftDown(index);
    } else {
      siftUp(index);
    }

    if (debug) {
      System.out.println(elements);
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

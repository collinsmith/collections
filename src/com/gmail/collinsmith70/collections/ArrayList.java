package com.gmail.collinsmith70.collections;

import java.util.Arrays;

public class ArrayList<E> {

  E[] elements;
  int size;

  public ArrayList() {
    this(16);
  }

  public ArrayList(int initialCapacity) {
    elements = (E[])new Object[initialCapacity];
  }

  ArrayList(E[] elements) {
    this.elements = Arrays.copyOf(elements, elements.length);
    this.size = elements.length;
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public E get(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    return elements[index];
  }

  public void set(int index, E element) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    elements[index] = element;
  }

  public boolean checkAndGrow(int by) {
    if (size() - 1 + by < elements.length) {
      return false;
    }

    int newSize = elements.length << 1;
    elements = Arrays.copyOf(elements, elements.length << 1);
    return true;
  }

  public void addFirst(E element) {
    checkAndGrow(1);
    System.arraycopy(elements, 0, elements, 1, size++);
    elements[0] = element;
  }

  public void addLast(E element) {
    checkAndGrow(1);
    elements[size++] = element;
  }

  public E removeFirst() {
    E element = elements[0];
    System.arraycopy(elements, 1, elements, 0, size--);
    return element;
  }

  public E removeLast() {
    E element = elements[--size];
    return element;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(":{");
    for (int i = 0; i < size(); i++) {
      sb.append(elements[i]);
      sb.append(',');
    }

    if (!isEmpty()) {
      sb.deleteCharAt(sb.length() - 1);
    }

    sb.append("}(");
    sb.append(elements.length);
    sb.append(")");
    return sb.toString();
  }

}

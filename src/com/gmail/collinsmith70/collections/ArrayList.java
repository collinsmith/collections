package com.gmail.collinsmith70.collections;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayList<E> implements List<E> {

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

  @Override
  public void clear() {
    size = 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public E get(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    return elements[index];
  }

  @Override
  public void set(int index, E element) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    elements[index] = element;
  }

  @Override
  public boolean contains(Object obj) {
    throw new UnsupportedOperationException();
  }

  private boolean checkAndGrow(int by) {
    if (size() - 1 + by < elements.length) {
      return false;
    }

    int newSize = elements.length << 1;
    elements = Arrays.copyOf(elements, elements.length << 1);
    return true;
  }

  @Override
  public void addFirst(E element) {
    checkAndGrow(1);
    System.arraycopy(elements, 0, elements, 1, size++);
    elements[0] = element;
  }

  @Override
  public void addLast(E element) {
    checkAndGrow(1);
    elements[size++] = element;
  }

  @Override
  public void add(int index, E element) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException();
    }

    checkAndGrow(1);
    System.arraycopy(elements, index, elements, index + 1, size() - index);
    elements[index] = element;
    size++;
  }

  @Override
  public E removeFirst() {
    if (isEmpty()) {
      return null;
    }

    E element = elements[0];
    System.arraycopy(elements, 1, elements, 0, size--);
    return element;
  }

  @Override
  public E removeLast() {
    if (isEmpty()) {
      return null;
    }

    E element = elements[--size];
    return element;
  }

  @Override
  public E remove(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    size--;
    E element = elements[index];
    System.arraycopy(elements, index + 1, elements, index, size() - index);
    return element;
  }

  @Override
  public boolean remove(Object obj) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(":{");
    for (int i = 0; i < size(); i++) {
      sb.append(elements[i]);
      sb.append(", ");
    }

    if (!isEmpty()) {
      sb.delete(sb.length()-2, sb.length());
    }

    sb.append("}(");
    sb.append(elements.length);
    sb.append(")");
    return sb.toString();
  }

  @Override
  public <T> T[] toArray(T[] array) {
    int size = size();
    if (array.length < size) {
      array = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
    }

    System.arraycopy(elements, 0, array, 0, size);
    if (array.length > size) {
      array[size] = null;
    }

    return array;
  }

}
package com.gmail.collinsmith70.collections;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayList<E> implements List<E> {

  Object[] elements;
  int size;

  public ArrayList() {
    this(16);
  }

  public ArrayList(int initialCapacity) {
    elements = new Object[initialCapacity];
  }

  public ArrayList(Iterable<? extends E> elements) {
    this();
    addAll(elements);
  }

  ArrayList(E[] elements) {
    this.elements = Arrays.copyOf(elements, elements.length);
    this.size = elements.length;
  }

  @Override
  public Iterator<E> iterator() {
    return new ArrayListIterator();
  }

  class ArrayListIterator implements Iterator<E> {

    E lastReturned;
    int nextIndex;

    @Override
    public boolean hasNext() {
      return nextIndex < size();
    }

    @Override
    @SuppressWarnings("unchecked")
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      lastReturned = (E) elements[nextIndex++];
      return lastReturned;
    }

    @Override
    public void remove() {
      if (lastReturned == null) {
        throw new IllegalStateException();
      }

      lastReturned = null;
      ArrayList.this.remove(--nextIndex);
    }

    @Override
    public String toString() {
      return String.format("%s:{lastReturned:%h, nextIndex:%d, elements:%s}",
          getClass().getSimpleName(), lastReturned, nextIndex, getElementsString(nextIndex));
    }

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
  @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    return (E) elements[index];
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
    for (int i = 0; i < size(); i++) {
      if (Objects.equals(elements[i], obj)) {
        return true;
      }
    }

    return false;
  }

  boolean checkAndGrow(int by) {
    if (size() - 1 + by < elements.length) {
      return false;
    }

    int newSize = Math.max(elements.length << 1, 1);
    elements = Arrays.copyOf(elements, newSize);
    return checkAndGrow(by);
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
  @SuppressWarnings("unchecked")
  public E removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }

    E element = (E) elements[0];
    System.arraycopy(elements, 1, elements, 0, size--);
    return element;
  }

  @Override
  @SuppressWarnings("unchecked")
  public E removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }

    return (E) elements[--size];
  }

  @Override
  @SuppressWarnings("unchecked")
  public E remove(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    size--;
    E element = (E) elements[index];
    elements[index] = null;
    System.arraycopy(elements, index + 1, elements, index, size() - index);
    return element;
  }

  @Override
  public boolean remove(Object obj) {
    for (int i = 0; i < size(); i++) {
      if (Objects.equals(elements[i], obj)) {
        remove(i);
        return true;
      }
    }

    return false;
  }

  private String getElementsString() {
    return getElementsString(0);
  }

  private String getElementsString(int index) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = index; i < size(); i++) {
      sb.append(elements[i]);
      sb.append(", ");
    }

    if (sb.length() > 1) {
      sb.delete(sb.length() - 2, sb.length());
    }

    sb.append("]");
    return sb.toString();
  }

  @Override
  public String toString() {
    return getElementsString();
  }

  @Override
  @SuppressWarnings({"unchecked", "cast"})
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

  String toStateString() {
    return String.format("%s:{size:%d, capacity:%d, elements:%s}",
        getClass().getSimpleName(), size, elements.length, getElementsString());
  }

}

package com.gmail.collinsmith70.collections;

import java.lang.reflect.Array;
import java.util.Objects;

public class DoublyLinkedList<E> implements List<E> {

  Node<E> first;
  Node<E> last;
  int size;

  public DoublyLinkedList() {
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void clear() {
    first = last = null;
    size = 0;
  }

  @Override
  public boolean contains(Object obj) {
    for (Node<E> n = first; n != null; n = n.next) {
      if (Objects.equals(n.element, obj)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public E get(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> n;
    if (index < size()>>>1) {
      n = first;
      for (int i = 0; i < index; i++) {
        n = n.next;
      }
    } else {
      index = (size() - 1) - index;
      n = last;
      for (int i = 0; i < index; i++) {
        n = n.prev;
      }
    }

    return n.element;
  }

  @Override
  public void set(int index, E element) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> n = first;
    for (int i = 0; i < index; i++) {
      n = n.next;
    }

    n.element = element;
  }

  @Override
  public void add(int index, E element) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> n = first, prev = null;
    for (int i = 0; i < index; i++) {
      prev = n;
      n = n.next;
    }

    Node<E> newNode = new Node<>(prev, element, n);
    if (prev == null) {
      first = newNode;
    } else {
      prev.next = newNode;
    }

    if (n != null) {
      n.prev = newNode;
    } else {
      last = newNode;
    }

    size++;
  }

  @Override
  public E remove(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> n = first, prev = null;
    for (int i = 0; i < index; i++) {
      prev = n;
      n = n.next;
    }

    E element = n.element;
    if (prev == null) {
      first = n.next;
    } else {
      prev.next = n.next;
    }

    if (n.next != null) {
      n.next.prev = prev;
    } else {
      last = prev;
    }

    size--;
    return element;
  }

  @Override
  public boolean remove(Object obj) {
    int i = 0;
    for (Node<E> n = first; n != null; n = n.next, i++) {
      if (Objects.equals(n.element, obj)) {
        remove(i);
        return true;
      }
    }

    return false;
  }

  Node<E> getNode(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> n = first;
    for (int i = 0; i < index; i++) {
      n = n.next;
    }

    return n;
  }

  private String getElementsString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (Node<E> n = first; n != null; n = n.next) {
      sb.append(n.element);
      sb.append(", ");
    }

    if (!isEmpty()) {
      sb.delete(sb.length() - 2, sb.length());
    }

    sb.append("]");
    return sb.toString();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(":");
    sb.append(getElementsString());
    return sb.toString();
  }

  @Override
  public <T> T[] toArray(T[] array) {
    int size = size();
    if (array.length < size) {
      array = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
    }

    int i = 0;
    for (Node<E> n = first; n != null; n = n.next) {
      array[i++] = (T) n.element;
    }

    if (array.length > size) {
      array[size] = null;
    }

    return array;
  }

  String toStateString() {
    return String.format("%s:{first=%s, last=%s, size=%d, elements=%s}",
        getClass().getSimpleName(), first, last, size, getElementsString());
  }

  static class Node<E> {
    E element;
    Node<E> next;
    Node<E> prev;

    Node() {
      this(null, null, null);
    }

    Node(Node<E> prev, E element, Node<E> next) {
      this.element = element;
      this.next = next;
      this.prev = prev;
    }

    @Override
    public String toString() {
      return String.format("%s:{prev=%h, element=%s, next=%h}",
          getClass().getSimpleName(), prev, element, next);
    }
  }

}

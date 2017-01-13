package com.gmail.collinsmith70.collections;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {

  Node<E> first;
  Node<E> last;
  int size;

  public SinglyLinkedList() {
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  public boolean contains(Object obj) {
    for (Node<E> n = first; n != null; n = n.next) {
      if (Objects.equals(n.element, obj)) {
        return true;
      }
    }

    return false;
  }

  public E get(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> n = first;
    for (int i = 0; i < index; i++) {
      n = n.next;
    }

    return n.element;
  }

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

  public void addFirst(E element) {
    if (first == null) {
      first = last = new Node<>(element, null);
      size++;
      return;
    }

    Node<E> n = new Node<>(element, first);
    first = n;
    size++;
  }

  public void addLast(E element) {
    if (first == null) {
      first = last = new Node<>(element, null);
      size++;
      return;
    }

    Node<E> n = new Node<>(element, null);
    last.next = n;
    last = n;
    size++;
  }

  public void add(int index, E element) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> n = first, prev = null;
    for (int i = 0; i < index; i++) {
      prev = n;
      n = n.next;
    }

    Node<E> newNode = new Node<>(element, n);
    if (prev == null) {
      first = newNode;
    } else {
      prev.next = newNode;
    }

    if (prev == last) {
      last = newNode;
    }

    size++;
  }

  public E removeFirst() {
    if (first == null) {
      throw new NoSuchElementException();
    }

    E element = first.element;
    if (first == last) {
      first = last = null;
    } else {
      first = first.next;
    }

    size--;
    return element;
  }

  public E removeLast() {
    if (last == null) {
      throw new NoSuchElementException();
    }

    E element = last.element;
    if (last == first) {
      first = last = null;
    } else {
      last = getPrevious(last);
      last.next = null;
    }

    size--;
    return element;
  }

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

    if (n == last) {
      last = prev;
    }

    size--;
    return element;
  }

  private Node<E> getPrevious(Node<E> node) {
    Node<E> prev = null;
    for (Node<E> n = first; n != null && n != node; prev = n, n = n.next);
    return prev;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(":{");
    for (Node<E> n = first; n != null; n = n.next) {
      sb.append(n.element);
      sb.append(", ");
    }

    if (!isEmpty()) {
      sb.delete(sb.length() - 2, sb.length());
    }

    sb.append("}");
    return sb.toString();
  }

  public E[] toArray() {
    int i = 0;
    E[] elements = (E[])new Object[size()];
    for (Node<E> n = first; n != null; n = n.next) {
      elements[i++] = n.element;
    }

    return elements;
  }

  static class Node<E> {
    E element;
    Node<E> next;

    Node() {
      this(null, null);
    }

    Node(E element, Node<E> next) {
      this.element = element;
      this.next = next;
    }

    @Override
    public String toString() {
      return String.format("%s:{element=%s, next=%h}", getClass().getSimpleName(), element, next);
    }
  }

}

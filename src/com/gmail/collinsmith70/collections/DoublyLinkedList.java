package com.gmail.collinsmith70.collections;

public class DoublyLinkedList<E> {

  Node<E> first;
  Node<E> last;
  int size;

  public DoublyLinkedList() {
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

    Node<E> n = null;
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

  public void addFirst(E element) {
    if (first == null) {
      first = last = new Node<>(null, element, null);
      size++;
      return;
    }

    Node<E> n = new Node<>(null, element, first);
    first.prev = n;
    first = n;
    size++;
  }

  public void addLast(E element) {
    if (last == null) {
      first = last = new Node<>(null, element, null);
      size++;
      return;
    }

    Node<E> n = new Node<>(last, element, null);
    last.next = n;
    last = n;
    size++;
  }

  public E removeFirst() {
    if (first == null) {
      return null;
    }

    E element = first.element;
    first = first.next;
    if (first != null) {
      first.prev = null;
    } else {
      first = last = null;
    }

    size--;
    return element;
  }

  public E removeLast() {
    if (last == null) {
      return null;
    }

    E element = last.element;
    last = last.prev;
    if (last != null) {
      last.next = null;
    } else {
      first = last = null;
    }

    size--;
    return element;
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

  public E[] toArray() {
    int i = 0;
    E[] elements = (E[])new Object[size()];
    for (Node<E> n = first; n != null; n = n.next) {
      elements[i++] = n.element;
    }

    return elements;
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

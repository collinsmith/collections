package com.gmail.collinsmith70.collections;

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
      if (n.element.equals(obj)) {
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

  public E removeFirst() {
    if (first == null) {
      return null;
    }

    E element = first.element;
    first = first.next;
    size--;
    return element;
  }

  public E removeLast() {
    if (last == null) {
      return null;
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
  }

}

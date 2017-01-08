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

  public boolean contains(Object obj) {
    for (Node<E> n = first; n != null; n = n.next) {
      if (n.element.equals(obj)) {
        return true;
      }
    }

    return false;
  }

  public void addFirst(E element) {
    if (first == null) {
      first = last = new Node<>(element, null);
    }

    Node<E> n = new Node<>(element, first);
    first = n;
  }

  public void addLast(E element) {
    if (first == null) {
      first = last = new Node<>(element, null);
      return;
    }

    Node<E> n = new Node<>(element, null);
    last.next = n;
    last = n;
  }

  public E removeFirst() {
    if (first == null) {
      return null;
    }

    E element = first.element;
    first = first.next;
    return element;
  }

  public E removeLast() {
    if (last == null) {
      return null;
    }

    E element = last.element;
    last = getPrevious(last);
    return element;
  }

  private Node<E> getPrevious(Node<E> node) {
    Node<E> prev = null;
    for (Node<E> n = first; n != null; prev = n, n = n.next);
    return prev;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(getClass().getSimpleName());
    sb.append(":{");
    for (Node<E> n = first; n != null; n = n.next) {
      sb.append(n.element);
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

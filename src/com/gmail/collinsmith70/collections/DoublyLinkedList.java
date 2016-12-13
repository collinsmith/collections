package com.gmail.collinsmith70.collections;

import java.util.ListIterator;

public class DoublyLinkedList<E> extends AbstractSinglyLinkedList<E, DoublyLinkedList.Node<E>> {

  private DoublyLinkedList() {
  }

  @Override
  public Node<E> get() {
    return new Node<>();
  }

  @Override
  protected Node<E> linkFirst(E e) {
    final Node<E> first = this.first;
    final Node<E> newNode = super.linkFirst(e);
    if (first != null) {
      first.prev = newNode;
    }

    return newNode;
  }

  @Override
  protected E unlinkFirst() {
    E data = super.unlinkFirst();
    if (first != null) {
      first.prev = null;
    }

    return data;
  }

  @Override
  protected Node<E> linkLast(E e) {
    final Node<E> last = this.last;
    final Node<E> newNode = super.linkLast(e);
    newNode.prev = last;
    return newNode;
  }

  @Override
  protected E unlinkLast() {
    final E element = last.data;
    final Node<E> prev = last.prev;
    last.data = null;
    last.prev = null;
    last = prev;
    if (prev == null) {
      first = null;
    } else {
      prev.next = null;
    }

    size--;
    modCount++;
    return element;
  }

  @Override
  protected Node<E> linkBefore(E e, int index) {
    final Node<E> successor = node(index);
    final Node<E> predecessor = successor.prev;
    final Node<E> newNode = new Node<>(predecessor, e, successor);
    successor.prev = newNode;
    if (predecessor == null) {
      first = newNode;
    } else {
      predecessor.next = newNode;
    }

    size++;
    modCount++;
    return newNode;
  }

  @Override
  Node<E> node(int index) {
    assert isElementIndex(index);

    int size = size();
    if (index < (size >> 1)) {
      Node<E> x = first;
      for (int i = 0; i < index; i++)
        x = x.next;
      return x;
    } else {
      Node<E> x = last;
      for (int i = size - 1; i > index; i--)
        x = x.prev;
      return x;
    }
  }

  protected static class Node<E> extends AbstractSinglyLinkedList.Node<E, Node<E>> {
    Node<E> prev;

    Node() {
      this(null, null, null);
    }

    Node(Node<E> prev, E element, Node<E> next) {
      super(element, next);
      this.prev = prev;
    }
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    checkElementIndex(index);
    return new DoublyLinkedListIterator(index);
  }

  protected class DoublyLinkedListIterator
      extends AbstractSinglyLinkedList<E, Node<E>>.SinglyLinkedListIterator<Node<E>> {

    DoublyLinkedListIterator(int index) {
      super(index);
    }

  }

}

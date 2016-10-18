package com.gmail.collinsmith70.util;

import java.util.AbstractList;

public class SinglyLinkedList<E> extends AbstractList<E> {

  private SinglyLinkedList() {
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public E get(int index) {
    return null;
  }

  static class Node<E> {

    Node<E> next;

    E data;

    Node(Node<E> next, E data) {
      _setNext(next);
      _setData(data);
    }

    Node<E> getNext() {
      return next;
    }

    boolean hasNext() {
      return getNext() != null;
    }

    protected final void _setNext(Node<E> next) {
      this.next = next;
    }

    void setNext(Node<E> next) {
      _setNext(next);
    }

    E getData() {
      return data;
    }

    protected final void _setData(E data) {
      this.data = data;
    }

    void setData(E data) {
      _setData(data);
    }

  }

  static final class ImmutableNode<E> extends Node<E> {

    static <E> Node<E> newImmutableNode(Node<E> next, E data) {
      return new ImmutableNode<E>(next, data);
    }

    ImmutableNode(Node<E> next, E data) {
      super(next, data);
    }

    @Override
    final void setNext(Node<E> next) {
      throw new UnsupportedOperationException("This object is immutable!");
    }

    @Override
    final void setData(E data) {
      throw new UnsupportedOperationException("This object is immutable!");
    }

  }

}

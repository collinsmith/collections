package com.gmail.collinsmith70.collections;

import java.util.AbstractList;

public class SinglyLinkedList<E> extends AbstractList<E> {

  private final Node<E> head;
  private Node<E> tail;

  private int size;

  public SinglyLinkedList() {
    this.head = new Node();
    _clear();
  }

  private void _clear() {
    this.head.next = null;
    this.tail = null;
    this.size = 0;
  }

  @Override
  public void clear() {
    _clear();
  }

  @Override
  public E get(int index) {
    return null;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void add(int index, E element) {
    super.add(index, element);
  }

  @Override
  public E remove(int index) {
    return super.remove(index);
  }

  private static class Node<E> {

    static final String TAG = Node.class.getSimpleName();

    E data;
    Node<E> next;

    boolean hasNext() {
      return next != null;
    }

    @Override
    public String toString() {
      return String.format("%s: { data=%s, next=%s }", TAG, data, next);
    }

  }

}

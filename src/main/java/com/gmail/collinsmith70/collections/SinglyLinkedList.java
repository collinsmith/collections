package com.gmail.collinsmith70.collections;

import java.util.AbstractList;

public class SinglyLinkedList<E> extends AbstractList<E> {

  private final Node<E> head;

  public SinglyLinkedList() {
    this.head = new Node();
  }

  @Override
  public E get(int index) {
    return null;
  }

  @Override
  public int size() {
    return 0;
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

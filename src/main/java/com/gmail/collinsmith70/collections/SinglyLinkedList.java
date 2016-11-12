package com.gmail.collinsmith70.collections;

import java.util.AbstractList;

public class SinglyLinkedList<E> extends AbstractList<E> {

  public SinglyLinkedList() {

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

  private static class Node<E> {

    E data;
    Node<E> next;

  }

}

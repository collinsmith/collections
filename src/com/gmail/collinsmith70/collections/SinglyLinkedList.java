package com.gmail.collinsmith70.collections;

import com.google.common.base.Preconditions;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.function.Supplier;

public class SinglyLinkedList<E, N extends SinglyLinkedList.Node<E, N>> extends AbstractSequentialList<E> {

  int size = 0;
  N first;
  N last;

  public SinglyLinkedList() {
  }

  /**
   * Links e as first element.
   */
  protected void linkFirst(E e, Supplier<N> supplier) {
    final N first = this.first;
    final N newNode = supplier.get();
    newNode.data = e;
    newNode.next = first;
    this.first = newNode;
    if (first == null) {
      last = newNode;
    }

    size++;
    modCount++;
  }

  protected void checkElementIndex(int index) {
    Preconditions.checkElementIndex(index, size());
  }

  protected final boolean isElementIndex(int index) {
    return 0 <= index && index < size();
  }

  @Override
  public int size() {
    return 0;
  }

  /**
   * Returns the (non-null) Node at the specified element index.
   */
  N node(int index) {
    assert isElementIndex(index);

    N x = first;
    for (int i = 0; i < index; i++) {
      x = x.next;
    }

    return x;
  }

  protected static class Node<E, N extends Node<E, N>> {
    E data;
    N next;

    Node(E element, N next) {
      this.data = element;
      this.next = next;
    }
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    checkElementIndex(index);
    return new SinglyLinkedListIterator<N>(index);
  }

  protected class SinglyLinkedListIterator<N extends Node<E, N>> implements ListIterator<E> {
    protected N current;
    protected N next;
    protected int nextIndex;

    SinglyLinkedListIterator(int index) {

    }

    @Override
    public boolean hasNext() {
      return false;
    }

    @Override
    public E next() {
      return null;
    }

    @Override
    public boolean hasPrevious() {
      return false;
    }

    @Override
    public E previous() {
      return null;
    }

    @Override
    public int nextIndex() {
      return 0;
    }

    @Override
    public int previousIndex() {
      return 0;
    }

    @Override
    public void remove() {

    }

    @Override
    public void set(E e) {

    }

    @Override
    public void add(E e) {

    }
  }

}

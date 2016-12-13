package com.gmail.collinsmith70.collections;

import com.google.common.base.Preconditions;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.function.Supplier;

abstract class AbstractSinglyLinkedList<E, N extends AbstractSinglyLinkedList.Node<E, N>>
    extends AbstractSequentialList<E>
    implements Supplier<N> {

  int size = 0;
  N first;
  N last;

  public AbstractSinglyLinkedList() {
  }

  /**
   * Links e as first element.
   */
  protected N linkFirst(E e) {
    final N first = this.first;
    final N newNode = get();
    newNode.data = e;
    newNode.next = first;
    this.first = newNode;
    if (first == null) {
      last = newNode;
    }

    size++;
    modCount++;
    return newNode;
  }

  protected E unlinkFirst() {
    assert first != null;
    final E element = first.data;
    final N next = first.next;
    first.data = null;
    first.next = null;
    first = next;
    if (next == null) {
      last = null;
    }

    size--;
    modCount++;
    return element;
  }

  /**
   * Links e as last element.
   */
  protected N linkLast(E e) {
    final N last = this.last;
    final N newNode = get();
    newNode.data = e;
    this.last = newNode;
    if (last == null) {
      first = newNode;
    } else {
      last.next = newNode;
    }

    size++;
    modCount++;
    return newNode;
  }

  protected E unlinkLast() {
    assert last != null;
    final E element = last.data;
    final N prev = node(size() - 1);
    last.data = null;
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

  protected N linkBefore(E e, int index) {
    final Link links = links(index);
    final N successor = links.node;
    final N predecessor = links.previous;
    final N newNode = get();
    newNode.data = e;
    newNode.next = successor;
    if (predecessor == null) {
      first = newNode;
    } else {
      predecessor.next = newNode;
    }

    size++;
    modCount++;
    return newNode;
  }

  protected void checkElementIndex(int index) {
    Preconditions.checkElementIndex(index, size());
  }

  protected final boolean isElementIndex(int index) {
    return 0 <= index && index < size();
  }

  @Override
  public int size() {
    return size;
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

  /**
   * Returns the (non-null) Node at the specified element index, paired with its possibly null node
   * preceding it.
   */
  Link links(int index) {
    assert isElementIndex(index);

    N x = null;
    N y = first;
    for (int i = 0; i < index; i++) {
      x = y;
      y = y.next;
    }

    return new Link(x, y);
  }

  protected class Link {
    N previous;
    N node;

    Link() {
      this(null, null);
    }

    Link(N previous, N node) {
      this.previous = previous;
      this.node = node;
    }
  }

  protected static class Node<E, N extends Node<E, N>> {
    E data;
    N next;

    Node() {
      this(null, null);
    }

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

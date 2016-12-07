package com.gmail.collinsmith70.collections;

import java.util.ListIterator;

public class DoublyLinkedList<E> extends AbstractSinglyLinkedList<E, DoublyLinkedList.Node<E>> {

  private DoublyLinkedList() {
  }

  @Override
  public Node<E> get() {
    return new Node<>();
  }

  /**
   * Links e as first element.
   */
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

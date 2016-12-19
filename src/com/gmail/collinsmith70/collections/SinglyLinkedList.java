package com.gmail.collinsmith70.collections;

import java.util.ListIterator;

public class SinglyLinkedList<E> extends AbstractSinglyLinkedList<E, SinglyLinkedList.Node<E>> {

  public SinglyLinkedList() {
  }

  @Override
  public Node<E> get() {
    return new Node<>();
  }

  protected static class Node<E> extends AbstractSinglyLinkedList.Node<E, Node<E>> {
    Node() {
      this(null, null);
    }

    Node(E element, Node<E> next) {
      super(element, next);
    }
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    checkElementIndex(index);
    return new SinglyLinkedListIterator(index);
  }

  protected class SinglyLinkedListIterator
      extends AbstractSinglyLinkedList<E, Node<E>>.SinglyLinkedListIterator {

    SinglyLinkedListIterator(int index) {
      super(index);
    }

  }

}

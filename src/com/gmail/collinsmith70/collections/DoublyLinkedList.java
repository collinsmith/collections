package com.gmail.collinsmith70.collections;

import java.util.ListIterator;
import java.util.function.Supplier;

public class DoublyLinkedList<E> extends SinglyLinkedList<E, DoublyLinkedList.Node<E>> {

  private DoublyLinkedList() {
  }

  /**
   * Links e as first element.
   */
  @Override
  protected void linkFirst(E e, Supplier<DoublyLinkedList.Node<E>> supplier) {
    final Node<E> first = this.first;
    final Node<E> newNode = new Node<>();
    super.linkFirst(e, () -> newNode);
    if (first != null) {
      first.prev = newNode;
    }
  }

  @Override
  Node<E> node(int index) {
    // assert isElementIndex(index);

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

  protected static class Node<E> extends SinglyLinkedList.Node<E, Node<E>> {
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

  protected class DoublyLinkedListIterator extends SinglyLinkedList<E, Node<E>>.SinglyLinkedListIterator<Node<E>> {

    DoublyLinkedListIterator(int index) {
      super(index);
    }

  }

}

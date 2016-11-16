package com.gmail.collinsmith70.collections;

import com.google.common.base.Preconditions;

import java.util.ListIterator;

public class DoublyLinkedList<E> extends SinglyLinkedList<E, DoublyLinkedList.Node<E>> {

  private DoublyLinkedList() {
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

    Node(Node<E> prev, E element, Node<E> next) {
      super(element, next);
      this.prev = prev;
    }
  }

  @Override
  public ListIterator<E> listIterator(int index) {
    Preconditions.checkElementIndex(index, size());
    return new DoublyLinkedListIterator(index);
  }

  protected class DoublyLinkedListIterator extends SinglyLinkedList<E, Node<E>>.SinglyLinkedListIterator<Node<E>> {

    DoublyLinkedListIterator(int index) {
      super(index);
    }

  }

}
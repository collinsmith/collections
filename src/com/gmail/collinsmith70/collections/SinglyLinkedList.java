package com.gmail.collinsmith70.collections;

import com.google.common.base.Preconditions;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> implements List<E> {

  Node<E> first;
  Node<E> last;
  int size;

  public SinglyLinkedList() {
  }

  public SinglyLinkedList(Iterable<E> elements) {
    addAll(elements);
  }

  /**
   * Creates a new link in the list after {@code prev} that should be followed by {@code next}.
   * Additionally:
   * <ul>
   *   <li>If {@code prev == null}, then {@link #first} is changed to reference this new node.</li>
   *   <li>If {@code next == null}, then {@link #last} is changed to reference this new node.</li>
   * </ul>
   *
   * @param prev    Node which should precede the new node
   * @param element Value of the new node
   * @param next    Node which will follow the new node
   *
   * @return The newly created node
   */
  Node<E> link(Node<E> prev, E element, Node<E> next) {
    final Node<E> newNode = new Node<>(element, next);
    if (prev == null) {
      first = newNode;
    } else {
      prev.next = newNode;
    }

    if (next == null) {
      last = newNode;
    }

    size++;
    return newNode;
  }

  /**
   * Removes the specified link from the list. If the link immediately preceding this one is known,
   * then {@link #unlink(Node, Node)} should be used instead, as this case can be optimized for.
   *
   * @param node Node to unlink
   *
   * @return Value of the node
   *
   * @see #unlink(Node, Node)
   */
  E unlink(Node<E> node) {
    Preconditions.checkArgument(node != null, "node cannot be null");
    Node<E> prev = getPrevious(node);
    return unlink(prev, node);
  }

  /**
   * Removes the specified link from the list. If {@code prev} is not known, then
   * {@link #unlink(Node)} may be used instead.
   *
   * @param prev Node which immediately precedes {@code node}
   * @param node Node to unlink
   *
   * @return Value of the node
   *
   * @see #unlink(Node)
   */
  E unlink(Node<E> prev, Node<E> node) {
    Preconditions.checkArgument(node != null, "node cannot be null");
    final E element = node.element;
    final Node<E> next = node.next;
    if (prev == null) {
      first = next;
    } else {
      Preconditions.checkArgument(prev.next == node, "node must immediately follow prev");
      prev.next = next;
    }

    if (next == null) {
      last = prev;
    } else {
      node.next = null;
    }

    node.element = null;
    size--;
    return element;
  }

  @Override
  public Iterator<E> iterator() {
    return new SinglyLinkedListIterator();
  }

  class SinglyLinkedListIterator implements Iterator<E> {

    Node<E> lastReturned;
    Node<E> next = first;
    int nextIndex;

    @Override
    public boolean hasNext() {
      return nextIndex < size();
    }

    @Override
    public E next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      lastReturned = next;
      next = next.next;
      nextIndex++;
      return lastReturned.element;
    }

    @Override
    public void remove() {
      if (lastReturned == null) {
        throw new IllegalStateException();
      }

      unlink(lastReturned);
      nextIndex--;
      lastReturned = null;
    }

    @Override
    public String toString() {
      return String.format("%s:{lastReturned:%h, next:%h, lastIndex:%d, elements:%s}",
          getClass().getSimpleName(), lastReturned, next, nextIndex, getElementsString(next));
    }

  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public void clear() {
    first = last = null;
    size = 0;
  }

  @Override
  public boolean contains(Object obj) {
    for (Node<E> n = first; n != null; n = n.next) {
      if (Objects.equals(n.element, obj)) {
        return true;
      }
    }

    return false;
  }

  @Override
  public E get(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> n = first;
    for (int i = 0; i < index; i++) {
      n = n.next;
    }

    return n.element;
  }

  @Override
  public void set(int index, E element) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> n = first;
    for (int i = 0; i < index; i++) {
      n = n.next;
    }

    n.element = element;
  }

  @Override
  public void add(int index, E element) {
    if (index < 0 || index > size()) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> node = first, prev = null;
    for (int i = 0; i < index; i++) {
      prev = node;
      node = node.next;
    }

    link(prev, element, node);
  }

  @Override
  public E remove(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> node = first, prev = null;
    for (int i = 0; i < index; i++) {
      prev = node;
      node = node.next;
    }

    return unlink(prev, node);
  }

  @Override
  public boolean remove(Object obj) {
    int i = 0;
    for (Node<E> n = first; n != null; n = n.next, i++) {
      if (Objects.equals(n.element, obj)) {
        remove(i);
        return true;
      }
    }

    return false;
  }

  Node<E> getPrevious(Node<E> node) {
    Node<E> prev = null;
    for (Node<E> n = first; n != null && n != node; prev = n, n = n.next);
    return prev;
  }

  Node<E> getNode(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    Node<E> n = first;
    for (int i = 0; i < index; i++) {
      n = n.next;
    }

    return n;
  }

  private String getElementsString() {
    return getElementsString(first);
  }

  private String getElementsString(Node<E> first) {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (Node<E> n = first; n != null; n = n.next) {
      sb.append(n.element);
      sb.append(", ");
    }

    if (sb.length() > 1) {
      sb.delete(sb.length() - 2, sb.length());
    }

    sb.append("]");
    return sb.toString();
  }

  @Override
  public String toString() {
    return getElementsString();
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> T[] toArray(T[] array) {
    int size = size();
    if (array.length < size) {
      array = (T[]) Array.newInstance(array.getClass().getComponentType(), size);
    }

    int i = 0;
    for (Node<E> n = first; n != null; n = n.next) {
      array[i++] = (T) n.element;
    }

    if (array.length > size) {
      array[size] = null;
    }

    return array;
  }

  String toStateString() {
    return String.format("%s:{first:%s, last:%h, size:%d, elements:%s}",
        getClass().getSimpleName(), first != null ? first.toStateString() : null, last, size, getElementsString());
  }

  static class Node<E> {
    E element;
    Node<E> next;

    Node(E element, Node<E> next) {
      this.element = element;
      this.next = next;
    }

    @Override
    public String toString() {
      return String.format("%s:%s", getClass().getSimpleName(), toStateString());
    }

    public String toStateString() {
      return String.format("{element:%s, next:%h}", element, next);
    }
  }

}

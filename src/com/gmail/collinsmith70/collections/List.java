package com.gmail.collinsmith70.collections;

import java.util.NoSuchElementException;

public interface List<E> extends Collection<E> {

  E get(int index);

  void set(int index, E element);

  void add(int index, E element);

  @Override
  default void add(E element) {
    addLast(element);
  }

  default void addFirst(E element) {
    add(0, element);
  }

  default void addLast(E element) {
    add(size(), element);
  }

  E remove(int index);

  default E removeFirst() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }

    return remove(0);
  }

  default E removeLast() {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }

    return remove(size() - 1);
  }

}

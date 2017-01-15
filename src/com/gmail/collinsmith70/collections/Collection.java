package com.gmail.collinsmith70.collections;

public interface Collection<E> extends Iterable<E> {

  int size();

  default boolean isEmpty() {
    return size() == 0;
  }

  void clear();

  void add(E element);

  default void addAll(E[] elements) {
    for (E element : elements) {
      add(element);
    }
  }

  boolean remove(Object obj);

  default void removeAll(E[] elements) {
    for (E element : elements) {
      remove(element);
    }
  }

  boolean contains(Object obj);

  <T> T[] toArray(T[] array);

}

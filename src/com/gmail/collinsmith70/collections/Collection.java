package com.gmail.collinsmith70.collections;

public interface Collection<E> {

  int size();

  default boolean isEmpty() {
    return size() == 0;
  }

  void clear();

  void add(E element);

  boolean remove(Object obj);

  boolean contains(Object obj);

  <T> T[] toArray(T[] array);

}

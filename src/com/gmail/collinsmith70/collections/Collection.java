package com.gmail.collinsmith70.collections;

/**
 *
 * @author Collin Smith <i>collinsmith70@gmail.com</i>
 */
public interface Collection<E> {
    boolean contains(Object obj);
    boolean remove(Object obj);
    void add(E e);
    int size();
    
    default boolean isEmpty() {
        return size() == 0;
    }
}

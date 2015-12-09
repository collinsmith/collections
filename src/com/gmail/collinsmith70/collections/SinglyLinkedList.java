
package com.gmail.collinsmith70.collections;

/**
 *
 * @author Collin Smith <i>collinsmith70@gmail.com</i>
 */
public class SinglyLinkedList<E> implements LinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    private SinglyLinkedList() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void add(E e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private static class Node<E> {
        E data;
        Node<E> next;
        
        Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
        
        boolean hasNext() {
            return next != null;
        }
    }
}

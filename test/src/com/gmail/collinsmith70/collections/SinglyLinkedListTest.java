package com.gmail.collinsmith70.collections;

import org.junit.Test;

public class SinglyLinkedListTest {

  @Test
  public void testAddLast() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    System.out.println(l);
    l.addLast(1);
    System.out.println(l);
    l.addLast(3);
    System.out.println(l);
    l.addLast(5);
    System.out.println(l);
    l.addLast(7);
    System.out.println(l);
    l.addLast(11);
    System.out.println(l);
  }

  @Test
  public void testAddFirst() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    System.out.println(l);
    l.addFirst(1);
    System.out.println(l);
    l.addFirst(3);
    System.out.println(l);
    l.addFirst(5);
    System.out.println(l);
    l.addFirst(7);
    System.out.println(l);
    l.addFirst(11);
    System.out.println(l);
  }

}

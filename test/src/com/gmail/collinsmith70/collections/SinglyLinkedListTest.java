package com.gmail.collinsmith70.collections;

import org.junit.Test;

public class SinglyLinkedListTest {

  @Test
  public void testAddLast() {
    System.out.println("testAddLast");
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
    System.out.println("testAddFirst");
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

  @Test
  public void testRemoveLast() {
    System.out.println("testRemoveLast");
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    l.addLast(1);
    l.addLast(3);
    l.addLast(5);
    l.addLast(7);
    l.addLast(11);
    System.out.println(l);
    int i;
    while (!l.isEmpty()) {
      i = l.removeLast();
      System.out.println(l + "; " + i);
    }
  }

  @Test
  public void testRemoveFirst() {
    System.out.println("testRemoveFirst");
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    l.addLast(1);
    l.addLast(3);
    l.addLast(5);
    l.addLast(7);
    l.addLast(11);
    System.out.println(l);
    int i;
    while (!l.isEmpty()) {
      i = l.removeFirst();
      System.out.println(l + "; " + i);
    }
  }

}

package com.gmail.collinsmith70.collections;

import junit.framework.Assert;

import org.junit.Test;

public class SinglyLinkedListTest {

  final int[] PRIMES = { 2, 3, 5, 7, 11 };

  @Test
  public void testGet() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      l.addLast(prime);
      for (int j = 0; j <= i; j++) {
        Assert.assertEquals((int)l.get(j), PRIMES[j]);
      }
    }
  }

  @Test
  public void testAddLast() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    System.out.println(l);
    for (int prime : PRIMES) {
      l.addLast(prime);
      Assert.assertEquals((int)l.get(l.size()-1), prime);
      System.out.println(l);
    }
  }

  @Test
  public void testAddFirst() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    System.out.println(l);
    for (int prime : PRIMES) {
      l.addFirst(prime);
      Assert.assertEquals((int) l.get(0), prime);
      System.out.println(l);
    }
  }

  @Test
  public void testRemoveLast() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int i = PRIMES.length - 1; i >= 0; i--) {
      int prime = PRIMES[i];
      int n = l.removeLast();
      Assert.assertEquals(n, prime);
      System.out.println(l + "; " + n);
    }
  }

  @Test
  public void testRemoveFirst() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int prime : PRIMES) {
      int n = l.removeFirst();
      Assert.assertEquals(n, prime);
      System.out.println(l + "; " + n);
    }
  }

}

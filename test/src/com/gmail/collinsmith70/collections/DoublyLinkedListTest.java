package com.gmail.collinsmith70.collections;

import junit.framework.Assert;

import org.junit.Test;

import static com.gmail.collinsmith70.collections.TestData.PRIMES;

public class DoublyLinkedListTest {

  @Test
  public void testGet() {
    DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      l.addLast(prime);
      for (int j = 0; j <= i; j++) {
        Assert.assertEquals((int) l.get(j), PRIMES[j]);
        System.out.printf("%s; [%d]%d=%d%n", l, j, l.get(j), PRIMES[j]);
      }
    }
  }

  @Test
  public void testAddFirst() {
    DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    System.out.println(l);
    for (int prime : PRIMES) {
      l.addFirst(prime);
      Assert.assertEquals((int) l.get(0), prime);
      System.out.println(l);
    }
  }

  @Test
  public void testAddLast() {
    DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    System.out.println(l);
    for (int prime : PRIMES) {
      l.addLast(prime);
      Assert.assertEquals((int) l.get(l.size() - 1), prime);
      System.out.println(l);
    }
  }

}

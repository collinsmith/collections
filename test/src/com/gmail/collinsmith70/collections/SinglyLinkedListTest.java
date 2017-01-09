package com.gmail.collinsmith70.collections;

import junit.framework.Assert;

import org.junit.Test;

import static com.gmail.collinsmith70.collections.TestData.PRIMES;

public class SinglyLinkedListTest {

  @Test
  public void testGet() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      l.addLast(prime);
      for (int j = 0; j <= i; j++) {
        Assert.assertEquals((int)l.get(j), PRIMES[j]);
        System.out.printf("%s; [%d]%d=%d%n", l, j, l.get(j), PRIMES[j]);
      }
    }
  }

  @Test
  public void testAddLast() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      l.addLast(prime);
      Assert.assertEquals(l.size(), i + 1);
      Assert.assertEquals((int) l.get(l.size() - 1), prime);
      System.out.println(l);
    }
  }

  @Test
  public void testAddFirst() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      l.addFirst(prime);
      Assert.assertEquals(l.size(), i + 1);
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
      Assert.assertEquals(l.size(), i);
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
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      int n = l.removeFirst();
      Assert.assertEquals(l.size(), PRIMES.length - i - 1);
      Assert.assertEquals(n, prime);
      System.out.println(l + "; " + n);
    }
  }

}

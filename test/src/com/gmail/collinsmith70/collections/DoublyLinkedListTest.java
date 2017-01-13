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
  public void testAddRemove() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    System.out.println(l);
    for (int prime : PRIMES) {
      l.addLast(prime);
      System.out.println(l);
    }

    for (int prime : PRIMES) {
      l.removeLast();
      System.out.println(l);
    }

    for (int prime : PRIMES) {
      l.addLast(prime);
      System.out.println(l);
    }

    for (int prime : PRIMES) {
      l.addLast(prime);
      System.out.println(l);
    }

    for (int prime : PRIMES) {
      l.removeLast();
      System.out.println(l);
    }

    for (int prime : PRIMES) {
      l.addLast(prime);
      System.out.println(l);
    }

    for (int prime : PRIMES) {
      l.removeFirst();
      System.out.println(l);
    }

    for (int prime : PRIMES) {
      l.addFirst(prime);
      System.out.println(l);
    }

    for (int prime : PRIMES) {
      l.removeLast();
      System.out.println(l);
    }

    for (int prime : PRIMES) {
      l.removeLast();
      System.out.println(l);
    }
  }

  @Test
  public void testAddFirst() {
    DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(l.size(), i);
      l.addFirst(prime);
      Assert.assertEquals(l.size(), i + 1);
      Assert.assertEquals((int) l.get(0), prime);
      System.out.println(l);
    }
  }

  @Test
  public void testAddLast() {
    DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(l.size(), i);
      l.addLast(prime);
      Assert.assertEquals(l.size(), i + 1);
      Assert.assertEquals((int) l.get(l.size() - 1), prime);
      System.out.println(l);
    }
  }

  @Test
  public void testRemoveFirst() {
    DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(l.size(), PRIMES.length - i);
      int n = l.removeFirst();
      Assert.assertEquals(l.size(), PRIMES.length - i - 1);
      Assert.assertEquals(n, prime);
      System.out.println(l + "; " + n);
    }
  }

  @Test
  public void testRemoveLast() {
    DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int i = PRIMES.length - 1; i >= 0; i--) {
      int prime = PRIMES[i];
      Assert.assertEquals(l.size(), i + 1);
      int n = l.removeLast();
      Assert.assertEquals(l.size(), i);
      Assert.assertEquals(n, prime);
      System.out.println(l + "; " + n);
    }
  }

}
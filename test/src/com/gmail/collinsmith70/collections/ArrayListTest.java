package com.gmail.collinsmith70.collections;

import junit.framework.Assert;

import org.junit.Test;

import static com.gmail.collinsmith70.collections.TestData.PRIMES;

public class ArrayListTest {

  @Test
  public void testGet() {
    ArrayList<Integer> l = new ArrayList<>();
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      l.addLast(prime);
      for (int j = 0; j <= i; j++) {
        Assert.assertEquals(PRIMES[j], (int) l.get(j));
        System.out.printf("%s; [%d]%d=%d%n", l, j, l.get(j), PRIMES[j]);
      }
    }
  }

  @Test
  public void testAddRemove() {
    ArrayList<Integer> l = new ArrayList<>(2);
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
  public void testAddLast() {
    ArrayList<Integer> l = new ArrayList<>();
    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(i, l.size());
      l.addLast(prime);
      Assert.assertEquals(i + 1, l.size());
      Assert.assertEquals(prime, (int) l.get(l.size() - 1));
      System.out.println(l);
    }
  }

  @Test
  public void testAddFirst() {
    ArrayList<Integer> l = new ArrayList<>();
    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(i, l.size());
      l.addFirst(prime);
      Assert.assertEquals(i + 1, l.size());
      Assert.assertEquals(prime, (int) l.get(0));
      System.out.println(l);
    }
  }

  @Test
  public void testInsertAtStart() {
    ArrayList<Integer> l = new ArrayList<>();
    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(i, l.size());
      l.add(0, prime);
      Assert.assertEquals(i + 1, l.size());
      Assert.assertEquals(prime, (int) l.get(0));
      System.out.println(l);
    }
  }

  @Test
  public void testInsertAtN() {
    ArrayList<Integer> l = new ArrayList<>();
    l.add(0, PRIMES[0]);
    l.add(l.size(), PRIMES[PRIMES.length-1]);
    System.out.println(l);
    for (int i = PRIMES.length - 2; i > 0; i--) {
      int prime = PRIMES[i];
      Assert.assertEquals(PRIMES.length - i, l.size());
      l.add(1, prime);
      Assert.assertEquals(PRIMES.length - i + 1, l.size());
      Assert.assertEquals(prime, (int) l.get(1));
      System.out.println(l);
    }
  }

  @Test
  public void testInsertAtEnd() {
    ArrayList<Integer> l = new ArrayList<>();
    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(i, l.size());
      l.add(l.size(), prime);
      Assert.assertEquals(i + 1, l.size());
      Assert.assertEquals(prime, (int) l.get(l.size() - 1));
      System.out.println(l);
    }
  }

  @Test
  public void testRemoveLast() {
    ArrayList<Integer> l = new ArrayList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int i = PRIMES.length - 1; i >= 0; i--) {
      int prime = PRIMES[i];
      Assert.assertEquals(i + 1, l.size());
      int n = l.removeLast();
      Assert.assertEquals(i, l.size());
      Assert.assertEquals(prime, n);
      System.out.println(l + "; " + n);
    }
  }

  @Test
  public void testRemoveFirst() {
    ArrayList<Integer> l = new ArrayList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(PRIMES.length - i, l.size());
      int n = l.removeFirst();
      Assert.assertEquals(PRIMES.length - i - 1, l.size());
      Assert.assertEquals(prime, n);
      System.out.println(l + "; " + n);
    }
  }

  @Test
  public void testRemoveFromStart() {
    ArrayList<Integer> l = new ArrayList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(PRIMES.length - i, l.size());
      int n = l.remove(0);
      Assert.assertEquals(PRIMES.length - i - 1, l.size());
      Assert.assertEquals(prime, n);
      System.out.println(l + "; " + n);
    }
  }

  @Test
  public void testRemoveFromN() {
    ArrayList<Integer> l = new ArrayList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int i = 1; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(PRIMES.length - i + 1, l.size());
      int n = l.remove(1);
      Assert.assertEquals(PRIMES.length - i, l.size());
      Assert.assertEquals(prime, n);
      System.out.println(l);
    }
  }

  @Test
  public void testRemoveFromEnd() {
    ArrayList<Integer> l = new ArrayList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int i = PRIMES.length - 1; i >= 0; i--) {
      int prime = PRIMES[i];
      Assert.assertEquals(i + 1, l.size());
      int n = l.remove(l.size() - 1);
      Assert.assertEquals(i, l.size());
      Assert.assertEquals(prime, n);
      System.out.println(l + "; " + n);
    }
  }

}

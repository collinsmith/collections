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
        Assert.assertEquals(PRIMES[j], (int)l.get(j));
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
  public void testAddLast() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(i, l.size());
      l.addLast(prime);
      System.out.println(l);
      Assert.assertEquals(i + 1, l.size());
      Assert.assertEquals(prime, (int) l.get(l.size() - 1));
      if (i == 0) {
        Assert.assertEquals(l.last, l.first);
      } else {
        Assert.assertNotSame(l.first, l.last);
        Assert.assertEquals(prime, (int) l.last.element);
      }
    }
  }

  @Test
  public void testAddFirst() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(i, l.size());
      l.addFirst(prime);
      System.out.println(l);
      Assert.assertEquals(i + 1, l.size());
      Assert.assertEquals(prime, (int) l.get(0));
      if (i == 0) {
        Assert.assertEquals(l.first, l.last);
      } else {
        Assert.assertNotSame(l.last, l.first);
        Assert.assertEquals(prime, (int) l.first.element);
      }
    }
  }

  @Test
  public void testInsertAtStart() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(i, l.size());
      l.add(0, prime);
      System.out.println(l);
      Assert.assertEquals(i + 1, l.size());
      Assert.assertEquals(prime, (int) l.get(0));
      if (i == 0) {
        Assert.assertEquals(l.first, l.last);
      } else {
        Assert.assertNotSame(l.last, l.first);
        Assert.assertEquals(prime, (int) l.first.element);
      }
    }
  }

  @Test
  public void testInsertAtN() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    l.add(0, PRIMES[0]);
    l.add(l.size(), PRIMES[PRIMES.length - 1]);
    System.out.println(l);
    for (int i = PRIMES.length - 2; i > 0; i--) {
      int prime = PRIMES[i];
      Assert.assertEquals(PRIMES.length - i, l.size());
      l.add(1, prime);
      System.out.println(l);
      Assert.assertEquals(PRIMES.length - i + 1, l.size());
      Assert.assertEquals(prime, (int) l.get(1));
    }
  }

  @Test
  public void testInsertAtEnd() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(i, l.size());
      l.add(l.size(), prime);
      System.out.println(l);
      Assert.assertEquals(i + 1, l.size());
      Assert.assertEquals(prime, (int) l.get(l.size() - 1));
      if (i == 0) {
        Assert.assertEquals(l.last, l.first);
      } else {
        Assert.assertNotSame(l.first, l.last);
        Assert.assertEquals(prime, (int) l.last.element);
      }
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
      Assert.assertEquals(i + 1, l.size());
      int n = l.removeLast();
      System.out.println(l + "; " + n);
      Assert.assertEquals(i, l.size());
      Assert.assertEquals(prime, n);
      switch (i) {
        case 0:
          Assert.assertNull(l.first);
          Assert.assertNull(l.last);
          break;
        case 1:
          Assert.assertEquals(l.last, l.first);
          break;
        default:
          Assert.assertNotSame(l.last, l.first);
          Assert.assertEquals(PRIMES[i - 1], (int) l.last.element);
      }
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
      Assert.assertEquals(PRIMES.length - i, l.size());
      int n = l.removeFirst();
      System.out.println(l + "; " + n);
      Assert.assertEquals(PRIMES.length - i - 1, l.size());
      Assert.assertEquals(prime, n);
      switch (PRIMES.length - i - 1) {
        case 0:
          Assert.assertNull(l.first);
          Assert.assertNull(l.last);
          break;
        case 1:
          Assert.assertEquals(l.first, l.last);
          break;
        default:
          Assert.assertNotSame(l.last, l.first);
          Assert.assertEquals(PRIMES[i + 1], (int) l.first.element);
      }
    }
  }

  @Test
  public void testRemoveFromStart() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int i = 0; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(PRIMES.length - i, l.size());
      int n = l.remove(0);
      System.out.println(l + "; " + n);
      Assert.assertEquals(PRIMES.length - i - 1, l.size());
      Assert.assertEquals(prime, n);
      if (i < PRIMES.length - 1) {
        Assert.assertEquals(PRIMES[i + 1], (int) l.first.element);
        if (i == PRIMES.length - 2) {
          Assert.assertEquals(l.last, l.first);
        }
      } else {
        Assert.assertNull(l.first);
        Assert.assertNull(l.last);
      }
    }
  }

  @Test
  public void testRemoveFromN() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int i = 1; i < PRIMES.length; i++) {
      int prime = PRIMES[i];
      Assert.assertEquals(PRIMES.length - i + 1, l.size());
      int n = l.remove(1);
      System.out.println(l + "; " + n);
      Assert.assertEquals(PRIMES.length - i, l.size());
      Assert.assertEquals(prime, n);
      if (i == PRIMES.length - 1) {
        Assert.assertEquals(l.last, l.first);
      } else {
        Assert.assertEquals(PRIMES[0], (int) l.first.element);
      }
    }
  }

  @Test
  public void testRemoveFromEnd() {
    SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
    for (int prime : PRIMES) {
      l.addLast(prime);
    }

    System.out.println(l);
    for (int i = PRIMES.length - 1; i >= 0; i--) {
      int prime = PRIMES[i];
      Assert.assertEquals(i + 1, l.size());
      int n = l.remove(l.size() - 1);
      System.out.println(l + "; " + n);
      Assert.assertEquals(i, l.size());
      Assert.assertEquals(prime, n);
      if (i > 0) {
        Assert.assertEquals(PRIMES[i - 1], (int) l.last.element);
        if (i == 1) {
          Assert.assertEquals(l.first, l.last);
        }
      } else {
        Assert.assertNull(l.first);
        Assert.assertNull(l.last);
      }
    }
  }

}

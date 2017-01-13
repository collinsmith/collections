package com.gmail.collinsmith70.collections;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.NoSuchElementException;

import static com.gmail.collinsmith70.collections.TestData.PRIMES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class SinglyLinkedListTests {

  public static class size {

    @Test
    public void incrementing() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      assertEquals(l.size, l.size());
      for (int i = 0; i < PRIMES.length; i++) {
        l.addLast(PRIMES[i]);
        assertEquals(l.size, l.size());
      }
    }

  }

  public static class isEmpty {

    @Test
    public void _true() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      assertTrue(l.isEmpty());
      assertEquals(l.size == 0, l.isEmpty());
    }

    @Test
    public void _false() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      for (int i = 0; i < PRIMES.length; i++) {
        l.addLast(PRIMES[i]);
        assertFalse(l.isEmpty());
        assertEquals(l.size == 0, l.isEmpty());
      }
    }

  }

  public static class contains {

    @Test
    public void _true() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
        assertTrue(l.contains(prime));
      }
    }

    @Test
    public void _false() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      for (int prime : PRIMES) {
        assertFalse(l.contains(prime));
      }
    }

    @Test
    public void true_contains_null() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(null);
      assertTrue(l.contains(null));
    }

  }

  public static class get {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_low() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.get(1);
    }

    @Test
    public void incrementing() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      for (int i = 0; i < PRIMES.length; i++) {
        l.addLast(PRIMES[i]);
        for (int j = 0; j <= i; j++) {
          int last = l.get(j);
          assertEquals(PRIMES[j], last);
        }
      }
    }

  }

  public static class set {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.set(0, PRIMES[0]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_low() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.set(-1, PRIMES[1]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.set(1, PRIMES[1]);
    }

    @Test
    public void incrementing() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(0);
      }

      for (int i = 0; i < PRIMES.length; i++) {
        l.set(i, PRIMES[i]);
        assertEquals(PRIMES[i], (int) l.get(i));
      }
    }

  }

  public static class add {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty_low() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.add(-1, PRIMES[0]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty_high() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.add(1, PRIMES[0]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.add(2, PRIMES[1]);
    }

    @Test
    public void empty_first() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.add(0, PRIMES[0]);
      assertSame(l.first, l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertEquals(1, l.size);
    }

    @Test
    public void nonempty_insert_at_front() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      for (int i = 1; i < PRIMES.length; i++) {
        l.add(0, PRIMES[i]);
        assertNotSame(l.first, l.last);
        assertEquals(PRIMES[i], (int) l.first.element);
        assertEquals(PRIMES[0], (int) l.last.element);
        assertEquals(i + 1, l.size);
      }
    }

    @Test
    public void nonempty_insert_at_back() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      for (int i = 1; i < PRIMES.length; i++) {
        l.add(l.size, PRIMES[i]);
        assertNotSame(l.first, l.last);
        assertEquals(PRIMES[i], (int) l.last.element);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(i + 1, l.size);
      }
    }

    @Test
    public void nonempty_insert_in_between() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[PRIMES.length - 1]);
      for (int i = PRIMES.length - 2; i > 0; i--) {
        l.add(1, PRIMES[i]);
        assertEquals(PRIMES[i], (int) l.get(1));
        assertEquals(PRIMES.length - i + 1, l.size);
        assertEquals(PRIMES[0], (int) l.get(0));
        assertEquals(PRIMES[i + 1], (int) l.get(2));

        // Test that first and last are still the same, since this is in-between them
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.last.element);
      }
    }

  }

  public static class addFirst {

    @Test
    public void first() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addFirst(PRIMES[0]);
      assertSame(l.first, l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertEquals(1, l.size);
    }

    @Test
    public void second() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addFirst(PRIMES[0]);
      l.addFirst(PRIMES[1]);
      assertNotSame(l.first, l.last);
      assertEquals(PRIMES[1], (int) l.first.element);
      assertEquals(PRIMES[0], (int) l.last.element);
      assertEquals(2, l.size);
    }

    @Test
    public void nth() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addFirst(PRIMES[0]);
      l.addFirst(PRIMES[1]);
      for (int i = 2; i < PRIMES.length; i++) {
        l.addFirst(PRIMES[i]);
        assertNotSame(l.first, l.last);
        assertEquals(PRIMES[i], (int) l.first.element);
        assertEquals(PRIMES[0], (int) l.last.element);
        assertEquals(i + 1, l.size);
      }
    }

  }

  public static class addLast {

    @Test
    public void first() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      assertSame(l.first, l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertEquals(1, l.size);
    }

    @Test
    public void second() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      assertNotSame(l.first, l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertEquals(PRIMES[1], (int) l.last.element);
      assertEquals(2, l.size);
    }

    @Test
    public void nth() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      for (int i = 2; i < PRIMES.length; i++) {
        l.addLast(PRIMES[i]);
        assertNotSame(l.first, l.last);
        assertEquals(PRIMES[i], (int) l.last.element);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(i + 1, l.size);
      }
    }

  }

  public static class remove {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty_low() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty_high() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.remove(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.remove(2);
    }

    @Test
    public void nonempty_last() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      int last = l.remove(0);
      assertEquals(PRIMES[0], last);
      assertEquals(0, l.size);
      assertNull(l.first);
      assertNull(l.last);
    }

    @Test
    public void nonempty_second_to_last_from_front() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      int first = l.remove(0);
      assertEquals(PRIMES[0], first);
      assertEquals(1, l.size);
      assertSame(l.first, l.last);
      assertEquals(PRIMES[1], (int) l.first.element);
    }

    @Test
    public void nonempty_second_to_last_from_back() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      int last = l.remove(1);
      assertEquals(PRIMES[1], last);
      assertEquals(1, l.size);
      assertSame(l.first, l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
    }

    @Test
    public void nonempty_remove_from_front() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      for (int i = 0; i < PRIMES.length - 1; i++) {
        int first = l.remove(0);
        assertEquals(PRIMES[i], first);
        assertEquals(PRIMES.length - i - 1, l.size);
        assertEquals(PRIMES[i + 1], (int) l.first.element);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.last.element);
      }
    }

    @Test
    public void nonempty_remove_from_back() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      for (int i = PRIMES.length - 1; i > 0; i--) {
        int last = l.remove(l.size - 1);
        assertEquals(PRIMES[i], last);
        assertEquals(i, l.size);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(PRIMES[i - 1], (int) l.last.element);
      }
    }

    @Test
    public void nonempty_remove_in_between() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      for (int i = 1; i < PRIMES.length - 1; i++) {
        int nth = l.remove(1);
        assertEquals(PRIMES[i], nth);
        assertEquals(PRIMES.length - i, l.size);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.last.element);
      }
    }

  }

  public static class removeFirst {

    @Test(expected = NoSuchElementException.class)
    public void fails_empty() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.removeFirst();
    }

    @Test
    public void last() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      int first = l.removeFirst();
      assertEquals(0, l.size);
      assertEquals(PRIMES[0], first);
      assertNull(l.first);
      assertNull(l.last);
    }

    @Test
    public void nth() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      for (int i = 0; i < PRIMES.length - 2; i++) {
        int first = l.removeFirst();
        assertEquals(PRIMES[i], first);
        assertEquals(PRIMES.length - i - 1, l.size);
        assertEquals(PRIMES[i + 1], (int) l.first.element);
      }
    }

  }

  public static class removeLast {

    @Test(expected = NoSuchElementException.class)
    public void fails_empty() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.removeLast();
    }

    @Test
    public void last() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.addLast(PRIMES[0]);
      int last = l.removeLast();
      assertEquals(0, l.size);
      assertEquals(PRIMES[0], last);
      assertNull(l.first);
      assertNull(l.last);
    }

    @Test
    public void nth() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      for (int i = PRIMES.length - 1; i > 0; i--) {
        int last = l.removeLast();
        assertEquals(PRIMES[i], last);
        assertEquals(i, l.size);
        assertEquals(PRIMES[i - 1], (int) l.last.element);
      }
    }

  }

}

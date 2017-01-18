package com.gmail.collinsmith70.collections;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static com.gmail.collinsmith70.collections.TestData.PRIMES;
import static com.gmail.collinsmith70.collections.TestData.WRAPPED_PRIMES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class DoublyLinkedListTests {

  static final boolean output = true;

  public static class _DoublyLinkedList {

    @Test
    public void default_constructor() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      assertNull(l.first);
      assertNull(l.last);
      assertEquals(0, l.size);
    }

  }

  public static class link {

    @Test
    public void first() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      if (output) System.out.println(l.toStateString());
      assertSame(first, l.first);
      assertSame(l.first, l.last);
    }

    @Test
    public void prepend() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      DoublyLinkedList.Node<Integer> next, node = l.link(null, PRIMES[0], null);
      if (output) System.out.println(l.toStateString());
      for (int i = 1; i < PRIMES.length; i++) {
        next = node;
        node = l.link(null, PRIMES[i], next);
        if (output) System.out.println(l.toStateString());
        assertEquals(PRIMES[i], (int) node.element);
        assertEquals(i + 1, l.size);
        assertSame(next, node.next);
        assertSame(node, next.prev);
        assertNull(node.prev);
        assertSame(l.first, node);
      }
    }

    @Test
    public void append() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      DoublyLinkedList.Node<Integer> prev, node = l.link(null, PRIMES[0], null);
      if (output) System.out.println(l.toStateString());
      for (int i = 1; i < PRIMES.length; i++) {
        prev = node;
        node = l.link(prev, PRIMES[i], null);
        if (output) System.out.println(l.toStateString());
        assertEquals(PRIMES[i], (int) node.element);
        assertEquals(i + 1, l.size);
        assertSame(node, prev.next);
        assertSame(prev, node.prev);
        assertNull(node.next);
        assertSame(l.last, node);
      }
    }

    @Test
    public void insert() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      DoublyLinkedList.Node<Integer> prev, node, next;
      prev = l.link(null, PRIMES[0], null);
      node = l.link(prev, PRIMES[PRIMES.length - 1], null);
      if (output) System.out.println(l.toStateString());
      for (int i = 1; i < PRIMES.length - 1; i++) {
        next = node;
        node = l.link(prev, PRIMES[i], next);
        if (output) System.out.println(l.toStateString());
        assertEquals(PRIMES[i], (int) node.element);
        assertEquals(i + 2, l.size);
        assertSame(node, prev.next);
        assertSame(prev, node.prev);
        assertSame(next, node.next);
        assertSame(node, next.prev);
      }
    }

  }

  public static class unlink {

    @Test(expected = IllegalArgumentException.class)
    public void fails_remove_null() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      l.unlink(null);
    }

    @Test
    public void ultimate() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      DoublyLinkedList.Node<Integer> node = l.link(null, PRIMES[0], null);
      if (output) System.out.println(l.toStateString());
      int element = l.unlink(node);
      if (output) System.out.println(l.toStateString());
      assertEquals(PRIMES[0], element);
      assertEquals(0, l.size);
      assertNull(node.next);
      assertNull(node.prev);
      assertNull(node.element);
      assertNull(l.first);
      assertNull(l.last);
    }

    @Test
    public void first() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>(Arrays.asList(WRAPPED_PRIMES));
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList.Node<Integer> oldFirst, newFirst = l.first;
      for (int i = 0; i < PRIMES.length - 1; i++) {
        oldFirst = newFirst;
        newFirst = newFirst != null ? newFirst.next : null;
        int element = l.unlink(oldFirst);
        if (output) System.out.println(l.toStateString());
        assertEquals(PRIMES[i], element);
        assertEquals(PRIMES.length - i - 1, l.size);
        assertNull(oldFirst.next);
        assertNull(oldFirst.prev);
        assertNull(oldFirst.element);
        assertSame(newFirst, l.first);
        assertNull(newFirst.prev);
      }

      assertSame(l.first, l.last);
    }

    @Test
    public void last() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>(Arrays.asList(WRAPPED_PRIMES));
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList.Node<Integer> newLast, oldLast;
      for (int i = PRIMES.length - 1; i > 0; i--) {
        oldLast = l.last;
        newLast = oldLast.prev;
        int element = l.unlink(oldLast);
        if (output) System.out.println(l.toStateString());
        assertEquals(PRIMES[i], element);
        assertEquals(i, l.size);
        assertNull(oldLast.next);
        assertNull(oldLast.prev);
        assertNull(oldLast.element);
        assertNull(newLast.next);
        assertSame(newLast, l.last);
      }

      assertSame(l.first, l.last);
    }

    @Test
    public void extract() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>(Arrays.asList(WRAPPED_PRIMES));
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList.Node<Integer> prev, extracted, next;
      for (int i = 1; i < PRIMES.length - 1; i++) {
        prev = l.first;
        extracted = prev.next;
        next = extracted.next;
        int element = l.unlink(extracted);
        if (output) System.out.println(l.toStateString());
        assertEquals(PRIMES[i], element);
        assertEquals(PRIMES.length - i, l.size);
        assertNull(extracted.next);
        assertNull(extracted.prev);
        assertNull(extracted.element);
        assertSame(next, prev.next);
        assertSame(prev, next.prev);
      }
    }

  }

  public static class iterator {

    @Test(expected = NoSuchElementException.class)
    public void fails_next() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList<Integer>.DoublyLinkedListIterator it
          = (DoublyLinkedList<Integer>.DoublyLinkedListIterator) l.iterator();
      Integer element = it.next();
    }

    @Test
    public void next() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>(Arrays.asList(WRAPPED_PRIMES));
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList<Integer>.DoublyLinkedListIterator it
          = (DoublyLinkedList<Integer>.DoublyLinkedListIterator) l.iterator();
      if (output) System.out.println(it);
      assertNull(it.lastReturned);
      assertSame(l.first, it.next);
      assertEquals(0, it.nextIndex);
      for (int i = 0; i < WRAPPED_PRIMES.length; i++) {
        assertTrue(it.hasNext());
        DoublyLinkedList.Node<Integer> prevNext = it.next;
        Integer element = it.next();
        if (output) System.out.println(it);
        assertSame(WRAPPED_PRIMES[i], element);
        assertSame(prevNext, it.lastReturned);
        assertSame(prevNext.next, it.next);
        assertEquals(i + 1, it.nextIndex);
      }
    }

    @Test(expected = IllegalStateException.class)
    public void fails_remove() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>(Arrays.asList(WRAPPED_PRIMES));
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList<Integer>.DoublyLinkedListIterator it
          = (DoublyLinkedList<Integer>.DoublyLinkedListIterator) l.iterator();
      if (output) System.out.println(it);
      it.remove();
    }

    @Test
    public void remove() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>(Arrays.asList(WRAPPED_PRIMES));
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList<Integer>.DoublyLinkedListIterator it
          = (DoublyLinkedList<Integer>.DoublyLinkedListIterator) l.iterator();
      if (output) System.out.println(it);
      assertNull(it.lastReturned);
      assertSame(l.first, it.next);
      assertEquals(0, it.nextIndex);
      for (int i = 0; i < WRAPPED_PRIMES.length; i++) {
        assertTrue(it.hasNext());
        Integer element = it.next();
        assertSame(WRAPPED_PRIMES[i], element);
        assertNotNull(it.lastReturned);
        assertEquals(1, it.nextIndex);

        it.remove();
        if (output) System.out.println(it);
        assertNull(it.lastReturned);
        assertEquals(0, it.nextIndex);
      }
    }

  }

  public static class getNode {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.getNode(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_low() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.getNode(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.getNode(1);
    }

    @Test
    public void single_element() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      assertSame(l.first, l.getNode(0));
    }

    @Test
    public void two_elements() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      assertSame(l.first, l.getNode(0));
      assertSame(l.last, l.getNode(1));
    }

    @Test
    public void n_elements() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      for (int i = 0; i < PRIMES.length; i++) {
        l.addLast(PRIMES[i]);
        if (output) System.out.println(l.toStateString());
        assertSame(l.last, l.getNode(i));
      }
    }

  }

  public static class clear {

    @Test
    public void empty() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      l.clear();
      if (output) System.out.println(l.toStateString());
      assertNull(l.first);
      assertNull(l.last);
      assertEquals(0, l.size);
    }

    @Test
    public void nonempty() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      if (output) System.out.println(l.toStateString());
      l.clear();
      if (output) System.out.println(l.toStateString());
      assertNull(l.first);
      assertNull(l.last);
      assertEquals(0, l.size);
    }

  }

  public static class size {

    @Test
    public void incrementing() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      assertEquals(l.size, l.size());
      for (int i = 0; i < PRIMES.length; i++) {
        l.addLast(PRIMES[i]);
        if (output) System.out.println(l.toStateString());
        assertEquals(l.size, l.size());
      }
    }

    @Test
    public void decrementing() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      if (output) System.out.println(l.toStateString());
      assertEquals(l.size, l.size());
      for (int i = 0; i < PRIMES.length; i++) {
        l.removeLast();
        if (output) System.out.println(l.toStateString());
        assertEquals(l.size, l.size());
      }
    }

  }

  public static class isEmpty {

    @Test
    public void _true() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString() + " isEmpty()");
      assertTrue(l.isEmpty());
      assertEquals(l.size == 0, l.isEmpty());
    }

    @Test
    public void _false() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      for (int i = 0; i < PRIMES.length; i++) {
        l.addLast(PRIMES[i]);
        if (output) System.out.println(l.toStateString() + " !isEmpty()");
        assertFalse(l.isEmpty());
        assertEquals(l.size == 0, l.isEmpty());
      }
    }

  }

  public static class contains {

    @Test
    public void _true() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
        if (output) System.out.println(l.toStateString() + " contains " + prime);
        assertTrue(l.contains(prime));
      }
    }

    @Test
    public void _false() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      for (int prime : PRIMES) {
        if (output) System.out.println(l.toStateString() + " does not contain " + prime);
        assertFalse(l.contains(prime));
      }
    }

    @Test
    public void true_contains_null() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(null);
      if (output) System.out.println(l.toStateString() + " contains " + null);
      assertTrue(l.contains(null));
    }

  }

  public static class get {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_low() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.get(1);
    }

    @Test
    public void incrementing() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      for (int i = 0; i < PRIMES.length; i++) {
        l.addLast(PRIMES[i]);
        for (int j = 0; j <= i; j++) {
          int last = l.get(j);
          if (output) System.out.printf("%s get(%d)=%d%n", l.toStateString(), j, last);
          assertEquals(PRIMES[j], last);
        }
      }
    }

  }

  public static class set {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.set(0, PRIMES[0]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_low() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.set(-1, PRIMES[1]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.set(1, PRIMES[1]);
    }

    @Test
    public void incrementing() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(0);
      }

      for (int i = 0; i < PRIMES.length; i++) {
        l.set(i, PRIMES[i]);
        if (output) System.out.printf("%s set(%d)=%d%n", l.toStateString(), i, PRIMES[i]);
        assertEquals(PRIMES[i], (int) l.get(i));
      }
    }

  }

  public static class add_Object {

    @Test
    public void first() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      l.add(PRIMES[0]);
      if (output) System.out.printf("%s add(%d)%n", l.toStateString(), PRIMES[0]);
      assertSame(l.first, l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertEquals(1, l.size);
      assertNull(l.first.next);
      assertNull(l.first.prev);
      assertNull(l.last.next);
      assertNull(l.last.prev);
    }

    @Test
    public void second() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.add(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      l.add(PRIMES[1]);
      if (output) System.out.printf("%s add(%d)%n", l.toStateString(), PRIMES[1]);
      assertNotSame(l.first, l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertEquals(PRIMES[1], (int) l.last.element);
      assertEquals(2, l.size);
      assertSame(l.first.next, l.last);
      assertSame(l.first, l.last.prev);
      assertNull(l.first.prev);
      assertNull(l.last.next);
    }

    @Test
    public void nth() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.add(PRIMES[0]);
      l.add(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList.Node<Integer> prevLast;
      for (int i = 2; i < PRIMES.length; i++) {
        prevLast = l.last;
        l.add(PRIMES[i]);
        if (output) System.out.printf("%s add(%d)%n", l.toStateString(), PRIMES[i]);
        assertNotSame(l.first, l.last);
        assertEquals(PRIMES[i], (int) l.last.element);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(i + 1, l.size);
        assertNull(l.last.next);
        assertNotSame(l.last, prevLast);
        assertSame(prevLast.next, l.last);
        assertSame(l.last.prev, prevLast);
      }
    }

  }

  public static class add_int_E {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty_low() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.add(-1, PRIMES[0]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty_high() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.add(1, PRIMES[0]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.add(2, PRIMES[1]);
    }

    @Test
    public void empty_first() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.add(0, PRIMES[0]);
      if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), 0, PRIMES[0]);
      assertSame(l.first, l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertEquals(1, l.size);
      assertNull(l.first.next);
      assertNull(l.first.prev);
      assertNull(l.last.next);
      assertNull(l.last.prev);
    }

    @Test
    public void nonempty_insert_at_front() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList.Node<Integer> prevFirst;
      for (int i = 1; i < PRIMES.length; i++) {
        prevFirst = l.first;
        l.add(0, PRIMES[i]);
        if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), 0, PRIMES[i]);
        assertNotSame(l.first, l.last);
        assertEquals(PRIMES[i], (int) l.first.element);
        assertEquals(PRIMES[0], (int) l.last.element);
        assertEquals(i + 1, l.size);
        assertEquals(PRIMES[i - 1], (int) l.first.next.element);
        assertNull(l.last.next);
        assertNull(l.first.prev);
        assertSame(prevFirst.prev, l.first);
        assertSame(l.first.next, prevFirst);
      }
    }

    @Test
    public void nonempty_insert_at_back() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList.Node<Integer> prevLast;
      for (int i = 1; i < PRIMES.length; i++) {
        prevLast = l.last;
        l.add(l.size, PRIMES[i]);
        if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), l.size - 1, PRIMES[i]);
        assertNotSame(l.first, l.last);
        assertEquals(PRIMES[i], (int) l.last.element);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(i + 1, l.size);
        assertNull(l.last.next);
        assertNull(l.first.prev);
        assertSame(prevLast.next, l.last);
        assertSame(l.last.prev, prevLast);
      }
    }

    @Test
    public void nonempty_insert_in_between() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[PRIMES.length - 1]);
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList.Node<Integer> third;
      for (int i = PRIMES.length - 2; i > 0; i--) {
        third = l.first.next;
        l.add(1, PRIMES[i]);
        if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), 1, PRIMES[i]);
        assertEquals(PRIMES[i], (int) l.first.next.element);
        assertEquals(PRIMES.length - i + 1, l.size);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(PRIMES[i + 1], (int) l.first.next.next.element);
        assertNull(l.last.next);
        assertNull(l.first.prev);
        assertSame(l.first, l.first.next.prev);
        assertSame(l.first.next.next, third);
        assertSame(third.prev, l.first.next);

        // Test that first and last are still the same, since this is in-between them
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.last.element);
      }
    }

  }

  public static class addFirst {

    @Test
    public void first() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      l.addFirst(PRIMES[0]);
      if (output) System.out.printf("%s addFirst(%d)%n", l.toStateString(), PRIMES[0]);
      assertSame(l.first, l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertEquals(1, l.size);
      assertNull(l.first.next);
      assertNull(l.first.prev);
      assertNull(l.last.next);
      assertNull(l.last.prev);
    }

    @Test
    public void second() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addFirst(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      l.addFirst(PRIMES[1]);
      if (output) System.out.printf("%s addFirst(%d)%n", l.toStateString(), PRIMES[1]);
      assertNotSame(l.first, l.last);
      assertEquals(PRIMES[1], (int) l.first.element);
      assertEquals(PRIMES[0], (int) l.last.element);
      assertEquals(2, l.size);
      assertSame(l.first.next, l.last);
      assertSame(l.first, l.last.prev);
      assertNull(l.last.next);
      assertNull(l.first.prev);
    }

    @Test
    public void nth() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addFirst(PRIMES[0]);
      l.addFirst(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList.Node<Integer> prevFirst;
      for (int i = 2; i < PRIMES.length; i++) {
        prevFirst = l.first;
        l.addFirst(PRIMES[i]);
        if (output) System.out.printf("%s addFirst(%d)%n", l.toStateString(), PRIMES[i]);
        assertNotSame(l.first, l.last);
        assertEquals(PRIMES[i], (int) l.first.element);
        assertEquals(PRIMES[0], (int) l.last.element);
        assertEquals(i + 1, l.size);
        assertNull(l.last.next);
        assertNull(l.first.prev);
        assertNotSame(l.first, prevFirst);
        assertSame(l.first.next, prevFirst);
        assertSame(prevFirst.prev, l.first);
      }
    }

  }

  public static class addLast {

    @Test
    public void first() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      l.addLast(PRIMES[0]);
      if (output) System.out.printf("%s addLast(%d)%n", l.toStateString(), PRIMES[0]);
      assertSame(l.first, l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertEquals(1, l.size);
      assertNull(l.first.next);
      assertNull(l.first.prev);
      assertNull(l.last.next);
      assertNull(l.last.prev);
    }

    @Test
    public void second() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      l.addLast(PRIMES[1]);
      if (output) System.out.printf("%s addLast(%d)%n", l.toStateString(), PRIMES[1]);
      assertNotSame(l.first, l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertEquals(PRIMES[1], (int) l.last.element);
      assertEquals(2, l.size);
      assertSame(l.first.next, l.last);
      assertSame(l.first, l.last.prev);
      assertNull(l.last.next);
      assertNull(l.first.prev);
    }

    @Test
    public void nth() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      DoublyLinkedList.Node<Integer> prevLast;
      for (int i = 2; i < PRIMES.length; i++) {
        prevLast = l.last;
        l.addLast(PRIMES[i]);
        if (output) System.out.printf("%s addLast(%d)%n", l.toStateString(), PRIMES[i]);
        assertNotSame(l.first, l.last);
        assertEquals(PRIMES[i], (int) l.last.element);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(i + 1, l.size);
        assertNull(l.last.next);
        assertNull(l.first.prev);
        assertNotSame(l.last, prevLast);
        assertSame(prevLast.next, l.last);
        assertSame(l.last.prev, prevLast);
      }
    }

  }

  public static class remove_Object {

    @Test
    public void _false() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      boolean removed = l.remove((Integer) PRIMES[0]);
      if (output) System.out.printf("%s remove((Integer)%d)%n", l.toStateString(), PRIMES[0]);
      assertFalse(removed);
      assertNull(l.first);
      assertNull(l.last);
    }

    @Test
    public void _true() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      boolean removed = l.remove((Integer) PRIMES[0]);
      if (output) System.out.printf("%s remove((Integer)%d)%n", l.toStateString(), PRIMES[0]);
      assertTrue(removed);
      assertNull(l.first);
      assertNull(l.last);
    }

    @Test
    public void true_removed_null() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(null);
      if (output) System.out.println(l.toStateString());
      boolean removed = l.remove(null);
      if (output) System.out.printf("%s remove(%h)%n", l.toStateString(), null);
      assertTrue(removed);
      assertNull(l.first);
      assertNull(l.last);
    }

  }

  public static class remove_int {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty_low() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty_high() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.remove(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.remove(2);
    }

    @Test
    public void nonempty_last() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      int last = l.remove(0);
      if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 0, last);
      assertEquals(PRIMES[0], last);
      assertEquals(0, l.size);
      assertNull(l.first);
      assertNull(l.last);
    }

    @Test
    public void nonempty_second_to_last_from_front() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      int first = l.remove(0);
      if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 0, first);
      assertEquals(PRIMES[0], first);
      assertEquals(1, l.size);
      assertSame(l.first, l.last);
      assertNotNull(l.first);
      assertNotNull(l.last);
      assertEquals(PRIMES[1], (int) l.first.element);
      assertNull(l.first.next);
      assertNull(l.first.prev);
      assertNull(l.last.next);
      assertNull(l.last.prev);
    }

    @Test
    public void nonempty_second_to_last_from_back() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      int last = l.remove(1);
      if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 1, last);
      assertEquals(PRIMES[1], last);
      assertEquals(1, l.size);
      assertSame(l.first, l.last);
      assertNotNull(l.first);
      assertNotNull(l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertNull(l.first.next);
      assertNull(l.first.prev);
      assertNull(l.last.next);
      assertNull(l.last.prev);
    }

    @Test
    public void nonempty_remove_from_front() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      if (output) System.out.println(l.toStateString());
      for (int i = 0; i < PRIMES.length - 2; i++) {
        int first = l.remove(0);
        if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 0, first);
        assertEquals(PRIMES[i], first);
        assertEquals(PRIMES.length - i - 1, l.size);
        assertEquals(PRIMES[i + 1], (int) l.first.element);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.last.element);
        assertNull(l.first.prev);
        assertNull(l.last.next);
      }
    }

    @Test
    public void nonempty_remove_from_back() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      if (output) System.out.println(l.toStateString());
      for (int i = PRIMES.length - 1; i > 1; i--) {
        int index = l.size - 1;
        int last = l.remove(index);
        if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), index, last);
        assertEquals(PRIMES[i], last);
        assertEquals(i, l.size);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(PRIMES[i - 1], (int) l.last.element);
        assertNull(l.first.prev);
        assertNull(l.last.next);
      }
    }

    @Test
    public void nonempty_remove_in_between() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      if (output) System.out.println(l.toStateString());
      DoublyLinkedList.Node<Integer> third;
      for (int i = 1; i < PRIMES.length - 2; i++) {
        third = l.first.next.next;
        int nth = l.remove(1);
        if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 1, nth);
        assertEquals(PRIMES[i], nth);
        assertEquals(PRIMES.length - i, l.size);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.last.element);
        assertEquals(PRIMES[i + 1], (int) l.first.next.element);
        assertSame(l.first.next, third);
        assertSame(l.first, third.prev);
        assertNull(l.first.prev);
        assertNull(l.last.next);
      }
    }

  }

  public static class removeFirst {

    @Test(expected = NoSuchElementException.class)
    public void fails_empty() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.removeFirst();
    }

    @Test
    public void last() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      int first = l.removeFirst();
      if (output) System.out.printf("%s removeFirst()=%d%n", l.toStateString(), first);
      assertEquals(0, l.size);
      assertEquals(PRIMES[0], first);
      assertNull(l.first);
      assertNull(l.last);
    }

    @Test
    public void second_to_last() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      int first = l.removeFirst();
      if (output) System.out.printf("%s removeFirst()=%d%n", l.toStateString(), first);
      assertEquals(1, l.size);
      assertEquals(PRIMES[0], first);
      assertNotNull(l.first);
      assertEquals(PRIMES[1], (int) l.first.element);
      assertSame(l.first, l.last);
      assertNotNull(l.last);
      assertNull(l.first.next);
      assertNull(l.first.prev);
      assertNull(l.last.next);
      assertNull(l.last.prev);
    }

    @Test
    public void nth() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      if (output) System.out.println(l.toStateString());
      for (int i = 0; i < PRIMES.length - 2; i++) {
        int first = l.removeFirst();
        if (output) System.out.printf("%s removeFirst()=%d%n", l.toStateString(), first);
        assertEquals(PRIMES[i], first);
        assertEquals(PRIMES.length - i - 1, l.size);
        assertEquals(PRIMES[i + 1], (int) l.first.element);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.last.element);
        assertNull(l.first.prev);
        assertNull(l.last.next);
      }
    }

  }

  public static class removeLast {

    @Test(expected = NoSuchElementException.class)
    public void fails_empty() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.removeLast();
    }

    @Test
    public void last() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      int last = l.removeLast();
      if (output) System.out.printf("%s removeLast()=%d%n", l.toStateString(), last);
      assertEquals(0, l.size);
      assertEquals(PRIMES[0], last);
      assertNull(l.first);
      assertNull(l.last);
    }

    @Test
    public void second_to_last() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      int last = l.removeLast();
      if (output) System.out.printf("%s removeLast()=%d%n", l.toStateString(), last);
      assertEquals(1, l.size);
      assertEquals(PRIMES[1], last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertSame(l.first, l.last);
      assertNotNull(l.first);
      assertNotNull(l.last);
      assertNull(l.first.next);
      assertNull(l.first.prev);
      assertNull(l.last.next);
      assertNull(l.last.prev);
    }

    @Test
    public void nth() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      if (output) System.out.println(l.toStateString());
      for (int i = PRIMES.length - 1; i > 1; i--) {
        int last = l.removeLast();
        if (output) System.out.printf("%s removeLast()=%d%n", l.toStateString(), last);
        assertEquals(PRIMES[i], last);
        assertEquals(i, l.size);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(PRIMES[i - 1], (int) l.last.element);
        assertNull(l.first.prev);
        assertNull(l.last.next);
      }
    }

  }

  public static class toArray {

    @Test
    public void empty() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      Integer[] array = l.toArray(new Integer[l.size]);
      if (output) System.out.printf("%s toArray=%s%n", l.toStateString(), Arrays.toString(array));
      assertNotNull(array);
      assertEquals(0, array.length);
    }

    @Test
    public void single() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      Integer[] array = l.toArray(new Integer[l.size]);
      if (output) System.out.printf("%s toArray=%s%n", l.toStateString(), Arrays.toString(array));
      assertNotNull(array);
      assertEquals(1, array.length);
      assertEquals(PRIMES[0], (int) array[0]);
    }

    @Test
    public void nth() {
      DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      for (int i = 1; i < PRIMES.length; i++) {
        l.addLast(PRIMES[i]);
        Integer[] array = l.toArray(new Integer[l.size]);
        if (output) System.out.printf("%s toArray=%s%n", l.toStateString(), Arrays.toString(array));
        assertNotNull(array);
        assertEquals(i + 1, array.length);
        for (int j = 0; j < array.length; j++) {
          assertEquals(PRIMES[j], (int) array[j]);
        }
      }
    }

  }

  @RunWith(Enclosed.class)
  public static class _Node {

    public static class toString {

      @Test
      public void empty() {
        DoublyLinkedList.Node<Integer> n = new DoublyLinkedList.Node<>(null, null, null);
        if (output) System.out.println(n.toStateString());
        String toString = n.toString();
        assertNotNull(toString);
        assertFalse(toString.isEmpty());
      }

    }

  }

}

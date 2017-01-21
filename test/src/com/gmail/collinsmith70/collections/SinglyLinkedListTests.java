package com.gmail.collinsmith70.collections;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.NoSuchElementException;

import static com.gmail.collinsmith70.collections.TestData.LISTED_PRIMES;
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
public class SinglyLinkedListTests {

  static final boolean output = true;

  public static class _SinglyLinkedList {

    @Test
    public void default_constructor() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      assertNull(l.first);
      assertNull(l.last);
      assertEquals(0, l.size);
    }

    @Test
    public void SinglyLinkedList_Iterable() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>(LISTED_PRIMES);
      if (output) System.out.println(l.toStateString());
      assertEquals(WRAPPED_PRIMES.length, l.size);
      int i = 0;
      for (Integer prime : l) {
        assertSame(WRAPPED_PRIMES[i++], prime);
      }
    }

  }

  public static class link {

    @Test
    public void first() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      if (output) System.out.println(l.toStateString());
      assertSame(first, l.first);
      assertSame(l.first, l.last);
    }

    @Test
    public void prepend() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> next, node = l.link(null, PRIMES[0], null);
      if (output) System.out.println(l.toStateString());
      for (int i = 1; i < PRIMES.length; i++) {
        next = node;
        node = l.link(null, PRIMES[i], next);
        if (output) System.out.println(l.toStateString());
        assertEquals(PRIMES[i], (int) node.element);
        assertEquals(i + 1, l.size);
        assertSame(next, node.next);
        assertSame(l.first, node);
      }
    }

    @Test
    public void append() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> prev, node = l.link(null, PRIMES[0], null);
      if (output) System.out.println(l.toStateString());
      for (int i = 1; i < PRIMES.length; i++) {
        prev = node;
        node = l.link(prev, PRIMES[i], null);
        if (output) System.out.println(l.toStateString());
        assertEquals(PRIMES[i], (int) node.element);
        assertEquals(i + 1, l.size);
        assertSame(node, prev.next);
        assertNull(node.next);
        assertSame(l.last, node);
      }
    }

    @Test
    public void insert() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> prev, node, next;
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
        assertSame(next, node.next);
      }
    }

  }

  public static class unlink {

    @Test(expected = IllegalArgumentException.class)
    public void fails_remove_null() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      l.unlink(null, null);
    }

    @Test
    public void ultimate() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> node = l.link(null, PRIMES[0], null);
      if (output) System.out.println(l.toStateString());
      int element = l.unlink(null, node);
      if (output) System.out.println(l.toStateString());
      assertEquals(PRIMES[0], element);
      assertEquals(0, l.size);
      assertNull(node.next);
      assertNull(node.element);
      assertNull(l.first);
      assertNull(l.last);
    }

    @Test
    public void first() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>(LISTED_PRIMES);
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList.Node<Integer> oldFirst, newFirst = l.first;
      for (int i = 0; i < PRIMES.length - 1; i++) {
        oldFirst = newFirst;
        newFirst = newFirst != null ? newFirst.next : null;
        int element = l.unlink(null, oldFirst);
        if (output) System.out.println(l.toStateString());
        assertEquals(PRIMES[i], element);
        assertEquals(PRIMES.length - i - 1, l.size);
        assertNull(oldFirst.next);
        assertNull(oldFirst.element);
        assertSame(newFirst, l.first);
      }

      assertSame(l.first, l.last);
    }

    @Test
    public void last() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>(LISTED_PRIMES);
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList.Node<Integer> newLast, oldLast;
      for (int i = PRIMES.length - 1; i > 0; i--) {
        oldLast = l.last;
        newLast = l.getPrevious(oldLast);
        int element = l.unlink(oldLast);
        if (output) System.out.println(l.toStateString());
        assertEquals(PRIMES[i], element);
        assertEquals(i, l.size);
        assertNull(oldLast.next);
        assertNull(oldLast.element);
        assertNull(newLast.next);
        assertSame(newLast, l.last);
        assertNull(l.last.next);
      }

      assertSame(l.first, l.last);
    }

    @Test
    public void extract() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>(LISTED_PRIMES);
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList.Node<Integer> first, second, third;
      for (int i = 1; i < PRIMES.length - 1; i++) {
        first = l.first;
        second = first.next;
        third = second.next;
        int element = l.unlink(first, second);
        if (output) System.out.println(l.toStateString());
        assertEquals(PRIMES[i], element);
        assertEquals(PRIMES.length - i, l.size);
        assertNull(second.next);
        assertNull(second.element);
        assertSame(third, first.next);
      }
    }

  }

  public static class iterator {

    @Test(expected = NoSuchElementException.class)
    public void fails_next() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList<Integer>.SinglyLinkedListIterator it
          = (SinglyLinkedList<Integer>.SinglyLinkedListIterator) l.iterator();
      Integer element = it.next();
    }

    @Test
    public void next() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>(LISTED_PRIMES);
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList<Integer>.SinglyLinkedListIterator it
          = (SinglyLinkedList<Integer>.SinglyLinkedListIterator) l.iterator();
      if (output) System.out.println(it);
      assertNull(it.lastReturned);
      assertSame(l.first, it.next);
      assertEquals(0, it.nextIndex);
      for (int i = 0; i < WRAPPED_PRIMES.length; i++) {
        assertTrue(it.hasNext());
        SinglyLinkedList.Node<Integer> prevNext = it.next;
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
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>(LISTED_PRIMES);
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList<Integer>.SinglyLinkedListIterator it
          = (SinglyLinkedList<Integer>.SinglyLinkedListIterator) l.iterator();
      if (output) System.out.println(it);
      it.remove();
    }

    @Test
    public void remove() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>(LISTED_PRIMES);
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList<Integer>.SinglyLinkedListIterator it
          = (SinglyLinkedList<Integer>.SinglyLinkedListIterator) l.iterator();
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

  public static class getPrevious {

    @Test
    public void empty() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      assertNull(l.getPrevious(null));
    }

    @Test
    public void single_element() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      if (output) System.out.println(l.toStateString());
      assertNull(l.getPrevious(first));
    }

    @Test
    public void two_elements() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      SinglyLinkedList.Node<Integer> second = l.link(first, PRIMES[1], null);
      if (output) System.out.println(l.toStateString());
      assertNull(l.getPrevious(first));
      assertSame(first, l.getPrevious(second));
    }

    @Test
    public void n_elements() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList.Node<Integer> prevLast, last = null;
      for (int i = 0; i < PRIMES.length; i++) {
        prevLast = last;
        last = l.link(prevLast, PRIMES[i], null);
        if (output) System.out.println(l.toStateString());
        assertSame(prevLast, l.getPrevious(last));
      }
    }

  }

  public static class getNode {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      l.getNode(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_low() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      l.getNode(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      l.getNode(1);
    }

    @Test
    public void single_element() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      if (output) System.out.println(l.toStateString());
      assertSame(first, l.getNode(0));
    }

    @Test
    public void two_elements() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      SinglyLinkedList.Node<Integer> second = l.link(first, PRIMES[1], null);
      if (output) System.out.println(l.toStateString());
      assertSame(first, l.getNode(0));
      assertSame(second, l.getNode(1));
    }

    @Test
    public void n_elements() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList.Node<Integer> last = null;
      for (int i = 0; i < PRIMES.length; i++) {
        last = l.link(last, PRIMES[i], null);
        if (output) System.out.println(l.toStateString());
        assertSame(last, l.getNode(i));
      }
    }

  }

  public static class clear {

    @Test
    public void empty() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      if (output) System.out.println(l.toStateString());
      l.clear();
      if (output) System.out.println(l.toStateString());
      assertNull(l.first);
      assertNull(l.last);
      assertEquals(0, l.size);
    }

    @Test
    public void nonempty() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>(LISTED_PRIMES);
      if (output) System.out.println(l.toStateString());
      l.clear();
      if (output) System.out.println(l.toStateString());
      assertNull(l.first);
      assertNull(l.last);
      assertEquals(0, l.size);
    }

  }

  public static class add_int_E {

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
      if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), 0, PRIMES[0]);
      assertSame(l.first, l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertEquals(1, l.size);
      assertNull(l.first.next);
      assertNull(l.last.next);
    }

    @Test
    public void nonempty_insert_at_front() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList.Node<Integer> prevFirst;
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
        assertSame(l.first.next, prevFirst);
      }
    }

    @Test
    public void nonempty_insert_at_back() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList.Node<Integer> prevLast;
      for (int i = 1; i < PRIMES.length; i++) {
        prevLast = l.last;
        l.add(l.size, PRIMES[i]);
        if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), l.size - 1, PRIMES[i]);
        assertNotSame(l.first, l.last);
        assertEquals(PRIMES[i], (int) l.last.element);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(i + 1, l.size);
        assertNull(l.last.next);
        assertSame(prevLast.next, l.last);
      }
    }

    @Test
    public void nonempty_insert_in_between() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      SinglyLinkedList.Node<Integer> second = l.link(first, PRIMES[PRIMES.length - 1], null);
      if (output) System.out.println(l.toStateString());
      SinglyLinkedList.Node<Integer> third;
      for (int i = PRIMES.length - 2; i > 0; i--) {
        third = l.first.next;
        l.add(1, PRIMES[i]);
        if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), 1, PRIMES[i]);
        assertEquals(PRIMES[i], (int) l.first.next.element);
        assertEquals(PRIMES.length - i + 1, l.size);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertNull(l.last.next);
        assertSame(l.first.next.next, third);

        // Test that first and last are still the same, since this is in-between them
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.last.element);
      }
    }

  }

  public static class remove_int {

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
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      l.remove(2);
    }

    @Test
    public void nonempty_last() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
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
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      SinglyLinkedList.Node<Integer> second = l.link(first, PRIMES[1], null);
      if (output) System.out.println(l.toStateString());
      int element = l.remove(0);
      if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 0, element);
      assertEquals(PRIMES[0], element);
      assertEquals(1, l.size);
      assertSame(l.first, l.last);
      assertNotNull(l.first);
      assertNotNull(l.last);
      assertEquals(PRIMES[1], (int) l.first.element);
      assertNull(l.first.next);
      assertNull(l.last.next);
    }

    @Test
    public void nonempty_second_to_last_from_back() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>();
      SinglyLinkedList.Node<Integer> first = l.link(null, PRIMES[0], null);
      SinglyLinkedList.Node<Integer> second = l.link(first, PRIMES[1], null);
      if (output) System.out.println(l.toStateString());
      int element = l.remove(1);
      if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 1, element);
      assertEquals(PRIMES[1], element);
      assertEquals(1, l.size);
      assertSame(l.first, l.last);
      assertNotNull(l.first);
      assertNotNull(l.last);
      assertEquals(PRIMES[0], (int) l.first.element);
      assertNull(l.first.next);
      assertNull(l.last.next);
    }

    @Test
    public void nonempty_remove_from_front() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>(LISTED_PRIMES);
      if (output) System.out.println(l.toStateString());
      for (int i = 0; i < PRIMES.length - 2; i++) {
        int element = l.remove(0);
        if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 0, element);
        assertEquals(PRIMES[i], element);
        assertEquals(PRIMES.length - i - 1, l.size);
        assertEquals(PRIMES[i + 1], (int) l.first.element);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.last.element);
      }
    }

    @Test
    public void nonempty_remove_from_back() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>(LISTED_PRIMES);
      if (output) System.out.println(l.toStateString());
      for (int i = PRIMES.length - 1; i > 1; i--) {
        int index = l.size - 1;
        int last = l.remove(index);
        if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), index, last);
        assertEquals(PRIMES[i], last);
        assertEquals(i, l.size);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(PRIMES[i - 1], (int) l.last.element);
        assertNull(l.last.next);
      }
    }

    @Test
    public void nonempty_remove_in_between() {
      SinglyLinkedList<Integer> l = new SinglyLinkedList<>(LISTED_PRIMES);
      if (output) System.out.println(l.toStateString());
      for (int i = 1; i < PRIMES.length - 2; i++) {
        int nth = l.remove(1);
        if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 1, nth);
        assertEquals(PRIMES[i], nth);
        assertEquals(PRIMES.length - i, l.size);
        assertEquals(PRIMES[0], (int) l.first.element);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.last.element);
        assertEquals(PRIMES[i + 1], (int) l.first.next.element);
      }
    }

  }

  @RunWith(Enclosed.class)
  public static class _Node {

    public static class toString {

      @Test
      public void empty() {
        SinglyLinkedList.Node<Integer> n = new SinglyLinkedList.Node<>(null, null);
        if (output) System.out.println(n.toStateString());
        String toString = n.toString();
        assertNotNull(toString);
        assertFalse(toString.isEmpty());
      }

    }

  }

}

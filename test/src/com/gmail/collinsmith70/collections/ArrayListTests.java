package com.gmail.collinsmith70.collections;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static com.gmail.collinsmith70.collections.TestData.PRIMES;
import static com.gmail.collinsmith70.collections.TestData.WRAPPED_PRIMES;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class ArrayListTests {

  static final boolean output = true;

  public static class _ArrayList {

    @Test
    public void default_constructor() {
      ArrayList<Integer> l = new ArrayList<>();
      if (output) System.out.println(l.toStateString());
      assertEquals(0, l.size);
    }

    @Test
    public void ArrayList_int() {
      ArrayList<Integer> l = new ArrayList<>(1);
      if (output) System.out.println(l.toStateString());
      assertEquals(1, l.elements.length);
      assertEquals(0, l.size);
    }

    @Test
    public void ArrayList_array_of_E() {
      ArrayList<Integer> l = new ArrayList<>(WRAPPED_PRIMES);
      if (output) System.out.println(l.toStateString());
      assertEquals(WRAPPED_PRIMES.length, l.elements.length);
      assertEquals(WRAPPED_PRIMES.length, l.size);
      assertArrayEquals(WRAPPED_PRIMES, l.elements);
      assertNotSame(WRAPPED_PRIMES, l.elements);
    }

  }

  public static class checkAndGrow {

    @Test
    public void doesnt_grow() {
      ArrayList<Integer> l = new ArrayList<>(16);
      if (output) System.out.println(l.toStateString());
      int capacityBefore = l.elements.length;
      l.checkAndGrow(16);
      if (output)
        System.out.printf("%s checkAndGrow(%d) capacity %d -> %d%n",
            l.toStateString(), 16, capacityBefore, l.elements.length);
      assertTrue(l.elements.length >= 16);
    }

    @Test
    public void grow() {
      ArrayList<Integer> l = new ArrayList<>(15);
      if (output) System.out.println(l.toStateString());
      int capacityBefore = l.elements.length;
      l.checkAndGrow(16);
      if (output)
        System.out.printf("%s checkAndGrow(%d) capacity %d -> %d%n",
            l.toStateString(), 16, capacityBefore, l.elements.length);
      assertTrue(l.elements.length >= 16);
    }

    @Test
    public void nonempty_grow() {
      ArrayList<Integer> l = new ArrayList<>(WRAPPED_PRIMES);
      if (output) System.out.println(l.toStateString());
      int capacityBefore = l.elements.length;
      l.checkAndGrow(16);
      if (output)
        System.out.printf("%s checkAndGrow(%d) capacity %d -> %d%n",
            l.toStateString(), 16, capacityBefore, l.elements.length);
      assertTrue(l.elements.length >= (WRAPPED_PRIMES.length + 16));
    }

    @Test
    public void recursive_grow() {
      ArrayList<Integer> l = new ArrayList<>(0);
      if (output) System.out.println(l.toStateString());
      int capacityBefore = l.elements.length;
      l.checkAndGrow(16);
      if (output)
        System.out.printf("%s checkAndGrow(%d) capacity %d -> %d%n",
            l.toStateString(), 16, capacityBefore, l.elements.length);
      assertTrue(l.elements.length >= 16);
    }

  }

  public static class clear {

    @Test
    public void empty() {
      ArrayList<Integer> l = new ArrayList<>();
      if (output) System.out.println(l.toStateString());
      l.clear();
      if (output) System.out.println(l.toStateString());
      assertEquals(0, l.size);
    }

    @Test
    public void nonempty() {
      ArrayList<Integer> l = new ArrayList<>(WRAPPED_PRIMES);
      if (output) System.out.println(l.toStateString());
      l.clear();
      if (output) System.out.println(l.toStateString());
      assertEquals(0, l.size);
    }

  }

  public static class size {

    @Test
    public void incrementing() {
      ArrayList<Integer> l = new ArrayList<>();
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
      ArrayList<Integer> l = new ArrayList<>(WRAPPED_PRIMES);
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
      ArrayList<Integer> l = new ArrayList<>();
      if (output) System.out.println(l.toStateString() + " isEmpty()");
      assertTrue(l.isEmpty());
      assertEquals(l.size == 0, l.isEmpty());
    }

    @Test
    public void _false() {
      ArrayList<Integer> l = new ArrayList<>();
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
      ArrayList<Integer> l = new ArrayList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
        if (output) System.out.println(l.toStateString() + " contains " + prime);
        assertTrue(l.contains(prime));
      }
    }

    @Test
    public void _false() {
      ArrayList<Integer> l = new ArrayList<>();
      for (int prime : PRIMES) {
        if (output) System.out.println(l.toStateString() + " does not contain " + prime);
        assertFalse(l.contains(prime));
      }
    }

    @Test
    public void true_contains_null() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(null);
      if (output) System.out.println(l.toStateString() + " contains " + null);
      assertTrue(l.contains(null));
    }

  }

  public static class get {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty() {
      ArrayList<Integer> l = new ArrayList<>();
      l.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_low() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      l.get(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      l.get(1);
    }

    @Test
    public void incrementing() {
      ArrayList<Integer> l = new ArrayList<>();
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
      ArrayList<Integer> l = new ArrayList<>();
      l.set(0, PRIMES[0]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_low() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      l.set(-1, PRIMES[1]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      l.set(1, PRIMES[1]);
    }

    @Test
    public void incrementing() {
      ArrayList<Integer> l = new ArrayList<>(WRAPPED_PRIMES);
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
      ArrayList<Integer> l = new ArrayList<>();
      if (output) System.out.println(l.toStateString());
      l.add(PRIMES[0]);
      if (output) System.out.printf("%s add(%d)%n", l.toStateString(), PRIMES[0]);
      assertEquals(PRIMES[0], (int) l.elements[0]);
      assertEquals(1, l.size);
    }

    @Test
    public void second() {
      ArrayList<Integer> l = new ArrayList<>();
      l.add(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      l.add(PRIMES[1]);
      if (output) System.out.printf("%s add(%d)%n", l.toStateString(), PRIMES[1]);
      assertEquals(PRIMES[0], (int) l.elements[0]);
      assertEquals(PRIMES[1], (int) l.elements[1]);
      assertEquals(2, l.size);
    }

    @Test
    public void nth() {
      ArrayList<Integer> l = new ArrayList<>();
      l.add(PRIMES[0]);
      l.add(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      for (int i = 2; i < PRIMES.length; i++) {
        l.add(PRIMES[i]);
        if (output) System.out.printf("%s add(%d)%n", l.toStateString(), PRIMES[i]);
        assertEquals(PRIMES[i], (int) l.elements[l.size - 1]);
        assertEquals(PRIMES[0], (int) l.elements[0]);
        assertEquals(i + 1, l.size);
      }
    }

  }

  public static class add_int_E {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty_low() {
      ArrayList<Integer> l = new ArrayList<>();
      l.add(-1, PRIMES[0]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty_high() {
      ArrayList<Integer> l = new ArrayList<>();
      l.add(1, PRIMES[0]);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      l.add(2, PRIMES[1]);
    }

    @Test
    public void empty_first() {
      ArrayList<Integer> l = new ArrayList<>();
      l.add(0, PRIMES[0]);
      if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), 0, PRIMES[0]);
      assertEquals(PRIMES[0], (int) l.elements[0]);
      assertEquals(1, l.size);
    }

    @Test
    public void nonempty_insert_at_front() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      for (int i = 1; i < PRIMES.length; i++) {
        l.add(0, PRIMES[i]);
        if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), 0, PRIMES[i]);
        assertEquals(PRIMES[i], (int) l.elements[0]);
        assertEquals(PRIMES[0], (int) l.elements[l.size - 1]);
        assertEquals(i + 1, l.size);
      }
    }

    @Test
    public void nonempty_insert_at_back() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      for (int i = 1; i < PRIMES.length; i++) {
        l.add(l.size, PRIMES[i]);
        if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), l.size - 1, PRIMES[i]);
        assertEquals(PRIMES[i], (int) l.elements[i]);
        assertEquals(PRIMES[0], (int) l.elements[0]);
        assertEquals(i + 1, l.size);
      }
    }

    @Test
    public void nonempty_insert_in_between() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[PRIMES.length - 1]);
      if (output) System.out.println(l.toStateString());
      for (int i = PRIMES.length - 2; i > 0; i--) {
        l.add(1, PRIMES[i]);
        if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), 1, PRIMES[i]);
        assertEquals(PRIMES[i], (int) l.get(1));
        assertEquals(PRIMES.length - i + 1, l.size);
        assertEquals(PRIMES[0], (int) l.get(0));
        assertEquals(PRIMES[i + 1], (int) l.get(2));

        // Test that first and last are still the same, since this is in-between them
        assertEquals(PRIMES[0], (int) l.elements[0]);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.elements[l.size - 1]);
      }
    }

  }

  public static class addFirst {

    @Test
    public void first() {
      ArrayList<Integer> l = new ArrayList<>();
      if (output) System.out.println(l.toStateString());
      l.addFirst(PRIMES[0]);
      if (output) System.out.printf("%s addFirst(%d)%n", l.toStateString(), PRIMES[0]);
      assertEquals(PRIMES[0], (int) l.elements[0]);
      assertEquals(1, l.size);
    }

    @Test
    public void second() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addFirst(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      l.addFirst(PRIMES[1]);
      if (output) System.out.printf("%s addFirst(%d)%n", l.toStateString(), PRIMES[1]);
      assertEquals(PRIMES[1], (int) l.elements[0]);
      assertEquals(PRIMES[0], (int) l.elements[l.size - 1]);
      assertEquals(2, l.size);
    }

    @Test
    public void nth() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addFirst(PRIMES[0]);
      l.addFirst(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      for (int i = 2; i < PRIMES.length; i++) {
        l.addFirst(PRIMES[i]);
        if (output) System.out.printf("%s addFirst(%d)%n", l.toStateString(), PRIMES[i]);
        assertEquals(PRIMES[i], (int) l.elements[0]);
        assertEquals(PRIMES[0], (int) l.elements[l.size - 1]);
        assertEquals(i + 1, l.size);
      }
    }

  }

  public static class addLast {

    @Test
    public void first() {
      ArrayList<Integer> l = new ArrayList<>();
      if (output) System.out.println(l.toStateString());
      l.addLast(PRIMES[0]);
      if (output) System.out.printf("%s addLast(%d)%n", l.toStateString(), PRIMES[0]);
      assertEquals(PRIMES[0], (int) l.elements[0]);
      assertEquals(1, l.size);
    }

    @Test
    public void second() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      l.addLast(PRIMES[1]);
      if (output) System.out.printf("%s addLast(%d)%n", l.toStateString(), PRIMES[1]);
      assertEquals(PRIMES[0], (int) l.elements[0]);
      assertEquals(PRIMES[1], (int) l.elements[l.size - 1]);
      assertEquals(2, l.size);
    }

    @Test
    public void nth() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      for (int i = 2; i < PRIMES.length; i++) {
        l.addLast(PRIMES[i]);
        if (output) System.out.printf("%s addLast(%d)%n", l.toStateString(), PRIMES[i]);
        assertEquals(PRIMES[i], (int) l.elements[l.size - 1]);
        assertEquals(PRIMES[0], (int) l.elements[0]);
        assertEquals(i + 1, l.size);
      }
    }

  }

  public static class remove_Object {

    @Test
    public void _false() {
      ArrayList<Integer> l = new ArrayList<>();
      if (output) System.out.println(l.toStateString());
      boolean removed = l.remove((Integer) PRIMES[0]);
      if (output) System.out.printf("%s remove((Integer)%d)%n", l.toStateString(), PRIMES[0]);
      assertFalse(removed);
    }

    @Test
    public void _true() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      boolean removed = l.remove((Integer) PRIMES[0]);
      if (output) System.out.printf("%s remove((Integer)%d)%n", l.toStateString(), PRIMES[0]);
      assertTrue(removed);
    }

    @Test
    public void true_removed_null() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(null);
      if (output) System.out.println(l.toStateString());
      boolean removed = l.remove(null);
      if (output) System.out.printf("%s remove(%h)%n", l.toStateString(), null);
      assertTrue(removed);
    }

  }

  public static class remove_int {

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty_low() {
      ArrayList<Integer> l = new ArrayList<>();
      l.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_empty_high() {
      ArrayList<Integer> l = new ArrayList<>();
      l.remove(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void fails_nonempty_high() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      l.remove(2);
    }

    @Test
    public void nonempty_last() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      int last = l.remove(0);
      if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 0, last);
      assertEquals(PRIMES[0], last);
      assertEquals(0, l.size);
    }

    @Test
    public void nonempty_second_to_last_from_front() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      int first = l.remove(0);
      if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 0, first);
      assertEquals(PRIMES[0], first);
      assertEquals(1, l.size);
      assertEquals(PRIMES[1], (int) l.elements[0]);
    }

    @Test
    public void nonempty_second_to_last_from_back() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      l.addLast(PRIMES[1]);
      if (output) System.out.println(l.toStateString());
      int last = l.remove(1);
      if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 1, last);
      assertEquals(PRIMES[1], last);
      assertEquals(1, l.size);
      assertEquals(PRIMES[0], (int) l.elements[0]);
    }

    @Test
    public void nonempty_remove_from_front() {
      ArrayList<Integer> l = new ArrayList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      if (output) System.out.println(l.toStateString());
      for (int i = 0; i < PRIMES.length - 1; i++) {
        int first = l.remove(0);
        if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 0, first);
        assertEquals(PRIMES[i], first);
        assertEquals(PRIMES.length - i - 1, l.size);
        assertEquals(PRIMES[i + 1], (int) l.elements[0]);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.elements[l.size - 1]);
      }
    }

    @Test
    public void nonempty_remove_from_back() {
      ArrayList<Integer> l = new ArrayList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      if (output) System.out.println(l.toStateString());
      for (int i = PRIMES.length - 1; i > 0; i--) {
        int index = l.size - 1;
        int last = l.remove(index);
        if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), index, last);
        assertEquals(PRIMES[i], last);
        assertEquals(i, l.size);
        assertEquals(PRIMES[0], (int) l.elements[0]);
        assertEquals(PRIMES[i - 1], (int) l.elements[l.size - 1]);
      }
    }

    @Test
    public void nonempty_remove_in_between() {
      ArrayList<Integer> l = new ArrayList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      if (output) System.out.println(l.toStateString());
      for (int i = 1; i < PRIMES.length - 1; i++) {
        int nth = l.remove(1);
        if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 1, nth);
        assertEquals(PRIMES[i], nth);
        assertEquals(PRIMES.length - i, l.size);
        assertEquals(PRIMES[0], (int) l.elements[0]);
        assertEquals(PRIMES[PRIMES.length - 1], (int) l.elements[l.size - 1]);
      }
    }

  }

  public static class removeFirst {

    @Test(expected = NoSuchElementException.class)
    public void fails_empty() {
      ArrayList<Integer> l = new ArrayList<>();
      l.removeFirst();
    }

    @Test
    public void last() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      int first = l.removeFirst();
      if (output) System.out.printf("%s removeFirst()=%d%n", l.toStateString(), first);
      assertEquals(0, l.size);
      assertEquals(PRIMES[0], first);
    }

    @Test
    public void nth() {
      ArrayList<Integer> l = new ArrayList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      if (output) System.out.println(l.toStateString());
      for (int i = 0; i < PRIMES.length - 2; i++) {
        int first = l.removeFirst();
        if (output) System.out.printf("%s removeFirst()=%d%n", l.toStateString(), first);
        assertEquals(PRIMES[i], first);
        assertEquals(PRIMES.length - i - 1, l.size);
        assertEquals(PRIMES[i + 1], (int) l.elements[0]);
      }
    }

  }

  public static class removeLast {

    @Test(expected = NoSuchElementException.class)
    public void fails_empty() {
      ArrayList<Integer> l = new ArrayList<>();
      l.removeLast();
    }

    @Test
    public void last() {
      ArrayList<Integer> l = new ArrayList<>();
      l.addLast(PRIMES[0]);
      if (output) System.out.println(l.toStateString());
      int last = l.removeLast();
      if (output) System.out.printf("%s removeLast()=%d%n", l.toStateString(), last);
      assertEquals(0, l.size);
      assertEquals(PRIMES[0], last);
    }

    @Test
    public void nth() {
      ArrayList<Integer> l = new ArrayList<>();
      for (int prime : PRIMES) {
        l.addLast(prime);
      }

      if (output) System.out.println(l.toStateString());
      for (int i = PRIMES.length - 1; i > 0; i--) {
        int last = l.removeLast();
        if (output) System.out.printf("%s removeLast()=%d%n", l.toStateString(), last);
        assertEquals(PRIMES[i], last);
        assertEquals(i, l.size);
        assertEquals(PRIMES[i - 1], (int) l.elements[l.size - 1]);
      }
    }

  }

  public static class toArray {

    @Test
    public void empty() {
      ArrayList<Integer> l = new ArrayList<>();
      if (output) System.out.println(l.toStateString());
      Integer[] array = l.toArray(new Integer[l.size]);
      if (output) System.out.printf("%s toArray=%s%n", l.toStateString(), Arrays.toString(array));
      assertNotNull(array);
      assertEquals(0, array.length);
    }

    @Test
    public void single() {
      ArrayList<Integer> l = new ArrayList<>();
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
      ArrayList<Integer> l = new ArrayList<>();
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

}

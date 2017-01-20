package com.gmail.collinsmith70.collections;

import org.junit.Test;

import java.util.NoSuchElementException;

import static com.gmail.collinsmith70.collections.TestData.LISTED_PRIMES;
import static com.gmail.collinsmith70.collections.TestData.PRIMES;
import static com.gmail.collinsmith70.collections.TestData.WRAPPED_PRIMES;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class ArrayListTests extends ListTests {

  static final boolean output = true;

  public ArrayListTests() {
    super(ArrayList.class);
  }

  @Test
  public void ArrayList() {
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

  @Test
  public void checkAndGrow_doesnt_grow() {
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
  public void checkAndGrow_grow() {
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
  public void checkAndGrow_nonempty_grow() {
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
  public void checkAndGrow_recursive_grow() {
    ArrayList<Integer> l = new ArrayList<>(0);
    if (output) System.out.println(l.toStateString());
    int capacityBefore = l.elements.length;
    l.checkAndGrow(16);
    if (output)
      System.out.printf("%s checkAndGrow(%d) capacity %d -> %d%n",
          l.toStateString(), 16, capacityBefore, l.elements.length);
    assertTrue(l.elements.length >= 16);
  }

  @Test(expected = NoSuchElementException.class)
  public void iterator_fails_next() {
    ArrayList<Integer> l = new ArrayList<>();
    if (output) System.out.println(l.toStateString());
    ArrayList<Integer>.ArrayListIterator it
        = (ArrayList<Integer>.ArrayListIterator) l.iterator();
    Integer element = it.next();
  }

  @Test
  public void iterator_next() {
    ArrayList<Integer> l = new ArrayList<>(LISTED_PRIMES);
    if (output) System.out.println(l.toStateString());
    ArrayList<Integer>.ArrayListIterator it
        = (ArrayList<Integer>.ArrayListIterator) l.iterator();
    if (output) System.out.println(it);
    assertNull(it.lastReturned);
    assertEquals(0, it.nextIndex);
    for (int i = 0; i < WRAPPED_PRIMES.length; i++) {
      assertTrue(it.hasNext());
      Integer element = it.next();
      if (output) System.out.println(it);
      assertSame(WRAPPED_PRIMES[i], element);
      assertEquals(i + 1, it.nextIndex);
    }
  }

  @Test(expected = IllegalStateException.class)
  public void iterator_fails_remove() {
    ArrayList<Integer> l = new ArrayList<>(LISTED_PRIMES);
    if (output) System.out.println(l.toStateString());
    ArrayList<Integer>.ArrayListIterator it
        = (ArrayList<Integer>.ArrayListIterator) l.iterator();
    if (output) System.out.println(it);
    it.remove();
  }

  @Test
  public void iterator_remove() {
    ArrayList<Integer> l = new ArrayList<>(LISTED_PRIMES);
    if (output) System.out.println(l.toStateString());
    ArrayList<Integer>.ArrayListIterator it
        = (ArrayList<Integer>.ArrayListIterator) l.iterator();
    if (output) System.out.println(it);
    assertNull(it.lastReturned);
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

  @Test(expected = IndexOutOfBoundsException.class)
  public void add_int_E_fails_empty_low() {
    ArrayList<Integer> l = new ArrayList<>();
    l.add(-1, PRIMES[0]);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void add_int_E_fails_empty_high() {
    ArrayList<Integer> l = new ArrayList<>();
    l.add(1, PRIMES[0]);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void add_int_E_fails_nonempty_high() {
    ArrayList<Integer> l = new ArrayList<>();
    l.addLast(PRIMES[0]);
    l.add(2, PRIMES[1]);
  }

  @Test
  public void add_int_E_empty_first() {
    ArrayList<Integer> l = new ArrayList<>();
    l.add(0, PRIMES[0]);
    if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), 0, PRIMES[0]);
    assertEquals(PRIMES[0], (int) l.elements[0]);
    assertEquals(1, l.size);
  }

  @Test
  public void add_int_E_second() {
    ArrayList<Integer> l = new ArrayList<>();
    l.add(0, PRIMES[0]);
    if (output) System.out.println(l.toStateString());
    l.add(1, PRIMES[1]);
    if (output) System.out.printf("%s add(%d, %d)%n", l.toStateString(), 0, PRIMES[0]);
    assertEquals(PRIMES[0], (int) l.elements[0]);
    assertEquals(PRIMES[1], (int) l.elements[1]);
    assertEquals(2, l.size);
  }

  @Test
  public void add_int_E_nonempty_insert_at_front() {
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
  public void add_int_E_nonempty_insert_at_back() {
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
  public void add_int_E_nonempty_insert_in_between() {
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

  @Test(expected = IndexOutOfBoundsException.class)
  public void remove_int_fails_empty_low() {
    ArrayList<Integer> l = new ArrayList<>();
    l.remove(-1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void remove_int_fails_empty_high() {
    ArrayList<Integer> l = new ArrayList<>();
    l.remove(1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void remove_int_fails_nonempty_high() {
    ArrayList<Integer> l = new ArrayList<>();
    l.addLast(PRIMES[0]);
    l.remove(2);
  }

  @Test
  public void remove_int_nonempty_last() {
    ArrayList<Integer> l = new ArrayList<>();
    l.addLast(PRIMES[0]);
    if (output) System.out.println(l.toStateString());
    int last = l.remove(0);
    if (output) System.out.printf("%s remove(%d)=%d%n", l.toStateString(), 0, last);
    assertEquals(PRIMES[0], last);
    assertEquals(0, l.size);
  }

  @Test
  public void remove_int_nonempty_second_to_last_from_front() {
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
  public void remove_int_nonempty_second_to_last_from_back() {
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
  public void remove_int_nonempty_remove_from_front() {
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
  public void remove_int_nonempty_remove_from_back() {
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
  public void remove_int_nonempty_remove_in_between() {
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

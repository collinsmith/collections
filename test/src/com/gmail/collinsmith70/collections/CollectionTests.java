package com.gmail.collinsmith70.collections;

import org.junit.Test;

import java.util.Arrays;

import static com.gmail.collinsmith70.collections.TestData.LISTED_PRIMES;
import static com.gmail.collinsmith70.collections.TestData.WRAPPED_PRIMES;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class CollectionTests {

  static final boolean output = true;

  final Class<? extends Collection> clazz;

  public CollectionTests(Class<? extends Collection> clazz) {
    this.clazz = clazz;
  }

  public Collection newInstance() {
    Collection c = null;
    try {
      c = clazz.newInstance();
    } catch (InstantiationException e) {
      fail(e.getMessage());
    } catch (IllegalAccessException e) {
      fail(e.getMessage());
    } finally {
      return c;
    }
  }

  public void outputCurrentState(Collection instance) {
    System.out.printf("%s:%s%n", instance.getClass().getSimpleName(), instance);
  }

  @Test
  public void clear_empty() {
    Collection c = newInstance();
    c.clear();
    if (output) outputCurrentState(c);
    assertEquals(0, c.size());
    assertTrue(c.isEmpty());
  }

  @Test
  public void clear_nonempty() {
    Collection c = newInstance();
    c.addAll(LISTED_PRIMES);
    if (output) outputCurrentState(c);
    c.clear();
    if (output) outputCurrentState(c);
    assertEquals(0, c.size());
    assertTrue(c.isEmpty());
  }

  @Test
  public void contains_null() {
    Collection c = newInstance();
    if (output) outputCurrentState(c);
    c.add(null);
    if (output) outputCurrentState(c);
    assertTrue(c.contains(null));
  }

  @Test
  public void add() {
    Collection c = newInstance();
    int sizeBefore = c.size();
    if (output) outputCurrentState(c);
    c.add(WRAPPED_PRIMES[0]);
    if (output) outputCurrentState(c);
    assertTrue(c.contains(WRAPPED_PRIMES[0]));
    assertEquals(sizeBefore + 1, c.size());
  }

  @Test
  public void addAll() {
    Collection c = newInstance();
    int sizeBefore = c.size();
    if (output) outputCurrentState(c);
    c.addAll(LISTED_PRIMES);
    if (output) outputCurrentState(c);
    for (Integer prime : LISTED_PRIMES) {
      assertTrue(c.contains(prime));
    }

    assertEquals(sizeBefore + LISTED_PRIMES.size(), c.size());
  }

  @Test
  public void remove_true() {
    Collection c = newInstance();
    c.add(WRAPPED_PRIMES[0]);
    if (output) outputCurrentState(c);
    int sizeBefore = c.size();
    boolean removed = c.remove(WRAPPED_PRIMES[0]);
    if (output) outputCurrentState(c);
    assertTrue(removed);
    assertFalse(c.contains(WRAPPED_PRIMES[0]));
    assertEquals(sizeBefore - 1, c.size());
  }

  @Test
  public void remove_false() {
    Collection c = newInstance();
    if (output) outputCurrentState(c);
    int sizeBefore = c.size();
    boolean removed = c.remove(WRAPPED_PRIMES[0]);
    if (output) outputCurrentState(c);
    assertFalse(removed);
    assertFalse(c.contains(WRAPPED_PRIMES[0]));
    assertEquals(sizeBefore, c.size());
  }

  @Test
  public void remove_null() {
    Collection c = newInstance();
    c.add(null);
    if (output) outputCurrentState(c);
    int sizeBefore = c.size();
    boolean removed = c.remove(null);
    if (output) outputCurrentState(c);
    assertTrue(removed);
    assertFalse(c.contains(null));
    assertEquals(sizeBefore - 1, c.size());
  }

  @Test
  public void removeAll() {
    Collection c = newInstance();
    c.addAll(LISTED_PRIMES);
    if (output) outputCurrentState(c);
    int sizeBefore = c.size();
    c.removeAll(LISTED_PRIMES);
    if (output) outputCurrentState(c);
    for (Integer prime : LISTED_PRIMES) {
      assertFalse(c.contains(prime));
    }

    assertEquals(sizeBefore - LISTED_PRIMES.size(), c.size());
  }

  @Test
  public void toArray_same() {
    Collection c = newInstance();
    c.addAll(LISTED_PRIMES);
    if (output) outputCurrentState(c);
    Object[] existing = new Object[c.size()];
    Object[] array = c.toArray(existing);
    if (output) System.out.println(Arrays.toString(array));
    assertArrayEquals(WRAPPED_PRIMES, array);
    assertSame(existing, array);
  }

  @Test
  public void toArray_smaller() {
    Collection c = newInstance();
    c.addAll(LISTED_PRIMES);
    if (output) outputCurrentState(c);
    Object[] existing = new Object[0];
    Object[] array = c.toArray(existing);
    if (output) System.out.println(Arrays.toString(array));
    assertArrayEquals(WRAPPED_PRIMES, array);
    assertNotSame(existing, array);
  }

  @Test
  public void toArray_larger() {
    Collection c = newInstance();
    c.addAll(LISTED_PRIMES);
    if (output) outputCurrentState(c);
    Object[] existing = new Object[c.size() + 1];
    Object[] array = c.toArray(existing);
    if (output) System.out.println(Arrays.toString(array));
    for (int i = 0; i < WRAPPED_PRIMES.length; i++) {
      assertSame(WRAPPED_PRIMES[i], array[i]);
    }

    assertNull(array[c.size()]);
    assertSame(existing, array);
  }

  @Test
  public void toString_notnull_nonempty() {
    Collection c = newInstance();
    String result = c.toString();
    if (output) outputCurrentState(c);
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

}

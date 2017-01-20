package com.gmail.collinsmith70.collections;

import com.google.common.collect.Lists;

import org.junit.Test;

import java.util.NoSuchElementException;

import static com.gmail.collinsmith70.collections.TestData.LISTED_PRIMES;
import static com.gmail.collinsmith70.collections.TestData.WRAPPED_PRIMES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

public class ListTests extends CollectionTests {

  static final boolean output = true;

  final Class<? extends List> clazz;

  public ListTests(Class<? extends List> clazz) {
    super(clazz);
    this.clazz = clazz;
  }

  public List newInstance() {
    List l = null;
    try {
      l = clazz.newInstance();
    } catch (InstantiationException e) {
      fail(e.getMessage());
    } catch (IllegalAccessException e) {
      fail(e.getMessage());
    } finally {
      return l;
    }
  }

  public void outputCurrentState(List instance) {
    System.out.printf("%s:%s%n", instance.getClass().getSimpleName(), instance);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void get_fails_low() {
    List l = newInstance();
    if (output) outputCurrentState(l);
    l.get(-1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void get_fails_high() {
    List l = newInstance();
    if (output) outputCurrentState(l);
    l.get(l.size());
  }

  @Test
  public void get() {
    List l = newInstance();
    l.addAll(LISTED_PRIMES);
    if (output) outputCurrentState(l);
    for (int i = 0; i < WRAPPED_PRIMES.length; i++)  {
      Object element = l.get(i);
      if (output) System.out.printf("get(%d)=%s%n", i, element);
      assertSame(WRAPPED_PRIMES[i], element);
    }
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void set_fails_low() {
    List l = newInstance();
    if (output) outputCurrentState(l);
    l.set(-1, WRAPPED_PRIMES[0]);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void set_fails_high() {
    List l = newInstance();
    if (output) outputCurrentState(l);
    l.set(l.size(), WRAPPED_PRIMES[0]);
  }

  @Test
  public void set() {
    List l = newInstance();
    l.addAll(LISTED_PRIMES);
    if (output) outputCurrentState(l);
    final Integer replacement = Integer.valueOf(0);
    for (int i = 0; i < WRAPPED_PRIMES.length; i++) {
      l.set(i, replacement);
      Object element = l.get(i);
      if (output) outputCurrentState(l);
      assertSame(replacement, element);
    }
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void add_fails_low() {
    List l = newInstance();
    if (output) outputCurrentState(l);
    l.add(-1, WRAPPED_PRIMES[0]);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void add_fails_high() {
    List l = newInstance();
    if (output) outputCurrentState(l);
    l.add(l.size() + 1, WRAPPED_PRIMES[0]);
  }

  @Test
  public void add() {
    List l = newInstance();
    if (output) outputCurrentState(l);
    for (int i = 0; i < WRAPPED_PRIMES.length; i++) {
      int sizeBefore = l.size();
      l.add(i, WRAPPED_PRIMES[i]);
      Object element = l.get(i);
      if (output) outputCurrentState(l);
      assertEquals(sizeBefore + 1, l.size());
      assertSame(WRAPPED_PRIMES[i], element);
    }
  }

  @Test
  public void addFirst() {
    List l = newInstance();
    if (output) outputCurrentState(l);
    for (Integer prime : WRAPPED_PRIMES) {
      int sizeBefore = l.size();
      l.addFirst(prime);
      if (output) outputCurrentState(l);
      Object element = l.get(0);
      assertEquals(sizeBefore + 1, l.size());
      assertSame(prime, element);
    }
  }

  @Test
  public void addLast() {
    List l = newInstance();
    if (output) outputCurrentState(l);
    for (Integer prime : WRAPPED_PRIMES) {
      int sizeBefore = l.size();
      l.addLast(prime);
      if (output) outputCurrentState(l);
      Object element = l.get(sizeBefore);
      assertEquals(sizeBefore + 1, l.size());
      assertSame(prime, element);
    }
  }

  @Test(expected = NoSuchElementException.class)
  public void removeFirst_fails_empty() {
    List l = newInstance();
    if (output) outputCurrentState(l);
    l.removeFirst();
  }

  @Test
  public void removeFirst() {
    List l = newInstance();
    l.addAll(LISTED_PRIMES);
    if (output) outputCurrentState(l);
    for (Integer prime : LISTED_PRIMES) {
      int sizeBefore = l.size();
      Object element = l.removeFirst();
      if (output) outputCurrentState(l);
      assertEquals(sizeBefore - 1, l.size());
      assertEquals(l.size() == 0, l.isEmpty());
      assertSame(prime, element);
    }
  }

  @Test(expected = NoSuchElementException.class)
  public void removeLast_fails_empty() {
    List l = newInstance();
    if (output) outputCurrentState(l);
    l.removeLast();
  }

  @Test
  public void removeLast() {
    List l = newInstance();
    l.addAll(LISTED_PRIMES);
    if (output) outputCurrentState(l);
    for (Integer prime : Lists.reverse(LISTED_PRIMES)) {
      int sizeBefore = l.size();
      Object element = l.removeLast();
      if (output) outputCurrentState(l);
      assertEquals(sizeBefore - 1, l.size());
      assertEquals(l.size() == 0, l.isEmpty());
      assertSame(prime, element);
    }
  }

}

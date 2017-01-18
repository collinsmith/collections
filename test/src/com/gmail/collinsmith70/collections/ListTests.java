package com.gmail.collinsmith70.collections;

import com.google.common.collect.Lists;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.NoSuchElementException;

import static com.gmail.collinsmith70.collections.TestData.LISTED_PRIMES;
import static com.gmail.collinsmith70.collections.TestData.WRAPPED_PRIMES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

@RunWith(Parameterized.class)
public class ListTests {

  static final boolean output = true;

  @Parameterized.Parameters(name = "{0}")
  public static java.util.Collection<Object[]> data() {
    return Arrays.asList(
        new Object[]{ArrayList.class.getSimpleName(), new ArrayList<Integer>()},
        new Object[]{SinglyLinkedList.class.getSimpleName(), new SinglyLinkedList<Integer>()},
        new Object[]{DoublyLinkedList.class.getSimpleName(), new DoublyLinkedList<Integer>()}
    );
  }

  List<Integer> list;

  public ListTests(String name, List<Integer> list) {
    this.list = list;
  }

  public void outputCurrentState() {
    System.out.printf("%s:%s%n", list.getClass().getSimpleName(), list);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void get_fails_low() {
    list.clear();
    if (output) outputCurrentState();
    list.get(-1);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void get_fails_high() {
    list.clear();
    if (output) outputCurrentState();
    list.get(list.size());
  }

  @Test
  public void get() {
    list.clear();
    list.addAll(LISTED_PRIMES);
    if (output) outputCurrentState();
    for (int i = 0; i < WRAPPED_PRIMES.length; i++)  {
      Integer element = list.get(i);
      if (output) System.out.printf("get(%d)=%s%n", i, element);
      assertSame(WRAPPED_PRIMES[i], element);
    }
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void set_fails_low() {
    list.clear();
    if (output) outputCurrentState();
    list.set(-1, WRAPPED_PRIMES[0]);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void set_fails_high() {
    list.clear();
    if (output) outputCurrentState();
    list.set(list.size(), WRAPPED_PRIMES[0]);
  }

  @Test
  public void set() {
    list.clear();
    list.addAll(LISTED_PRIMES);
    if (output) outputCurrentState();
    final Integer replacement = Integer.valueOf(0);
    for (int i = 0; i < WRAPPED_PRIMES.length; i++) {
      list.set(i, replacement);
      Integer element = list.get(i);
      if (output) outputCurrentState();
      assertSame(replacement, element);
    }
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void add_fails_low() {
    list.clear();
    if (output) outputCurrentState();
    list.add(-1, WRAPPED_PRIMES[0]);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void add_fails_high() {
    list.clear();
    if (output) outputCurrentState();
    list.add(list.size() + 1, WRAPPED_PRIMES[0]);
  }

  @Test
  public void add() {
    list.clear();
    if (output) outputCurrentState();
    for (int i = 0; i < WRAPPED_PRIMES.length; i++) {
      int sizeBefore = list.size();
      list.add(i, WRAPPED_PRIMES[i]);
      Integer element = list.get(i);
      if (output) outputCurrentState();
      assertEquals(sizeBefore + 1, list.size());
      assertSame(WRAPPED_PRIMES[i], element);
    }
  }

  @Test
  public void addFirst() {
    list.clear();
    if (output) outputCurrentState();
    for (Integer prime : WRAPPED_PRIMES) {
      int sizeBefore = list.size();
      list.addFirst(prime);
      if (output) outputCurrentState();
      Integer element = list.get(0);
      assertEquals(sizeBefore + 1, list.size());
      assertSame(prime, element);
    }
  }

  @Test
  public void addLast() {
    list.clear();
    if (output) outputCurrentState();
    for (Integer prime : WRAPPED_PRIMES) {
      int sizeBefore = list.size();
      list.addLast(prime);
      if (output) outputCurrentState();
      Integer element = list.get(sizeBefore);
      assertEquals(sizeBefore + 1, list.size());
      assertSame(prime, element);
    }
  }

  @Test(expected = NoSuchElementException.class)
  public void removeFirst_fails_empty() {
    list.clear();
    if (output) outputCurrentState();
    list.removeFirst();
  }

  @Test
  public void removeFirst() {
    list.clear();
    list.addAll(LISTED_PRIMES);
    if (output) outputCurrentState();
    for (Integer prime : LISTED_PRIMES) {
      int sizeBefore = list.size();
      Integer element = list.removeFirst();
      if (output) outputCurrentState();
      assertEquals(sizeBefore - 1, list.size());
      assertEquals(list.size() == 0, list.isEmpty());
      assertSame(prime, element);
    }
  }

  @Test(expected = NoSuchElementException.class)
  public void removeLast_fails_empty() {
    list.clear();
    if (output) outputCurrentState();
    list.removeLast();
  }

  @Test
  public void removeLast() {
    list.clear();
    list.addAll(LISTED_PRIMES);
    if (output) outputCurrentState();
    for (Integer prime : Lists.reverse(LISTED_PRIMES)) {
      int sizeBefore = list.size();
      Integer element = list.removeLast();
      if (output) outputCurrentState();
      assertEquals(sizeBefore - 1, list.size());
      assertEquals(list.size() == 0, list.isEmpty());
      assertSame(prime, element);
    }
  }

}

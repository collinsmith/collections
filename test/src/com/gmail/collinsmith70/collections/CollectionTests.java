package com.gmail.collinsmith70.collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static com.gmail.collinsmith70.collections.TestData.LISTED_PRIMES;
import static com.gmail.collinsmith70.collections.TestData.PRIMES;
import static com.gmail.collinsmith70.collections.TestData.WRAPPED_PRIMES;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class CollectionTests {

  static final boolean output = true;

  @Parameterized.Parameters(name = "{0}")
  public static java.util.Collection<Object[]> data() {
    return Arrays.asList(
        new Object[]{ArrayList.class.getSimpleName(), new ArrayList<Integer>()},
        new Object[]{SinglyLinkedList.class.getSimpleName(), new SinglyLinkedList<Integer>()},
        new Object[]{DoublyLinkedList.class.getSimpleName(), new DoublyLinkedList<Integer>()}
    );
  }

  Collection<Integer> collection;

  public CollectionTests(String name, Collection<Integer> collection) {
    this.collection = collection;
  }

  public void outputCurrentState() {
    System.out.printf("%s:%s%n", collection.getClass().getSimpleName(), collection);
  }

  @Test
  public void clear_empty() {
    collection.clear();
    if (output) outputCurrentState();
    assertEquals(0, collection.size());
    assertTrue(collection.isEmpty());
  }

  @Test
  public void clear_nonempty() {
    collection.clear();
    collection.addAll(LISTED_PRIMES);
    if (output) outputCurrentState();
    collection.clear();
    if (output) outputCurrentState();
    assertEquals(0, collection.size());
    assertTrue(collection.isEmpty());
  }

  @Test
  public void contains_null() {
    collection.clear();
    if (output) outputCurrentState();
    collection.add(null);
    if (output) outputCurrentState();
    assertTrue(collection.contains(null));
  }

  @Test
  public void add() {
    collection.clear();
    int sizeBefore = collection.size();
    if (output) outputCurrentState();
    collection.add(PRIMES[0]);
    if (output) outputCurrentState();
    assertTrue(collection.contains(PRIMES[0]));
    assertEquals(sizeBefore + 1, collection.size());
  }

  @Test
  public void addAll() {
    collection.clear();
    int sizeBefore = collection.size();
    if (output) outputCurrentState();
    collection.addAll(LISTED_PRIMES);
    if (output) outputCurrentState();
    for (Integer prime : LISTED_PRIMES) {
      assertTrue(collection.contains(prime));
    }

    assertEquals(sizeBefore + LISTED_PRIMES.size(), collection.size());
  }

  @Test
  public void remove_true() {
    collection.clear();
    collection.add(PRIMES[0]);
    if (output) outputCurrentState();
    int sizeBefore = collection.size();
    boolean removed = collection.remove(PRIMES[0]);
    if (output) outputCurrentState();
    assertTrue(removed);
    assertFalse(collection.contains(PRIMES[0]));
    assertEquals(sizeBefore - 1, collection.size());
  }

  @Test
  public void remove_false() {
    collection.clear();
    if (output) outputCurrentState();
    int sizeBefore = collection.size();
    boolean removed = collection.remove(PRIMES[0]);
    if (output) outputCurrentState();
    assertFalse(removed);
    assertFalse(collection.contains(PRIMES[0]));
    assertEquals(sizeBefore, collection.size());
  }

  @Test
  public void remove_null() {
    collection.clear();
    collection.add(null);
    if (output) outputCurrentState();
    int sizeBefore = collection.size();
    boolean removed = collection.remove(null);
    if (output) outputCurrentState();
    assertTrue(removed);
    assertFalse(collection.contains(null));
    assertEquals(sizeBefore - 1, collection.size());
  }

  @Test
  public void removeAll() {
    collection.clear();
    collection.addAll(LISTED_PRIMES);
    if (output) outputCurrentState();
    int sizeBefore = collection.size();
    collection.removeAll(LISTED_PRIMES);
    if (output) outputCurrentState();
    for (Integer prime : LISTED_PRIMES) {
      assertFalse(collection.contains(prime));
    }

    assertEquals(sizeBefore - LISTED_PRIMES.size(), collection.size());
  }

  @Test
  public void toArray_same() {
    collection.clear();
    collection.addAll(LISTED_PRIMES);
    if (output) outputCurrentState();
    Integer[] existing = new Integer[collection.size()];
    Integer[] array = collection.toArray(existing);
    if (output) System.out.println(Arrays.toString(array));
    assertArrayEquals(WRAPPED_PRIMES, array);
    assertSame(existing, array);
  }

  @Test
  public void toArray_smaller() {
    collection.clear();
    collection.addAll(LISTED_PRIMES);
    if (output) outputCurrentState();
    Integer[] existing = new Integer[0];
    Integer[] array = collection.toArray(existing);
    if (output) System.out.println(Arrays.toString(array));
    assertArrayEquals(WRAPPED_PRIMES, array);
    assertNotSame(existing, array);
  }

  @Test
  public void toArray_larger() {
    collection.clear();
    collection.addAll(LISTED_PRIMES);
    if (output) outputCurrentState();
    Integer[] existing = new Integer[collection.size() + 1];
    Integer[] array = collection.toArray(existing);
    if (output) System.out.println(Arrays.toString(array));
    for (int i = 0; i < WRAPPED_PRIMES.length; i++) {
      assertSame(WRAPPED_PRIMES[i], array[i]);
    }

    assertNull(array[collection.size()]);
    assertSame(existing, array);
  }

  @Test
  public void toString_notnull_nonempty() {
    collection.clear();
    String result = collection.toString();
    if (output) outputCurrentState();
    assertNotNull(result);
    assertFalse(result.isEmpty());
  }

}

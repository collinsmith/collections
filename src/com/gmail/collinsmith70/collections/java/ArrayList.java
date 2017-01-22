package com.gmail.collinsmith70.collections.java;

import com.google.common.base.Preconditions;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.function.Consumer;

// TODO: document class header
public class ArrayList<E> extends AbstractList<E>
    implements List<E>, RandomAccess, Cloneable {

  private static final int DEFAULT_CAPACITY = 16;

  /**
   * Array containing references to {@code Object} instances stored within this {@code ArrayList}.
   * Valid references are stored from indeces {@code 0} to {@link #size} (exclusive), and the
   * capacity of the {@code ArrayList} before re-allocation is needed is equal to the length of the
   * array.
   */
  private Object[] elements;

  /**
   * Size of the {@code ArrayList} (the number of elements it contains).
   */
  private int size;

  /**
   * Constructs an empty {@code ArrayList} with the default initial capacity of
   * {@value #DEFAULT_CAPACITY}.
   */
  public ArrayList() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Constructs an empty {@code ArrayList} with the specified initial capacity.
   *
   * @param initialCapacity initial capacity of the list
   *
   * @throws IllegalArgumentException if the specified initial capacity is negative
   */
  public ArrayList(int initialCapacity) {
    Preconditions.checkArgument(initialCapacity >= 0,
        "Illegal initial capacity: " + initialCapacity);
    this.elements = new Object[initialCapacity];
  }

  /**
   * Constructs a list containing all of the elements from the specified {@code Collection}.
   * Elements will appear in the same order that they are retrieved with using the collection's
   * {@linkplain Collection#iterator() iterator}.
   *
   * @param c collection whose elements should be added to the list
   *
   * @throws IllegalArgumentException if the specified collection is null
   */
  public ArrayList(Collection<? extends E> c) {
    Preconditions.checkArgument(c != null, "Source collection cannot be null");
    this.elements = c.toArray();
    this.size = elements.length;
    if (elements.getClass() != Object[].class) {
      // c.toArray might (incorrectly) not return Object[] (see 6260652)
      elements = Arrays.copyOf(elements, size, Object[].class);
    }
  }

  /**
   * Returns the capacity of this {@code ArrayList} before resizing is necessary when adding
   * additional elements.
   *
   * @return capacity of this {@code ArrayList}
   */
  public int capacity() {
    return elements.length;
  }

  /**
   * Shrinks this {@code ArrayList} instance's capacity to be the same as the current size. This
   * operation can be called to minimize the storage requirements of this {@code ArrayList}
   * instance.
   */
  public void trimToSize() {
    super.modCount++;
    final int size = this.size;
    if (size < capacity()) {
      elements = Arrays.copyOf(elements, size);
    }
  }

  /**
   * Increases the capacity of this {@code ArrayList} instance, if necessary, to ensure that it can
   * accommodate the specified number of elements.
   *
   * @param minCapacity minimum number of elements that this {@code ArrayList} should be able to
   *                    accommodate
   *
   * @throws IllegalArgumentException if the minimum capacity is less than 0
   */
  public void ensureCapacity(int minCapacity) {
    Preconditions.checkArgument(minCapacity >= 0, "Illegal min capacity: " + minCapacity);
    if (minCapacity <= 0) {
      return;
    }

    grow(minCapacity);
  }

  /**
   * Increases the capacity of this {@code ArrayList} instance so that it can hold the specified
   * number of elements.
   *
   * @param minCapacity minimum number of elements that this {@code ArrayList} should be able to
   *                    accommodate
   *
   * @see #ensureCapacity(int)
   */
  private void grow(int minCapacity) {
    assert minCapacity >= 0 : "min capacity should always be >= 0";
    if (minCapacity < size) {
      return;
    }

    super.modCount++;
    this.elements = Arrays.copyOf(elements, minCapacity);
  }

  /**
   * Returns the number of elements in this {@code ArrayList}.
   *
   * @return number of elements in this {@code ArrayList}
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Validates that the specified {@code index} is from {@code 0} up to the size (exclusive) of this
   * {@code ArrayList}. This validation is performed to guarantee an index is valid when used as
   * an accessor for {@link #elements}.
   *
   * @param index index to validate
   *
   * @throws IndexOutOfBoundsException if the specified index is less than {@code 0} or greater than
   *     or equal to the size of this {@code ArrayList}
   *
   * @see #checkRangeInclusive(int)
   */
  private void checkRange(int index) {
    Preconditions.checkElementIndex(index, size);
  }

  /**
   * Validates that the specified {@code index} is from {@code 0} up to the size (inclusive) of this
   * {@code ArrayList}. This validation is performed to guarantee an index is valid when used in an
   * addition or iteration operation, where an {@code index} value of {@link #size} denotes the end
   * of this list.
   *
   * @param index index to validate
   *
   * @throws IndexOutOfBoundsException if the specified index is less than {@code 0} or greater than
   *     the size of this {@code ArrayList}
   *
   * @see #checkRange(int)
   */
  private void checkRangeInclusive(int index) {
    Preconditions.checkPositionIndex(index, size);
  }

  /**
   * Returns an unchecked cast of the {@code index} in {@code elements}.
   *
   * @param elements array to retrieve from
   * @param index    index to retrieve
   * @param <E>      return type to cast the value at {@code index} to
   *
   * @return Value in {@link #elements} at {@code index}
   *
   * @see #elements(int)
   */
  @SuppressWarnings("unchecked")
  private static <E> E elements(Object[] elements, int index) {
    assert 0 <= index && index < elements.length;
    return (E) elements[index];
  }

  /**
   * Returns an unchecked cast of the {@code index} in {@link #elements}.
   *
   * @param index index to retrieve
   *
   * @return Value in {@link #elements} at {@code index}
   *
   * @see #elements(int)
   */
  @SuppressWarnings("unchecked")
  private E elements(int index) {
    return elements(elements, index);
  }

  /**
   * Returns the element at the specified position in this {@code ArrayList}.
   *
   * @param index index of the element to return
   *
   * @return element at the specified position in this {@code ArrayList}
   *
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  @Override
  public E get(int index) {
    checkRange(index);
    return elements(index);
  }

  /**
   * Replaces the element at the specified position in this {@code ArrayList} with the specified
   * element.
   *
   * @param index   index of the element to replace
   * @param element element to be stored at the specified position
   *
   * @return element previously at the specified position
   *
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  @Override
  public E set(int index, E element) {
    checkRange(index);
    E oldValue = elements(index);
    this.elements[index] = element;
    return oldValue;
  }

  /**
   * Inserts the specified element at the specified position in this {@code ArrayList}. In addition,
   * this operation will shift the element currently at that position (if any) and any subsequent
   * elements backward.
   *
   * @param index   position to insert the specified {@code element} at
   * @param element element to insert
   *
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  @Override
  public void add(int index, E element) {
    checkRangeInclusive(index);
    final int size = this.size;
    ensureCapacity(size + 1); // increments super.modCount
    System.arraycopy(elements, index, elements, index + 1, size - index);
    elements[index] = element;
    this.size++;
  }

  /**
   * Removes the element at the specified position in this {@code ArrayList} and shifts any
   * subsequent elements forward.
   *
   * @param index position of the element to remove
   *
   * @return element that was removed
   *
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  @Override
  public E remove(int index) {
    checkRange(index);
    super.modCount++;
    E oldValue = elements(index);
    int numMoved = size - index - 1;
    if (numMoved > 0) {
      System.arraycopy(elements, index + 1, elements, index, numMoved);
    }

    elements[--size] = null;
    return oldValue;
  }

  /**
   * Removes all of the elements from this {@code ArrayList}.
   */
  @Override
  public void clear() {
    super.modCount++;
    Arrays.fill(elements, 0, size, null);
    this.size = 0;
  }

  /**
   * Returns an {@code Iterator} over the elements in this {@code ArrayList} in proper sequence.
   * <p>
   * The returned iterator is <a href="https://en.wikipedia.org/wiki/Fail-fast">fail-fast</a>.
   *
   * @return an iterator over the elements in this {@code ArrayList}
   */
  @Override
  public Iterator<E> iterator() {
    return new Itr();
  }

  /**
   * Returns a {@code ListIterator} over the elements in this {@code ArrayList} in proper sequence,
   * starting at the specified position. The specified {@code index} indicates the first element
   * that would be returned by the first call to {@link ListIterator#next() next}, while a first
   * call to {@link ListIterator#previous() previous} would return the element at {@code index - 1}.
   * <p>
   * The returned iterator is <a href="https://en.wikipedia.org/wiki/Fail-fast">fail-fast</a>.
   *
   * @param index initial cursor position of the returned iterator
   *
   * @return an iterator over the elements in this {@code ArrayList} starting at the specified
   *         position
   *
   * @throws IndexOutOfBoundsException {@inheritDoc}
   *
   * @see #listIterator()
   */
  @Override
  public ListIterator<E> listIterator(int index) {
    checkRangeInclusive(index);
    return new ListItr(index);
  }

  /**
   * Returns a {@code ListIterator} over the elements in this {@code ArrayList} in proper sequence,
   * beginning with the first element.
   * <p>
   * The returned iterator is <a href="https://en.wikipedia.org/wiki/Fail-fast">fail-fast</a>.
   *
   * @return an iterator over the elements in this {@code ArrayList} starting with the first element
   *
   * @see #listIterator(int)
   */
  @Override
  public ListIterator<E> listIterator() {
    return listIterator(0);
  }

  /**
   * An optimized implementation of {@code AbstractList.Itr}.
   */
  private class Itr implements Iterator<E> {

    /**
     * Value used to indicate that {@link #lastReturned} is invalid.
     */
    static final int INVALID_LAST_RETURNED = -1;

    /**
     * Index of the next element to return.
     */
    int cursor;

    /**
     * Index of the last element returned, otherwise {@value #INVALID_LAST_RETURNED} indicates that
     * no element has been returned.
     */
    int lastReturned = INVALID_LAST_RETURNED;

    /**
     * Expected number of modifications made to this list after each iterator operation. If the
     * value of this field does not match the current {@link AbstractList#modCount mod count},
     * then a {@link ConcurrentModificationException} is thrown.
     */
    int expectedModCount = ArrayList.super.modCount;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
      return cursor != size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E next() {
      checkForComodification();
      int i = cursor;
      if (i >= size) {
        throw new NoSuchElementException();
      }

      final Object[] elements = ArrayList.this.elements;
      if (i >= elements.length) {
        throw new ConcurrentModificationException();
      }

      this.cursor = i + 1;
      this.lastReturned = i;
      return ArrayList.elements(elements, lastReturned);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
      if (lastReturned < 0) {
        throw new IllegalStateException();
      }

      checkForComodification();
      try {
        ArrayList.this.remove(lastReturned);
        this.cursor = lastReturned;
        this.lastReturned = INVALID_LAST_RETURNED;
        this.expectedModCount = modCount;
      } catch (IndexOutOfBoundsException e) {
        throw new ConcurrentModificationException();
      }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void forEachRemaining(Consumer<? super E> consumer) {
      Preconditions.checkArgument(consumer != null, "consumer cannot be null");
      final int size = ArrayList.this.size;
      int i = cursor;
      if (i >= size) {
        return;
      }

      final Object[] elements = ArrayList.this.elements;
      if (i >= elements.length) {
        throw new ConcurrentModificationException();
      }

      while (i != size && modCount == expectedModCount) {
        E element = ArrayList.elements(elements, i++);
        consumer.accept(element);
      }

      this.cursor = i;
      this.lastReturned = i - 1;
      checkForComodification();
    }

    /**
     * Validates that the state of this {@code ArrayList} has not changed outside of operations
     * present within this {@code Iterator}.
     *
     * @throws ConcurrentModificationException if the state of this {@code ArrayList} has changed,
     *     e.g., via another thread.
     */
    final void checkForComodification() {
      if (modCount != expectedModCount) {
        throw new ConcurrentModificationException();
      }
    }

  }

  /**
   * An optimized implementation of {@code AbstractList.ListItr}.
   */
  private class ListItr extends Itr implements ListIterator<E> {

    /**
     * Constructs a {@code ListItr} over the elements in this {@code ArrayList} in proper sequence,
     * beginning with the element at the specified position. The specified {@code index} indicates
     * the first element that would be returned by the first call to {@link #next() next}, while a
     * first call to {@link #previous() previous} would return the element at {@code index - 1}.
     * <p>
     * This iterator is <a href="https://en.wikipedia.org/wiki/Fail-fast">fail-fast</a>.
     *
     * @param index initial cursor position of the returned iterator
     */
    private ListItr(int index) {
      super();
      assert 0 <= index && index <= size;
      super.cursor = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasPrevious() {
      return cursor != 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E previous() {
      checkForComodification();
      int i = cursor - 1;
      if (i < 0) {
        throw new NoSuchElementException();
      }

      final Object[] elements = ArrayList.this.elements;
      if (i >= elements.length) {
        throw new ConcurrentModificationException();
      }

      super.cursor = i;
      super.lastReturned = cursor;
      return ArrayList.elements(elements, lastReturned);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int nextIndex() {
      return cursor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int previousIndex() {
      return cursor - 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void set(E element) {
      if (lastReturned < 0) {
        throw new IllegalStateException();
      }

      checkForComodification();
      try {
        ArrayList.this.set(lastReturned, element);
      } catch (IndexOutOfBoundsException e) {
        throw new ConcurrentModificationException();
      }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(E element) {
      checkForComodification();
      try {
        int i = cursor;
        ArrayList.this.add(i, element);
        super.cursor = i + 1;
        super.lastReturned = INVALID_LAST_RETURNED;
        super.expectedModCount = ArrayList.super.modCount;
      } catch (IndexOutOfBoundsException e) {
        throw new ConcurrentModificationException();
      }
    }

  }

}

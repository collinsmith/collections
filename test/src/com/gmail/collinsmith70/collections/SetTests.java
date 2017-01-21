package com.gmail.collinsmith70.collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class SetTests {

  static final boolean output = true;

  @Parameterized.Parameters(name = "{0}")
  public static java.util.Collection<Object[]> data() {
    return Arrays.asList(
        // TODO: fill in set implementations
        new Object[]{ArrayList.class.getSimpleName(), new ArrayList<Integer>()},
        new Object[]{DoublyLinkedList.class.getSimpleName(), new DoublyLinkedList<Integer>()}
    );
  }

  Set<Integer> set;

  public SetTests(String name, Set<Integer> set) {
    this.set = set;
  }

  public void outputCurrentState() {
    System.out.printf("%s:%s%n", set.getClass().getSimpleName(), set);
  }

  @Test
  public void retainAll_true() {
    // TODO: write test
  }

  @Test
  public void retainAll_false() {
    // TODO: write test
  }

}

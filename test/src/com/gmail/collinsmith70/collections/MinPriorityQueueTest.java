package com.gmail.collinsmith70.collections;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MinPriorityQueueTest {

  @Before
  public void init() {
    MinPriorityQueue.debug = true;
  }

  @After
  public void cleanup() {
    MinPriorityQueue.debug = false;
  }

  @Test
  public void testUpdate() {
    Character[] expected = {'A', 'P', 'Q', 'R'};
    Character[] chars = {'S', 'P', 'Q', 'R'};
    MinPriorityQueue<Character> queue = MinPriorityQueue.heapify(chars);
    queue.elements.set(queue.size()-1, 'A');
    System.out.println("set: " + queue.elements);
    queue.update(queue.size() - 1);
    Assert.assertArrayEquals(expected, queue.elements.toArray(new Character[queue.size()]));
  }

}

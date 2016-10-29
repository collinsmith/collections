package com.gmail.collinsmith70.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class SinglyLinkedListTest extends ListTest<SinglyLinkedList> {

  public static class NodeTest {

    @Test
    public void testNode() {
      SinglyLinkedList.Node<Integer> node1 = new SinglyLinkedList.Node<>(null, null);
      Assert.assertNull(node1.next);
      Assert.assertNull(node1.data);

      SinglyLinkedList.Node next = node1;
      SinglyLinkedList.Node<Integer> node2 = new SinglyLinkedList.Node<>(next, null);
      Assert.assertTrue(node2.next == next);
      Assert.assertNull(node1.data);

      next = node2;
      final Integer value = Integer.MAX_VALUE;
      SinglyLinkedList.Node<Integer> node3 = new SinglyLinkedList.Node<>(next, value);
      Assert.assertTrue(node3.next == next);
      Assert.assertTrue(node3.data == value);
    }

    @Test
    public void testSetNext() {
      SinglyLinkedList.Node<Integer> node1 = new SinglyLinkedList.Node<>(null, null);
      Assert.assertNull(node1.next);
      Assert.assertTrue(node1.next == node1.getNext());
      Assert.assertNull(node1.data);
      Assert.assertTrue(node1.data == node1.getData());

      SinglyLinkedList.Node<Integer> node2 = new SinglyLinkedList.Node<>(node1, null);
      Assert.assertNotNull(node2.next);
      Assert.assertTrue(node2.next == node2.getNext());
      Assert.assertTrue(node2.getNext() == node1);
      Assert.assertNull(node2.data);
      Assert.assertTrue(node2.data == node2.getData());

      final Integer value = Integer.MAX_VALUE;
      SinglyLinkedList.Node<Integer> node3 = new SinglyLinkedList.Node<>(node2, value);
      Assert.assertNotNull(node3.next);
      Assert.assertTrue(node3.next == node3.getNext());
      Assert.assertTrue(node3.getNext() == node2);
      Assert.assertNotNull(node3.data);
      Assert.assertTrue(node3.data == node3.getData());
      Assert.assertTrue(node3.getData() == value);
    }

  }

}

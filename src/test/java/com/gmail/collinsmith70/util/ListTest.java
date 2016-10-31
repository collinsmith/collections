package com.gmail.collinsmith70.util;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ListTest<T extends List> {

  private Class<T> clazz;

  @Before
  public void init() {
    clazz = (Class<T>)getClass();
  }

  @Test
  public void test_add() throws IllegalAccessException, InstantiationException {
    Object obj = new Object();
    T instance = clazz.newInstance();
    assertFalse(instance.contains(obj));
    instance.add(obj);
    assertTrue(instance.contains(obj));
  }

}

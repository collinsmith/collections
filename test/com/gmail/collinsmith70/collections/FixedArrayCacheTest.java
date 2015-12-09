package com.gmail.collinsmith70.collections;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Collin Smith <i>collinsmith70@gmail.com</i>
 */
public class FixedArrayCacheTest {
    
    public FixedArrayCacheTest() {
    }

    @Test
    public void testSize() {
    }

    @Test
    public void testIsEmpty() {
    }

    @Test
    public void testClear() {
    }

    @Test
    public void testPush() {
        FixedArrayCache<Integer> cache = new FixedArrayCache<>();
        
        assertEquals(cache.size(), 0);
        for (int i = 1; i <= cache.limit()*2; i++) {
            cache.push(i);
            assertEquals(cache.size(), Math.min(i, cache.limit()));
            assertEquals(cache.pushes(), i);
            assertEquals((int)cache.head(), i);
        }
    }

    @Test
    public void testIterator() {
        FixedArrayCache<Integer> cache = new FixedArrayCache<>();
        
        final int limit = 8;
        for (int i = 0; i < limit; i++) {
            cache.push(i);
        }
        
        assertEquals(cache.size(), limit);
        
        for (int i : cache) {
            assertEquals((int)cache.get(i), i);
        }
    }

    @Test
    public void testContains() {
        FixedArrayCache<Integer> cache = new FixedArrayCache<>();
        for (int i = 1; i <= cache.limit()*2; i++) {
            cache.push(i);
            assertTrue(cache.contains(i));
        }
    }

    @Test
    public void testToArray_0args() {
    }

    @Test
    public void testToArray_GenericType() {
    }

    @Test
    public void testAdd() {
    }
    
}

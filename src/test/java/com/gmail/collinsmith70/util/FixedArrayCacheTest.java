package com.gmail.collinsmith70.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Collin Smith <i>collinsmith70@gmail.com</i>
 */
public class FixedArrayCacheTest {
    
    public FixedArrayCacheTest() {
    }

    /**
     * Test of push method, of class FixedArrayCache.
     */
    @Test
    public void testPush() {
        FixedArrayCache<String> cache = new FixedArrayCache<>(8);
        
        int size = 0;
        for (String s : cache) {
            size++;
        }
        
        assertEquals(size, 0);
        
        cache.push("1");
        size = 0;
        for (String s : cache) {
            size++;
        }
        
        assertEquals(size, 0);
    }

    /**
     * Test of iterator method, of class FixedArrayCache.
     */
    @Test
    public void testIterator() {
        
    }
    
}

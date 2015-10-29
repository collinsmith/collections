package com.gmail.collinsmith70.util;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Collin Smith <i>collinsmith70@gmail.com</i>
 */
public class TernaryTrieTest {
    public TernaryTrieTest() {
        Trie<Integer> trie = new TernaryTrie<Integer>();
        assertNotNull(trie);
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        map.put("key4", 4);
        trie = new TernaryTrie<Integer>(map);
        assertNotNull(trie);
        assertEquals(map.size(), trie.size());
        for (Entry<String, Integer> e : map.entrySet()) {
            assertTrue(trie.containsKey(e.getKey()));
            assertTrue(trie.get(e.getKey()).equals(e.getValue()));
        }
    }

    /**
     * Test of size method, of class TernaryTrie.
     */
    @Test
    public void testSize() {
        Trie<Integer> trie = new TernaryTrie<Integer>();
        assertEquals(0, trie.size());
        trie.put("key1", 1);
        assertEquals(1, trie.size());
        trie.put("key2", 2);
        assertEquals(2, trie.size());
        trie.put("key2", 2);
        assertEquals(2, trie.size());
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        map.put("key4", 4);
        trie = new TernaryTrie<Integer>(map);
        assertEquals(map.size(), trie.size());
    }

    /**
     * Test of isEmpty method, of class TernaryTrie.
     */
    @Test
    public void testIsEmpty() {
        Trie<Integer> trie = new TernaryTrie<Integer>();
        assertTrue(trie.isEmpty());
        trie.put("key1", 1);
        assertFalse(trie.isEmpty());
        trie.remove("key1");
        assertTrue(trie.isEmpty());
    }

    /**
     * Test of put method, of class TernaryTrie.
     */
    @Test
    public void testPut() {
        Trie<Integer> trie = new TernaryTrie<Integer>();
        trie.put("key1", 1);
        assertTrue(trie.get("key1") == 1);
        trie.put("key1", 2);
        assertTrue(trie.get("key1") == 2);
        trie.put("key2", 2);
        assertTrue(trie.get("key2") == 2);
        int oldSize = trie.size();
        trie.put("key2", null);
        assertNull(trie.get("key2"));
        assertEquals(oldSize-1, trie.size());
    }

    /**
     * Test of containsKey method, of class TernaryTrie.
     */
    @Test
    public void testContainsKey() {
        Trie<Integer> trie = new TernaryTrie<Integer>();
        assertFalse(trie.containsKey("key1"));
        trie.put("key1", 1);
        assertTrue(trie.containsKey("key1"));
        trie.put("key1", 2);
        assertTrue(trie.containsKey("key1"));
        trie.put("key2", 2);
        assertTrue(trie.containsKey("key2"));
    }

    /**
     * Test of get method, of class TernaryTrie.
     */
    @Test
    public void testGet() {
        Trie<Integer> trie = new TernaryTrie<Integer>();
        trie.put("key1", 1);
        assertTrue(trie.get("key1") == 1);
        trie.put("key1", 2);
        assertTrue(trie.get("key1") == 2);
        trie.put("key2", 2);
        assertTrue(trie.get("key2") == 2);
        int oldSize = trie.size();
        trie.put("key2", null);
        assertNull(trie.get("key2"));
        assertEquals(oldSize-1, trie.size());
    }

    /**
     * Test of containsValue method, of class TernaryTrie.
     */
    @Test
    public void testContainsValue() {
        Trie<Integer> trie = new TernaryTrie<Integer>();
        assertFalse(trie.containsValue(1));
        trie.put("key1", 1);
        assertTrue(trie.containsValue(1));
        trie.put("key1", 2);
        assertFalse(trie.containsValue(1));
        trie.put("key2", 2);
        assertTrue(trie.containsValue(2));
        trie.put("key1", 1);
        assertTrue(trie.containsValue(1));
        assertTrue(trie.containsValue(2));
    }

    /**
     * Test of remove method, of class TernaryTrie.
     */
    @Test
    public void testRemove() {
        Trie<Integer> trie = new TernaryTrie<Integer>();
        trie.put("key1", 1);
        assertTrue(trie.remove("key1") == 1);
        assertNull(trie.remove("key1"));
        trie.put("key1", 1);
        trie.put("key2", 2);
        trie.put("key3", 3);
        assertTrue(trie.remove("key1") == 1);
        assertNull(trie.remove("key1"));
        assertTrue(trie.remove("key2") == 2);
        assertNull(trie.remove("key2"));
        assertTrue(trie.remove("key3") == 3);
        assertNull(trie.remove("key3"));
    }

    /**
     * Test of putAll method, of class TernaryTrie.
     */
    @Test
    public void testPutAll() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        map.put("key4", 4);
        
        Trie<Integer> trie = new TernaryTrie<Integer>();
        trie.putAll(map);
        for (Entry<String, Integer> e : map.entrySet()) {
            assertTrue(trie.containsKey(e.getKey()));
            assertTrue(trie.get(e.getKey()).equals(e.getValue()));
        }
    }

    /**
     * Test of clear method, of class TernaryTrie.
     */
    @Test
    public void testClear() {
        Trie<Integer> trie = new TernaryTrie<Integer>();
        trie.put("key1", 1);
        trie.put("key2", 2);
        trie.put("key3", 3);
        assertEquals(3, trie.size());
        trie.clear();
        assertEquals(0, trie.size());
    }

    /**
     * Test of getKeysPrefixedWith method, of class TernaryTrie.
     */
    @Test
    public void testGetKeysPrefixedWith() {
        Trie<Object> trie = new TernaryTrie<Object>();
        final Object Object = new Object();
        trie.put("a", Object);
        trie.put("apple", Object);
        trie.put("app", Object);
        trie.put("to", Object);
        trie.put("tea", Object);
        trie.put("ted", Object);
        trie.put("ten", Object);
        trie.put("i", Object);
        trie.put("in", Object);
        trie.put("inn", Object);
        
        Set<String> keys = trie.getKeysPrefixedWith("a");
        keys.containsAll(ImmutableSet.of("a", "apple", "app"));
        keys.removeAll(ImmutableSet.of("a", "apple", "app"));
        assertTrue(keys.isEmpty());
        
        keys = trie.getKeysPrefixedWith("ap");
        keys.containsAll(ImmutableSet.of("apple", "app"));
        keys.removeAll(ImmutableSet.of("apple", "app"));
        assertTrue(keys.isEmpty());
        
        keys = trie.getKeysPrefixedWith("app");
        keys.containsAll(ImmutableSet.of("apple", "app"));
        keys.removeAll(ImmutableSet.of("apple", "app"));
        assertTrue(keys.isEmpty());
        
        keys = trie.getKeysPrefixedWith("appe");
        assertTrue(keys.isEmpty());
        
        keys = trie.getKeysPrefixedWith("appl");
        keys.containsAll(ImmutableSet.of("apple"));
        keys.removeAll(ImmutableSet.of("apple"));
        assertTrue(keys.isEmpty());
        
        keys = trie.getKeysPrefixedWith("t");
        keys.containsAll(ImmutableSet.of("to", "tea", "ted", "ten"));
        keys.removeAll(ImmutableSet.of("to", "tea", "ted", "ten"));
        assertTrue(keys.isEmpty());
        
        keys = trie.getKeysPrefixedWith("to");
        keys.containsAll(ImmutableSet.of("to"));
        keys.removeAll(ImmutableSet.of("to"));
        assertTrue(keys.isEmpty());
        
        keys = trie.getKeysPrefixedWith("te");
        keys.containsAll(ImmutableSet.of("tea", "ted", "ten"));
        keys.removeAll(ImmutableSet.of("tea", "ted", "ten"));
        assertTrue(keys.isEmpty());
        
        keys = trie.getKeysPrefixedWith("i");
        keys.containsAll(ImmutableSet.of("i", "in", "inn"));
        keys.removeAll(ImmutableSet.of("i", "in", "inn"));
        assertTrue(keys.isEmpty());
        
        keys = trie.getKeysPrefixedWith("in");
        keys.containsAll(ImmutableSet.of("in", "inn"));
        keys.removeAll(ImmutableSet.of("in", "inn"));
        assertTrue(keys.isEmpty());
        
        keys = trie.getKeysPrefixedWith("inn");
        keys.containsAll(ImmutableSet.of("inn"));
        keys.removeAll(ImmutableSet.of("inn"));
        assertTrue(keys.isEmpty());
    }

    /**
     * Test of keySet method, of class TernaryTrie.
     */
    @Test
    public void testKeySet() {
        final Object Object = new Object();
        Map<String, Object> map = ImmutableMap.<String, Object>builder()
                .put("a", Object)
                .put("apple", Object)
                .put("app", Object)
                .put("to", Object)
                .put("tea", Object)
                .put("ted", Object)
                .put("ten", Object)
                .put("i", Object)
                .put("in", Object)
                .put("inn", Object)
                .build();
        Trie<Object> trie = new TernaryTrie<Object>(map);
        Set<String> keySet = trie.keySet();
        Set<String> mapKeys = map.keySet();
        assertEquals(mapKeys.size(), keySet.size());
        assertTrue(keySet.containsAll(mapKeys));
        keySet.removeAll(mapKeys);
        assertEquals(0, keySet.size());
    }

    /**
     * Test of values method, of class TernaryTrie.
     */
    @Test
    public void testValues() {
        Map<String, Integer> map = ImmutableMap.<String, Integer>builder()
                .put("a", 1)
                .put("apple", 2)
                .put("app", 3)
                .put("to", 4)
                .put("tea", 5)
                .put("ted", 6)
                .put("ten", 7)
                .put("i", 8)
                .put("in", 8)
                .put("inn", 8)
                .build();
        Trie<Integer> trie = new TernaryTrie<Integer>(map);
        Collection<Integer> values = trie.values();
        Collection<Integer> mapValues = map.values();
        assertEquals(mapValues.size(), values.size());
        assertTrue(values.containsAll(mapValues));
        values.removeAll(mapValues);
        assertEquals(0, values.size());
    }

    /**
     * Test of entrySet method, of class TernaryTrie.
     */
    @Test
    public void testEntrySet() {
        Map<String, Integer> map = ImmutableMap.<String, Integer>builder()
                .put("a", 1)
                .put("apple", 2)
                .put("app", 3)
                .put("to", 4)
                .put("tea", 5)
                .put("ted", 6)
                .put("ten", 7)
                .put("i", 8)
                .put("in", 8)
                .put("inn", 8)
                .build();
        Trie<Integer> trie = new TernaryTrie<Integer>(map);
        assertEquals(trie.entrySet(), map.entrySet());
    }
    
}

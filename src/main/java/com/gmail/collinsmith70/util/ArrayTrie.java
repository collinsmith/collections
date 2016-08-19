package com.gmail.collinsmith70.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ArrayTrie<T> implements Trie<T> {
    private static final int SENTINEL = '\uE000';
    private static final int DEFAULT_DATA_SIZE = 1<<10;
    
    private Map<Character, Integer> base;
    private char[] data;
    private int[] next;
    
    private int tail;
    private int size;
    
    public ArrayTrie() {
        this(DEFAULT_DATA_SIZE);
    }
    
    public ArrayTrie(int dataSize) {
        if (dataSize < 0) {
            throw new IllegalArgumentException(String.format(
                    "Invalid dataSize value: %d, Expected >= 0", dataSize));
        }
        
        this.base = new HashMap<Character, Integer>();
        this.data = new char[dataSize];
        this.next = new int[dataSize];
        
        this.tail = 0;
        this.size = 0;
    }

    @Override
    public T put(String key, T value) {
        key = Strings.nullToEmpty(key);
        if (key.isEmpty()) {
            throw new IllegalArgumentException("Empty keys are not allowed");
        }
        
        Character firstChar = key.charAt(0);
        Integer obj = base.get(firstChar);
        if (obj == null) {
            return null;
        }
        
        int dataOffset = obj;
        if (dataOffset == 0) {
            base.put(firstChar, tail);
            put(key.toCharArray(), 1);
            return null;
        }
        
        char[] chars = key.toCharArray();
        for (int i = 1; i < chars.length; i++) {
            while (true) {
                if (data[dataOffset] == chars[i]) {
                    dataOffset++;
                    break;
                } else if (next[dataOffset] >= 0) {
                    dataOffset = next[dataOffset];
                    continue;
                } else {
                    next[dataOffset] = tail;
                    put(chars, i);
                    return null;
                }
            }
        }
        
        if (data[dataOffset] == SENTINEL) {
            return null;
        } else {
            while (next[dataOffset] >= 0) {
                dataOffset = next[dataOffset];
                if (data[dataOffset] == SENTINEL) {
                    return null;
                }
            }
        }
        
        checkAndGrow(1);
        next[dataOffset] = tail;
        data[tail++] = SENTINEL;
        size++;
        return null;
    }
    
    private void put(char[] chars, int start) {
        assert chars != null;
        assert start >= 0;
        checkAndGrow(chars.length-start+1);
        while (start < chars.length) {
            data[tail++] = chars[start++];
        }
        
        data[tail++] = SENTINEL;
        size++;
    }
    
    private void checkAndGrow(int size) {
        if (data.length > (tail+size)) {
            return;
        }
        
        int newSize = data.length;
        do {
            newSize <<= 1;
        } while (newSize <= (tail+size));
        
        char[] newData = new char[newSize];
        System.arraycopy(data, 0, newData, 0, tail);
        this.data = newData;
        
        int[] newNext = new int[newSize];
        System.arraycopy(next, 0, newNext, 0, tail);
        this.next = newNext;
    }

    @Override
    public Set<String> getKeysPrefixedWith(String prefix) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsKey(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsValue(Object value) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T get(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void putAll(Map<? extends String, ? extends T> m) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<String> keySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<T> values() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Set<Entry<String, T>> entrySet() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

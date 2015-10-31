package com.gmail.collinsmith70.util;

import com.google.common.base.MoreObjects;
import com.google.common.base.Predicates;
import com.google.common.base.Strings;
import java.util.HashMap;
import java.util.Map;

public class ArrayTrie<T> implements Trie<T> {
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
        
        Integer obj = base.get(key.charAt(0));
        if (obj == null) {
            return null;
        }
        
        char[] chars = key.toCharArray();
        int dataOffset = obj;
    }
}

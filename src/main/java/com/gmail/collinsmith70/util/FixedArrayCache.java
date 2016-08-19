package com.gmail.collinsmith70.util;

import java.util.Iterator;

public class FixedArrayCache<V> implements Iterable<V> {
    private static final int DEFAULT_SIZE = 1<<8;
    
    private Object[] data;
    private int head;
    private int tail;
    
    public FixedArrayCache() {
        this(DEFAULT_SIZE);
    }
    
    public FixedArrayCache(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(
                    "size must be a positive integer");
        }
        
        this.data = new Object[size];
    }
    
    public void push(V obj) {
        data[head] = obj;
        head = increment(head);
    }
    
    protected V get(int i) {
        return (V)data[i];
    }
    
    private int increment(int i) {
        if (i + 1 >= data.length) {
            return 0;
        }
        
        return i + 1;
    }
    
    private int decrement(int i) {
        if (i - 1 < 0) {
            return data.length - 1;
        }
        
        return i - 1;
    }
    
    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            
            private int head = FixedArrayCache.this.decrement(
                    FixedArrayCache.this.head);
            
            @Override
            public boolean hasNext() {
                return FixedArrayCache.this.get(head) != null;
            }

            @Override
            public V next() {
                V data = FixedArrayCache.this.get(head);
                FixedArrayCache.this.decrement(head);
                return data;
            }
            
        };
    }
}

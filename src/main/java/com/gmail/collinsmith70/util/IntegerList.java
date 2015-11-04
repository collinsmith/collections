package com.gmail.collinsmith70.util;

import java.util.LinkedList;
import java.util.List;

public class IntegerList implements Buildable<IntegerList, IntegerList.Builder> {
    
    private final List<Integer> list;
    
    public IntegerList() {
        this.list = new LinkedList<>();
    }
    
    public void add(int i) {
        list.add(i);
    }

    @Override
    public Builder builder() {
        return new Builder();
    }

    @Override
    public Builder builder(IntegerList initialValues) {
        return new Builder(initialValues);
    }
    
    public static class Builder extends AbstractBuilder<IntegerList> {

        public Builder() {
            this(new IntegerList());
        }
        
        public Builder(IntegerList initialValues) {
            super(initialValues);
        }
        
        public Builder add(int i) {
            build().add(i);
            return this;
        }
        
    }
}

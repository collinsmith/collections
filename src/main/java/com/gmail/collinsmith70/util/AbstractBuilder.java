package com.gmail.collinsmith70.util;

public abstract class AbstractBuilder<T> implements Builder<T> {
    private final T INSTANCE;

    public AbstractBuilder(T initialValues) {
        this.INSTANCE = initialValues;
    }
    
    private AbstractBuilder(Class<T> clazz)
            throws InstantiationException, IllegalAccessException {
        this(clazz.newInstance());
    }
    
    @Override
    public T build() {
        return INSTANCE;
    }
}

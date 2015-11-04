package com.gmail.collinsmith70.util;

public interface Buildable<T extends Buildable<T, B>, B extends Builder<? super T>> {
    static <T extends Buildable<T, B>, B extends Builder<T>>
            B getBuilder(Class<T> clazz)
                    throws IllegalAccessException, InstantiationException {
        return clazz.newInstance().builder();
    }
            
    static <T extends Buildable<T, B>, B extends Builder<T>>
            B getBuilder(Class<T> clazz, T initialValues)
                    throws IllegalAccessException, InstantiationException {
        return clazz.newInstance().builder(initialValues);
    }
    
    static void test() {
        try {
            IntegerList list = Buildable
                    .getBuilder(IntegerList.class)
                    .add(1)
                    .build();
        } catch (Exception e) {
        }
    }
    
    B builder();
    B builder(T initialValues);
}

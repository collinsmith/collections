package com.gmail.collinsmith70.collections;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    CollectionTests.class,
    ListTests.class,
    ArrayListTests.class,
    SinglyLinkedListTests.class,
    DoublyLinkedListTests.class
})
public class AllListImplementationTests {
}

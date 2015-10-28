
package com.gmail.collinsmith70.util;

import java.util.Map;
import java.util.Set;

/**
 * A <a href="https://en.wikipedia.org/wiki/Trie">Trie</a> (pronounced "try") is
 * a form of a {@link Map} data structure, except optimized for storing and
 * searching for {@link String}s.
 *
 * @author Collin Smith <strong>collinsmith@csupomona.edu</strong>
 *
 * @param <V> type of the value to be stored at each node
 */
public interface Trie<V> extends Map<String, V> {
    /**
     * Returns a {@link Set} of {@link String}s with each element
     * representing a valid key contained within this {@link Trie} and
     * starting with the specified prefix value.
     *
     * @param prefix key prefix to search for
     *
     * @return a {@link Set} of {@link String}s with each String
     *	representing a key with the given prefix
     */
    Set<String> getKeysPrefixedWith(String prefix);
}

package com.gmail.collinsmith70.collections;

import java.util.Iterator;

public interface Set<E> extends Collection<E> {

  default boolean retainAll(Collection<?> elements) {
    boolean modified = false;
    for (Iterator<E> it = this.iterator(); it.hasNext();) {
      E element = it.next();
      if (!elements.contains(element)) {
        it.remove();
        modified = true;
      }
    }

    return modified;
  }

}

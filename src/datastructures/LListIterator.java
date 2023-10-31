package datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LListIterator<T extends Comparable<T>> implements Iterator<T> {
    private LLNode<T> head;

    public LListIterator(LLNode<T> head) {
        this.head = head;
    }

    @Override
    public boolean hasNext() {
        return head != null;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        T ret = head.getValue();
        head = head.getNext();

        return ret;
    }
}

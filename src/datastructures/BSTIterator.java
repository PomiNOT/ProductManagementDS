package datastructures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BSTIterator<T extends Comparable<T>> implements Iterator<T> {
    private final Queue<BSTNode<T>> queue;

    public BSTIterator(BSTNode<T> root) {
        this.queue = new LinkedList<>();

        if (root != null) {
            this.queue.add(root);
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();

        BSTNode<T> parent = queue.poll();

        if (parent.getLeft() != null) {
            queue.add(parent.getLeft());
        }

        if (parent.getRight() != null) {
            queue.add(parent.getRight());
        }

        return parent.getValue();
    }
}

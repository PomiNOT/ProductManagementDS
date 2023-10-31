package datastructures;

public class LLNode<T extends Comparable<T>> {
    private T value;
    private LLNode<T> next;
    private LLNode<T> prev;

    public LLNode(T value, LLNode<T> next, LLNode<T> prev) {
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public LLNode<T> getNext() {
        return next;
    }

    public void setNext(LLNode<T> next) {
        this.next = next;
    }

    public LLNode<T> getPrev() {
        return prev;
    }

    public void setPrev(LLNode<T> prev) {
        this.prev = prev;
    }
}

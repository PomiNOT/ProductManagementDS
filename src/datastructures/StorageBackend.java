package datastructures;

// this is the storage object requirements that ProductManager needs to function
// the BST and LinkedList implement this interface
public interface StorageBackend<T extends Comparable<T>> extends Iterable<T> {
    public boolean exists(T value);
    public T find(T search);

    public void insert(T value);
    public void delete(T value);
    public void clear();
}

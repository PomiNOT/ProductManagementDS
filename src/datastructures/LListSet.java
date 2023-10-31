package datastructures;

import java.util.Iterator;

public class LListSet<T extends Comparable<T>> implements StorageBackend<T> {

	private LLNode<T> head;
	private LLNode<T> tail;
	private int count = 0;

	public int getCount() {
		return count;
	}

	@Override
	public void clear() {
		head = tail = null;
	}

	@Override
	public boolean exists(T value) {
		return find(value) != null;
	}

	@Override
	public T find(T value) {
		for (T item : this) {
			if (item.compareTo(value) == 0) {
				return item;
			}
		}

		return null;
	}

	private LLNode<T> nodeOfValue(T value) {
		LLNode<T> curr = head;

		while (head != null) {
			if (curr.getValue().compareTo(value) == 0) {
				return curr;
			}
			curr = head.getNext();
		}

		return null;
	}

	public boolean isEmpty() {
		return head == null && tail == null;
	}

	public void insertAfterK(int k, T value) {
		int i = 0;

		LLNode<T> current = head;

		while (current != null) {
			LLNode<T> next = current.getNext();

			if (i == k) {
				LLNode<T> node = new LLNode<>(value, next, current.getPrev());
				current.setNext(node);

				if (current == tail) {
					tail = node;
				}

				return;
			}

			current = current.getNext();
			i++;
		}
	}

	public void sort() {
		boolean swapped;

		do {
			swapped = false;

			LLNode<T> current = head;
			while (current.getNext() != null) {
				int compareResult = current.getValue().compareTo(current.getNext().getValue());
				if (compareResult > 0) {
					T tmp = current.getValue();
					current.setValue(current.getNext().getValue());
					current.getNext().setValue(tmp);
					swapped = true;
				}

				current = current.getNext();
			}
		} while (swapped);
	}

	@Override
	public void insert(T value) {
		if (exists(value)) {
			return;
		}

		LLNode<T> node = new LLNode<>(value, null, tail);
		if (isEmpty()) {
			head = tail = node;
			count++;
			return;
		}

		tail.setNext(node);
		tail = node;
		count++;
	}

	@Override
	public void delete(T value) {
		if (isEmpty()) {
			return;
		}

		LLNode<T> nodeToDelete = nodeOfValue(value);
		if (nodeToDelete == null) {
			return;
		}

		if (nodeToDelete == head) {
			head = head.getNext();
			if (head == null) {
				tail = null;
			} else {
				head.setPrev(null);
			}

			count--;
			return;
		}

		if (nodeToDelete == tail) {
			tail = tail.getPrev();

			if (tail == null) {
				head = null;
			} else {
				tail.setNext(null);
			}

			count--;
			return;
		}

		LLNode<T> prev = nodeToDelete.getPrev();
		LLNode<T> next = nodeToDelete.getNext();

		prev.setNext(next);
		next.setPrev(prev);
		count--;
	}

	@Override
	public Iterator<T> iterator() {
		return new LListIterator<T>(head);
	}
}

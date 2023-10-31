package datastructures;

public class BSTNode<T extends Comparable> implements Comparable<BSTNode<T>> {
	private T value;
	private BSTNode<T> parent;
	private BSTNode<T> left;
	private BSTNode<T> right;

	public BSTNode(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public BSTNode<T> getParent() {
		return parent;
	}

	public BSTNode<T> setParent(BSTNode<T> parent) {
		this.parent = parent;
		return this;
	}

	public BSTNode<T> getLeft() {
		return left;
	}

	public BSTNode<T> setLeft(BSTNode<T> left) {
		this.left = left;
		return this;
	}

	public BSTNode<T> getRight() {
		return right;
	}

	public BSTNode<T> setRight(BSTNode<T> right) {
		this.right = right;
		return this;
	}

	public void replaceTargetWith(BSTNode<T> target, BSTNode<T> node) {
		if (target == getLeft()) {
			setLeft(node);
		} else if (target == getRight()) {
			setRight(node);
		}
	}

	public boolean isLeaf() {
		return getLeft() == null && getRight() == null;
	}
	
	@Override
	public int compareTo(BSTNode<T> other) {
		return this.getValue().compareTo(other.getValue());
	}

	@Override
	public String toString() {
		return "Node(" + this.getValue() + ")";
	}
}

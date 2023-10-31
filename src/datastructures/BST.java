package datastructures;

import java.util.Iterator;

// this is modified from the tree exercise that I did, I removed all the unnecessary methods
// and added additional features
public class BST<T extends Comparable<T>> implements StorageBackend<T> {

	/*
	protected interface Visitor<U extends Comparable<U>> {
		// the visitor returns true to continue iterating or false
		boolean visit(BSTNode<U> node);
	}
	 */
	protected BSTNode<T> root;

	public BST() {
	}

	public BSTNode<T> getRoot() {
		return root;
	}

	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void clear() {
		root = null;
	}

	/*
	protected int breadthVisit(Visitor<T> visitor, BSTNode<T> theRoot) {
		Queue<BSTNode<T>> queue = new LinkedList<>();
		if (isEmpty()) return 0;

		queue.add(theRoot);

		int depth = 0;

		while (!queue.isEmpty()) {
			int levelSize = queue.size();

			for (int i = 0; i < levelSize; i++) {
				BSTNode<T> node = queue.poll();
				boolean cont = visitor.visit(node);
				if (!cont) return depth;

                if (node.getLeft() != null) {
					queue.add(node.getLeft());
				}
				
				if (node.getRight() != null) {
					queue.add(node.getRight());
				}
			}

			depth++;
		}

		return depth;
	}
	 */
	@Override
	public Iterator<T> iterator() {
		return new BSTIterator<T>(root);
	}

	public BSTNode<T> searchExtended(T value, boolean returnPrevious) {
		BSTNode<T> previous = root;
		BSTNode<T> current = root;
		while (current != null) {
			int comparisonResult = value.compareTo(current.getValue());
			if (comparisonResult == 0) {
				return current;
			}

			previous = current;
			if (comparisonResult < 0) {
				current = current.getLeft();
			} else {
				current = current.getRight();
			}
		}

		return returnPrevious ? previous : null;
	}

	public boolean exists(T value) {
		return search(value) != null;
	}

	public BSTNode<T> search(T value) {
		return searchExtended(value, false);
	}

	public T find(T value) {
		BSTNode<T> result = search(value);
		return result == null ? null : result.getValue();
	}

	public void insert(T value) {
		if (isEmpty()) {
			root = new BSTNode<>(value);
			return;
		}

		BSTNode<T> parent = searchExtended(value, true);

		int comparisonResult = value.compareTo(parent.getValue());
		if (comparisonResult == 0) {
			return;
		}

		BSTNode<T> newNode = new BSTNode<>(value).setParent(parent);
		if (comparisonResult < 0) {
			parent.setLeft(newNode);
		} else {
			parent.setRight(newNode);
		}

		return;
	}

	public BSTNode<T> max(BSTNode<T> root) {
		if (root == null) {
			return null;
		}

		BSTNode<T> current = root;
		while (current.getRight() != null) {
			current = current.getRight();
		}

		return current;
	}

	public BSTNode<T> min(BSTNode<T> root) {
		if (root == null) {
			return null;
		}

		BSTNode<T> current = root;
		while (current.getLeft() != null) {
			current = current.getLeft();
		}

		return current;
	}

	public void delete(T value) {
		BSTNode<T> target = search(value);
		if (target == null) {
			return;
		}

		// case: leaf node
		if (target.isLeaf()) {
			// subcase: root and is leaf then just clear the tree, since the root does not have any parent
			if (target == root) {
				clear();
			} else {
				target.getParent().replaceTargetWith(target, null);
			}
			return;
		}

		//cases: one branch exists, replace the target with either subtree
		if (target.getLeft() == null && target.getRight() != null) {
			// if root, make the subtree be the root and set its parent to null since root has no parent
			if (target == root) {
				root = target.getRight();
				target.getRight().setParent(null);
			} else {
				target.getRight().setParent(target.getParent());
				target.getParent().replaceTargetWith(target, target.getRight());
			}
			return;
		}

		if (target.getLeft() != null && target.getRight() == null) {
			if (target == root) {
				root = target.getLeft();
				target.getLeft().setParent(null);
			} else {
				target.getLeft().setParent(target.getParent());
				target.getParent().replaceTargetWith(target, target.getLeft());
			}
			return;
		}

		//case: both branches exist, find max node in the left subtree, copy its value and delete it
		BSTNode<T> maxNode = max(target.getLeft());
		target.setValue(maxNode.getValue());
		// it's guaranteed to be leaf node
		maxNode.getParent().replaceTargetWith(maxNode, null);

		return;
	}
}

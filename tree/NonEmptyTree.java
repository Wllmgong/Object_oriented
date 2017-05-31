package tree;

/**
 * 
 * @author William Gong Name:William 
 * Gong Section:0303 ID:113510311 Directory
 *    		ID:wgong1
 *          Honor Code:I pledge on my honor that i have not give or
 *         received any unauthorized assistance on this assignment
 * 
 *         The purpose of the project is to create a polymorphic tree that uses
 *         a emptytree class and a nonempty class. When a subtree is empty, the
 *         emptytree class is called, and when the tree is not empty, then
 *         nonemptytree is called This is the NonEmptyTree class for the tree
 *         interface. This class serves for the values that contains a key and a
 *         value. Its a data storage that finds the value by matching the key it
 *         finds. The empty tree acts like the base case that ends the recursion
 *         calls in the methods The values stored in left are smaller than the
 *         root, and the values stored in right are larger than the root
 * @param <K>
 * @param <V>
 */
@SuppressWarnings("unchecked")
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {
	private K key; // the key that points to the value
	private V value; // the value to be stored
	private Tree<K, V> left; // left subtree
	private Tree<K, V> right;// right subtree

	/**
	 * the constructor for the nonemptytree class that creates a new
	 * nonemptytree with the given key and value
	 * 
	 * @param newKey
	 * @param newVal
	 */
	public NonEmptyTree(K newKey, V newVal) {

		key = newKey;
		value = newVal;
		left = EmptyTree.getInstance();// set the left subtree as empty
		right = EmptyTree.getInstance();// set the right subtree as empty
	}

	/**
	 * it adds a new nonempty tree to the original tree and either becomes the
	 * root, left subtree or the right subtree if the key equals to an already
	 * existing tree, the value is replaced
	 */
	public NonEmptyTree<K, V> add(K keyToAdd, V valueToAdd) {
		if (keyToAdd == null || valueToAdd == null)
			throw new NullPointerException();
		// check for null
		if (key.equals(keyToAdd)) {
			value = valueToAdd;// when the key is equal already existing tree,
								// it replaces the new value
			return this;
		}

		if (key.compareTo(keyToAdd) > 0) {
			// if the keytoadd is less than the key, then its added to the left
			left = left.add(keyToAdd, valueToAdd);
			return this;
		} else {
			// if the keytoadd is more than the key, then its added to the right
			right = right.add(keyToAdd, valueToAdd);
			return this;
		}
	}

	/**
	 * returns the size of the nonemptytree
	 */
	public int size() {
		return 1 + left.size() + right.size();
		// adds the left subtree size and right subtree size
		// plus the current tree
	}

	/**
	 * it goes through the tree and finds the key and returns the value the key
	 * points to
	 */
	public V lookup(K keyToLookFor) {
		if (keyToLookFor == null)
			throw new NullPointerException();
		// check if the keytolookfor is null and returns the null exception if
		// it is null
		if (key.equals(keyToLookFor))
			return value;
		// finds the key in the tree and returns the value in that tree

		if (key.compareTo(keyToLookFor) > 0)
			return left.lookup(keyToLookFor);
		// if key to look for is less than the key, then to
		// looks in the left tree
		else
			return right.lookup(keyToLookFor);
		// if key to look for is more than the key, then to
		// looks in the right tree
	}

	/**
	 * it returns the string form of the tree, which contains the key and the
	 * value
	 */
	public String toString() {

		String str = "";
		if (!left.toString().equals("")) {
			str = str + left.toString() + " ";
		}
		// first finds the left subtree's keys and values until it reaches to
		// the end, then it adds the string form of the subtree to string
		str = str + key.toString() + "+" + value.toString();
		// adds the root key and value to the string
		if (!(right.toString().equals(""))) {
			str = str + " " + right.toString();
		}
		// finds the right sub tree substring and adds to the string
		return str;

	}

	/**
	 * this method copies a new tree of the current tree and returns the new
	 * tree
	 */
	public Tree<K, V> copy() {

		NonEmptyTree<K, V> one = new NonEmptyTree<K, V>(key, value);
		// creates a new nonemptytree with the same values
		one.left = this.left.copy();
		// copies the left subtree
		one.right = this.right.copy();
		// copies the right subtree
		return (Tree<K, V>) one;
		// return the new tree
	}

	/**
	 * this method copies the subtree that's below the given key
	 */
	public Tree<K, V> subTree(K keyOfSubTree) {
		if (keyOfSubTree == null)
			throw new NullPointerException();
		if (key == keyOfSubTree) {
			return this.copy();
		}
		// first finds the key, then create a copy of the subtree

		if (key.compareTo(keyOfSubTree) > 0)
			// finds the key by checking left if its less than the current
			return left.subTree(keyOfSubTree);
		else
			// finds the key by checking the right if its more than the current
			return right.subTree(keyOfSubTree);
	}

	/**
	 * this method removes the subtree of the given key, it copies the tree
	 * until it reaches the key, then it just set the subtree as empty trees
	 */
	public Tree<K, V> removeSubTree(K keyOfSubTree) {
		if (keyOfSubTree == null)
			throw new NullPointerException();
		NonEmptyTree<K, V> one = new NonEmptyTree<K, V>(key, value);
		if (key.equals(keyOfSubTree))
			return EmptyTree.getInstance();
		// finds the keyofsubtree within the tree, then it sets it as a
		// emptytree

		one.left = this.left.removeSubTree(keyOfSubTree);
		// copies left subtree
		one.right = this.right.removeSubTree(keyOfSubTree);
		// copites right subtree
		return (Tree<K, V>) one;
	}

	/**
	 * It finds the tree at the most left for the given level. I used a helper
	 * method for this to create a counter that counts the level as the method
	 * goes through every level
	 */
	public K leftMostAtLevel(int level) {
		return leftMostAtLevel(level, 0);
	}

	public K leftMostAtLevel(int level, int count) {
		if (level - 1 == count)
			return key;
		// finds the level and returns the key
		if (count > level)
			return null;
		// if the level goes past the given level, then null is returned
		if (left.leftMostAtLevel(level, count + 1) == null)
			// first check if going left reaches null, if it doesnt, keep going
			// left
			return right.leftMostAtLevel(level, count + 1);
		return left.leftMostAtLevel(level, count + 1);
		// if going left reaches null, then go right
	}

	/**
	 * finds the max value, since the tree is sorted in order, the max value
	 * should be at the end of right side
	 */
	public K max() throws EmptyTreeException {
		try {
			return right.max();// return the max value recursively
		} catch (EmptyTreeException k) {
			return key;
		}
		// it keeps going right until right before it reaches the end, since
		// the empty tree returns an exception

	}

	/**
	 * finds the min value, since the tree is sorted in order, the min value
	 * should be at the end of left side
	 */
	public K min() throws EmptyTreeException {
		try {
			return left.min();// return the min value recursively
		} catch (EmptyTreeException k) {
			return key;
		}
	}

	/**
	 * the method deletes a leaf from the tree, and takes a value from the tree
	 * to balance the tree after the leaf has been deleted.Then it deletes
	 * recursively until the key is deleted and the tree has balanced by being
	 * replaced.
	 * 
	 */
	public Tree<K, V> delete(K keyToDelete) {
		if (keyToDelete == null)
			throw new NullPointerException();
		// checks for null
		if (key.compareTo(keyToDelete) > 0)
			left = left.delete(keyToDelete);
		// looks for the key in the tree, if its smaller than the current key
		// checks left first
		else if (key.compareTo(keyToDelete) < 0)
			right = right.delete(keyToDelete);
		// if its the current is greater than it checks right

		else
			// if the key is equal
			try {
				// this try block catches the exception from the max method
				value = lookup(left.max());// finds the value, and replaces the
				// current value
				key = left.max();
				// replaces the key with max
				left = left.delete(key);
				// then recursively deletes until it reaches the end
			} catch (EmptyTreeException k) {
				try {
					value = lookup(right.min());
					key = right.min();
					right = right.delete(key);
					// same done previously except with right min
				} catch (EmptyTreeException e) {
					return EmptyTree.getInstance();
					// if both max and min has an exception then it returns the
					// empty tree
				}
			}

		return this;
		// return the tree after it has been deteled
	}

}

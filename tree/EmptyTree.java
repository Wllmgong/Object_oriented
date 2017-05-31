package tree;
/**
 * 
 * @author William Gong
 *The emptytree class is called when only the tree or subtree is empty, 
 *containing no key and no values. It is the base case for the recursion methods
 *in the nonemptytree. The class has a private constructor, because it cannot be
 *created outside of the class, and only one exist.
 * @param <K>
 * @param <V>
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class EmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {
	
	private static EmptyTree empty = new EmptyTree();
	//it creates the one and only reference to the emptytree 
	/**
	 * the private constructor
	 */
	private EmptyTree() {

	}
/**
 * the get instance method return the only reference of emptylist class
 * @return
 */
	public static EmptyTree getInstance() {
		return empty;
	}
/**
 * the add method, it creates a new nonemptytree with the given value and
 * returns the new new tree
 */
	public NonEmptyTree<K, V> add(K keyToAdd, V valueToAdd) {
		NonEmptyTree one = new NonEmptyTree(keyToAdd, valueToAdd);
		return one;

	}
/**
 * it return 0 since the empty tree doesnt count as a tree
 */
	public int size() {
		return 0;
	}
/**
 * if the nonemptylist method reaches the emptylist, then the key doesnt exist
 * in the tree
 */
	public V lookup(K keyToLookFor) {
		return null;
	}
/**
 * the emptylist doesnt return any string
 */
	public String toString() {
		
		return "";

	}
/**
 * the copy of the empty tree return as emptytree
 */
	public Tree<K, V> copy() {
		return empty;
	}
/**
 * the subtree of the empty tree return as emptytree
 */
	public Tree<K, V> subTree(K keyOfSubtree) {
		return empty;
	}
/**
 * since the emptytree is empty, it just returns the empty tree
 */
	public Tree<K, V> removeSubTree(K keyOfSubtree) {
		return empty;
	}
/**
 * return null if the recursion reaches to the emptylist
 */
	public K leftMostAtLevel(int level) {
		return null;
	}
/**
* the helper method, so the same as above
*/
	public K leftMostAtLevel(int level, int count)
	{
		return null;
	}
	/**
	 * finds the max values in the list, return the emptytreeexpcetion if no value
	 * is within the tree
	 */
	public K max() throws EmptyTreeException {
		throw new EmptyTreeException();
	}
	/**
	 * finds the min values in the list, return the emptytreeexpcetion if no value
	 * is within the tree
	 */
	public K min() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	public Tree<K, V> delete(K keyToDelete) {
		return empty;
	}

}

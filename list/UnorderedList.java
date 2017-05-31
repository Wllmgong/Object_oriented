package list;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IndexOutOfBoundsException;

/**
 * Name:William Gong 
 * Section:0303 
 * ID:113510311 
 * Directory 
 * ID:wgong1 
 * Honor Code:
 * I pledge on my honor that i have not give or received any unauthorized
 * assistance on this assignment
 * 
 * @author William Gong This class creates a list of elements using link list. I
 *         created a one direction link list that stores all the elements in the
 *         memories.There are several mutator methods that can alter the list by
 *         adding or removing elements. This is an unordered list, when adding
 *         an element, it added at the front of the list. An element must be
 *         defined while creating the class. A Comparator class is required to
 *         create the unordered object.
 *         
 * 
 *
 * @param <T>
 */
public class UnorderedList<T> implements Iterable<T>,
		Comparable<UnorderedList<T>> {

	protected Comparator<T> comparator; // the comparator object needed to
	// compare the elements in the data.
	protected Node<T> head = null;// the head node that stores the 1st element

	// its set as protected because its subclass also uses these fields
	// you may ADD TO this inner class, but not CHANGE what's already here!
	/**
	 * 
	 * @author William Gong This is a Node class that takes in an element and
	 *         stores it.
	 * @param <D>
	 */
	protected class Node<D> {
		D data; // stores the data
		Node<D> next;// stores the next node that contains either null or data

		/**
		 * The node constructor that creates the new node
		 * 
		 * @param data
		 */
		public Node(D data) {
			this.data = data; // stores the data
			next = null; // creates a new next node that contain null
			// represents the end
		}

	}

	/**
	 * The unorderedList constructor that creates a new unordered list object
	 * creates a new head node and takes in a comparator
	 * 
	 * @param comparator
	 */
	public UnorderedList(Comparator<T> comparator) {
		head = null;// creates the head node
		this.comparator = comparator;
		// the comparator that to be able to use comparable

	}

	/**
	 * The add method adds a new element in the form of node first create a new
	 * node and place it as the new head calls on null pointer when newElt is
	 * null
	 * 
	 * @param newElt
	 */
	public void add(T newElt) throws NullPointerException {
		if (newElt == null)// throws null exception
			throw new NullPointerException();
		Node<T> n = new Node<T>(newElt);// creates a new node with the element
		// as the data
		n.next = head;// set the new node as the head
		head = n;

	}

	/**
	 * return the number of the data that is stored
	 * 
	 * @return
	 */
	public int length() {
		int count = 0;// counts the number of data
		Node<T> n = head;// creates the new node as the head
		// so it doesnt alter the original head
		if (n == null) // check if head is null
			return count;

		while (!(n == null)) {
			// the while loop, loops until it reaches the end which is null
			// and counts the amount of time it loops
			count++;
			n = n.next;

		}

		return count;// return count;
	}

	/**
	 * deletes all of the stored data and nodes
	 */
	public void clear() {
		head = null;
	}

	/**
	 * creates a string representation of all the data that is stored
	 */
	public String toString() {
		String str = "";// creates an empty string
		Node<T> n = head;// creates a new node represent the
		if (n == null)
			return str;

		while (!(n == null)) {
			// a while loop that goes through the nodes and
			// takes the data to add them to the string
			str = n.data.toString() + " " + str;
			n = n.next;
		}
		str = str.trim();// gets rid of any spaces at the end
		return str;

	}

	/**
	 * It goes through the nodes and returns the element stored at the location
	 * if it goes outside of the location then it throws a
	 * indexoutofboundexception
	 * 
	 * @param index
	 * @return
	 * @throws IndexOutOfBoundsException
	 */
	public T elementAtPos(int index) throws IndexOutOfBoundsException {

		if (index > this.length() - 1 || index < 0)
			// check if index is within the boundary
			throw new IndexOutOfBoundsException();

		index = this.length() - 1 - index;// it goes backward
		Node<T> n = head;// create the temp node
		while (index > 0) {// loop to count
			index--;
			n = n.next; // the temp node goes to check the next one
		}
		return n.data; // return the data if the position is found
	}

	/**
	 * count the number of occurrences in the element if its null then it throws
	 * a nullpointerexception
	 * 
	 * @param element
	 * @return
	 * @throws NullPointerException
	 */
	public int countElement(T element) throws NullPointerException {
		if (element == null)// check for null;
			throw new NullPointerException();
		int count = 0;// the count
		Node<T> n = head;
		while (!(n == null)) {// the loop going through the link list
			if (n.data.equals(element))// if found, add one to count
				count++;
			n = n.next;// going through the link list
		}
		return count;// returns the count;
	}

	/**
	 * helper method that creates a shallow copy of the head for other
	 * unorderedLists that requires the head unchanged
	 * 
	 * @param list
	 * @return
	 */
	private Node<T> copy(UnorderedList<T> list) {

		UnorderedList<T> k = new UnorderedList<T>(list.comparator);
		UnorderedList<T> n = new UnorderedList<T>(list.comparator);
		// creates a new unorderedlist
		Node<T> copy = list.head;
		while (!(copy == null)) {
			k.add(copy.data);
			copy = copy.next;
			// copies only the data and it doesnt affect the node
		}
		// requires two loops in order to copy the list
		while (!(k.head == null)) {
			n.add(k.head.data);
			k.head = k.head.next;

		}

		return n.head;
	}

	/**
	 * helper method reverse copy, creates a copy of the node in the reverse
	 * directions even though its not needed, it helps iterate through the node
	 * in the correct direction.
	 * 
	 * @param list
	 * @return
	 */
	protected Node<T> reverseCopy(UnorderedList<T> list) {
		UnorderedList<T> k = new UnorderedList<T>(list.comparator);
		Node<T> copy = list.head;
		// creates a head and unorderedlist to be copied
		while (!(copy == null)) {
			k.add(copy.data);
			copy = copy.next;
			// copies the data and not the node
		}
		// only requires one loop

		return k.head;
	}

	/**
	 * this method adds all of the otherlist's data into this link list. It
	 * takes the nodes from the other list, then it creates a copy of the data
	 * so it wont change the nodes in the other list. then its added using the
	 * add method to the current link list if the unorderedList is nulll, then
	 * throws nullpointerexception
	 * 
	 * @param otherList
	 * @throws NullPointerException
	 */
	public void append(UnorderedList<T> otherList) throws NullPointerException {
		if (otherList == null)// checks for null
			throw new NullPointerException();
		Node<T> k = reverseCopy(otherList);// reverse copy because it adds the
		// node in the reverse direction
		Node<T> n = k;
		// create a temp node, n
		if (!(n == null)) {
			this.add(n.data);// goes through the copies linklist of the other
								// list and adds them to this linklist
			while (!(n.next == null)) {
				n = n.next;
				this.add(n.data);
			}

		}

	}

	/**
	 * the compareto method compares this and another unorderedlist. it first
	 * checks if the elements are small larger or equals to eachother return a
	 * negative positive or 0 depending on the result if the element are the
	 * same until one has runs out of nodes, then it check the length of both of
	 * the linklist and return positive negative or 0 depending on the length
	 * the other list is null throws NoPointerException if
	 */
	public int compareTo(UnorderedList<T> otherList)
			throws NullPointerException {
		if (otherList == null)// checks null
			throw new NullPointerException();
		Node<T> n = head;// temp node for head
		Node<T> o = copy(otherList);// shallow copy node for otherlist

		while (!(n == null) && !(o == null)) {
			// the while loop checks each element to see if one is small or
			// larger or equal
			if (comparator.compare(n.data, o.data) > 0) {
				return 1;
				// if this is larger
			}
			if (comparator.compare(n.data, o.data) < 0) {
				return -1;
				// if this is smaller
			}
			n = n.next;
			o = o.next;
		}
		// if its neither smaller or larger between the elements then it check
		// size

		if (this.length() == otherList.length())// if both are equal size
			return 0;

		if (this.length() > otherList.length())// if this is longer
			return 1;
		else
			return -1; // if this is shorter

	}

	/**
	 * 
	 * @author William Gong This is a named inner class that implements iterator
	 *         the iterator allows the program to loop through the list and
	 *         return each element
	 */
	private class MyIterator implements Iterator<T> {
		Node<T> k; // temp node for myIterator

		/**
		 * construct a linklist
		 * 
		 * @param temp
		 */
		public MyIterator(UnorderedList<T> temp) {
			k = temp.reverseCopy(temp);// creates a reversecopy of the list
		}

		/**
		 * hasnext checks if there is another element stored in the list
		 */
		public boolean hasNext() {

			return k == null ? false : true;
			// if there is another element return true, else returns false
		}

		/**
		 * next returns the next on the list element throws
		 * NoSuchElementException if there are not elements left
		 */
		public T next() throws NoSuchElementException {
			if (hasNext()) {// uses has next to check is there is another
							// element
				T temp = null;
				temp = k.data;// goes to the next element
				k = k.next;
				return temp;// returns the element
			} else
				// if there is no more element then throws exception
				throw new NoSuchElementException();
		}

		// does nothing
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	/**
	 * the iterator() creates an iterator object from the innerclass
	 */
	public Iterator<T> iterator() {
		// return the iterator object for this class
		return new MyIterator(this);

	}

	/**
	 * this method removes specified element for num number of times from the
	 * list if the num is larger than the element, then it removes all the
	 * specified element in the list. If element is null, then throws
	 * NUllPointerException it return an int that counts the number of elements
	 * removed
	 * 
	 * @param element
	 * @param num
	 * @return
	 * @throws NullPointerException
	 */
	public int removeNumOccurrences(T element, int num)
			throws NullPointerException {
		if (element == null)// checks null
			throw new NullPointerException();
		int count = 0;
		Node<T> t = reverseCopy(this); // creates a reversecopy of the list
		Node<T> temp = t;// a temp node
		if (t == null || num == 0)
			return count;
		// check if the list is null or num is 0, if so return 0

		while (count < num && temp != null && !(temp.next == null)) {
			// the loop goes through the list while check if the element is the
			// same as the specified element
			if (comparator.compare(temp.next.data, element) == 0) {
				// if so then removes it from the list
				Node<T> k = temp.next;
				temp.next = k.next;
				count++;
			} else {
				// else it goes onto the next element
				temp = temp.next;
			}
		}

		if (temp.next == null && count < num) {
			// if it reaches to the end of the list
			// and its it be remove
			// then sets the last element as null
			if (comparator.compare(temp.data, element) == 0 && count < num) {
				temp = null;
				count++;
			}
		}

		if ((t != null) && comparator.compare(t.data, element) == 0
				&& count < num) {
			// check the first element because it wasnt checked in the loop
			t = t.next;
			count++;
		}
		// this is reversed copy so that it the first element is the last and
		// the last is the first

		UnorderedList<T> one = new UnorderedList<T>(comparator);
		one.head = t;
		head = one.reverseCopy(one);
		// reverse the list again so that it become the correct one

		return count;// return the number of occurrences

	}

	/**
	 * removes elements from one location fromPos to another location toPos
	 * 
	 * @param fromPos
	 * @param toPos
	 */
	public void removeRange(int fromPos, int toPos) {

		Node<T> t = reverseCopy(this);// creates a reversecopy of the list
		Node<T> temp = t;// a temp node
		int loc = 0;
		if (temp == null)
			return;
		// checks null
		if (toPos - fromPos < 0) {
			return;
		}
		// check if the range is larger than 0

		if (fromPos <= 0)
			fromPos = 0;
		// if fromPos is less than 1, sets it to 1
		if (toPos > this.length() - 1)
			toPos = this.length() - 1;
		// if toPos is larger than the size, then set it to end
		if (fromPos < toPos) {
			while (!(loc == fromPos - 1) && temp.next != null && fromPos != 0) {
				//the loop loops to the fromPos
				temp = temp.next;
				loc++;
			}
			while (loc < toPos && temp.next != null) {
				//fromPos to toPos, removes all elements
				Node<T> k = temp.next;
				temp.next = k.next;
				loc++;
			}
			
			if (fromPos == 0)
				t = t.next;
			//check the end if fromPos is 0, if so remove the beginning
			UnorderedList<T> one = new UnorderedList<T>(comparator);
			one.head = t;
			//reorder the list
			head = one.reverseCopy(one);//set the new list to head

		}
	}

}

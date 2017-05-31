package list;

import java.lang.Iterable;
import java.util.Comparator;

import list.UnorderedList.Node;

/**
 * orderedList creates a UnorderList but the order of the element are from
 * smallest to largest. It takes the method add, countElements, and removeNumOcc
 * from the unorderedlist to fit the orderedlist.
 * Can use methods from unorderedList and fields
 * @author William Gong
 *
 * @param <T>
 */
public class OrderedList<T> extends UnorderedList<T> {
/**
 * the constructor that takes in the comparator and calls super for the
 * unorderedlist
 * @param comparator
 */
	public OrderedList(Comparator<T> comparator) {
		super(comparator);

	}

	/*
	 * Implement (i.e., override) whatever methods from the superclass that you
	 * find are necessary to have this list be sorted, as well as any methods
	 * that would be more efficient if overridden in this subclass.
	 */
	/**
	 * the add method adds the new elements in order 
	 * throws NUllPointerException if newElt is null
	 */
	public void add(T newElt) throws NullPointerException {
		if (newElt == null)//check null
			throw new NullPointerException();
		Node<T> temp = new Node<T>(newElt);//node for the new elt
		Node<T> replace = head;//temp node

		if (replace == null) {
			super.add(temp.data);
			//adds the node if its the first one
			return;
		} else {
			Node<T> previ = head;
			replace = head.next;
			if (comparator.compare(temp.data, previ.data) >= 0) {
				//then it compares the nodes with in the list
				//if its at the end then it calls super
				super.add(temp.data);
				return;
			}

			while (!(replace == null)) {
				if (comparator.compare(temp.data, replace.data) >= 0) {
					temp.next = replace;
					previ.next = temp;
					// if its add infront of the another node
					return;
				}

				previ = replace;
				replace = replace.next;//loops
			}
			
			temp.next = replace;
			previ.next = temp;
			//sets the nodes
		}

	}
/**
 * more efficient version to count the specified elements for orderedlist
 */
	public int countElement(T element) throws NullPointerException {
		if (element == null)//checks null
			throw new NullPointerException();

		Node<T> temp = new Node<T>(element);
		Node<T> replace = head;
		int count = 0;

		if (replace == null)//check if the list is empty
			return count;

		while (!(replace.next == null)
				//finds the first element it equals
				&& comparator.compare(replace.data, temp.data) != 0) {
			replace = replace.next;
		}
		if (replace.next == null
				&& comparator.compare(replace.data, temp.data) != 0) {
			//if none is equal then return 0
			return count;
		} else {
			
			while (!(replace.next == null)
					// if there are elements to count then it goes through
					//the list until it hits a different element
					&& comparator.compare(replace.data, temp.data) == 0) {
				replace = replace.next;
				count++;
			}
		}

		return count;//return the number of elements
	}
/**
 * a more efficient version of removeNumOccurrences for orderedList
 */
	public int removeNumOccurrences(T element, int num)
			throws NullPointerException {
		if (element == null) //checks null
			throw new NullPointerException();
		Node<T> temp = new Node<T>(element);
		Node<T> t = super.reverseCopy(this);//uses reversecopy
		Node<T> replace = t;
		int count = 0;

		if (replace == null)
			return count;//check if the first element is null

		while (!(replace.next == null)//loops until it reaches the first element
				&& comparator.compare(replace.data, temp.data) < 0) {
			replace = replace.next;
		}
		
		if (replace.next == null
				// if it reaches the end instead then it returns 0
				&& comparator.compare(replace.data, temp.data) < 0) {
			return count;
		}

		while (!(replace.next == null)
				&& comparator.compare(replace.data, temp.data) == 0
				&& count < num) {
			// goes through the elements and remove the ones it equals
			Node<T> k = replace.next;
			replace.next = k.next;
			count++;
		}
		if (comparator.compare(t.data, temp.data) == 0 && count < num) {
			//checks the first element because it is skipped in the loop
			t = t.next;
			count++;

		}
		//reverse copy so the first is the last
		UnorderedList<T> one = new UnorderedList<T>(comparator);
		one.head = t;
		head = one.reverseCopy(one);
		//reorganized the list
		return count;

	}

}

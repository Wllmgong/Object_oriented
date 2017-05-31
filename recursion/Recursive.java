package recursion;

import java.util.ArrayList;
import java.util.List;

public class Recursive {

	public static <T extends Comparable<T>> boolean hasNoneSmaller(
			List<T> list, T elt) {
		
		if (list== null || elt ==null)
			throw new NullPointerException();
		if (list.size() == 0)
			return true;

		if (list.get(list.size() - 1).compareTo(elt) < 0)
			return false;

		list.remove(list.size() - 1);
		List<T> k = list;
		return hasNoneSmaller(k, elt);

	}

	public static <T> int lastIdxOf(List<T> list, T element) {
		if (list== null || element ==null)
			throw new NullPointerException();

		if (list.size() <= 0)
			return -1;
		if (list.get(list.size() - 1).equals(element))
			return list.size() - 1;

		list.remove(list.size() - 1);
		List<T> k = list;
		return lastIdxOf(k, element);

	}

	public static <T> List<T> insertAfter(List<T> list, T elt, T newElt) {
		List<T> k = new ArrayList<T>();
		if (list== null || elt==null || newElt ==null)
			throw new NullPointerException();

		return insert(list, elt, newElt, 0, k);

	}

	private static <T> List<T> insert(List<T> list, T elt, T newElt,
			int position, List<T> k) {
		if (list== null || elt==null || newElt ==null)
			throw new NullPointerException();
		if (list.size() == 0 || position > list.size() - 1)
			return k;

		k.add(list.get(position));

		if (list.get(position).equals(elt)) {
			k.add(newElt);
		}

		return insert(list, elt, newElt, position + 1, k);

	}

	public static <T> List<T> removeNumTimes(List<T> list, T elt, int num) {

		List<T> k = new ArrayList<T>();

		return remove(list, elt, num, 0, k);

	}

	private static <T> List<T> remove(List<T> list, T elt, int num,
			int position, List<T> k) {
		if (list== null || elt==null)
			throw new NullPointerException();
		if (list.size() == 0 || position > list.size() - 1)
			return k;
		if (!(list.get(position).equals(elt)) || num <= 0) {
			k.add(list.get(position));
		} else {
			num--;
		}

		return remove(list, elt, num, position + 1, k);

	}

}

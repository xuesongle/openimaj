package org.openimaj.util.list;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 * A read-only {@link List} view built on top of a list of underlying lists.
 * Useful for combining multiple lists without the memory overhead of actually
 * creating new storage for all the elements.
 * <p>
 * Be aware that if you change the underlying lists, then those changes will be
 * reflected in this list.
 * 
 * @author Jonathon Hare (jsh2@ecs.soton.ac.uk)
 * 
 * @param <T>
 */
public class ConcatenatedList<T> extends AbstractList<T> {
	private List<List<T>> innerLists = new ArrayList<List<T>>();

	/**
	 * Construct with the given list of lists.
	 * 
	 * @param lists
	 *            the lists
	 */
	public ConcatenatedList(List<List<T>> lists) {
		innerLists = lists;
	}

	/**
	 * Construct with the given lists.
	 * 
	 * @param l1
	 *            first list
	 * @param l2
	 *            second list
	 */
	public ConcatenatedList(List<T> l1, List<T> l2) {
		innerLists.add(l1);
		innerLists.add(l2);
	}

	/**
	 * Construct with the given lists.
	 * 
	 * @param l1
	 *            first list
	 * @param l2
	 *            second list
	 * @param l3
	 *            third list
	 */
	public ConcatenatedList(List<T> l1, List<T> l2, List<T> l3) {
		innerLists.add(l1);
		innerLists.add(l2);
		innerLists.add(l3);
	}

	/**
	 * Construct with the given lists.
	 * 
	 * @param l1
	 *            first list
	 * @param l2
	 *            second list
	 * @param l3
	 *            third list
	 * @param l4
	 *            forth list
	 */
	public ConcatenatedList(List<T> l1, List<T> l2, List<T> l3, List<T> l4) {
		innerLists.add(l1);
		innerLists.add(l2);
		innerLists.add(l3);
		innerLists.add(l4);
	}

	@Override
	public T get(int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException();

		int count = 0;

		for (final List<T> d : innerLists) {
			if (index >= count + d.size()) {
				count += d.size();
			} else {
				return d.get(index - count);
			}
		}

		throw new IndexOutOfBoundsException();
	}

	@Override
	public int size() {
		int size = 0;
		for (final List<T> d : innerLists) {
			size += d.size();
		}
		return size;
	}
}

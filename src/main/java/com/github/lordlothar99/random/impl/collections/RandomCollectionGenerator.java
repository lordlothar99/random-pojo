/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.github.lordlothar99.random.impl.AbstractContainerGenerator;

/**
 * Random {@link Collection} generator
 * 
 * @author Francois Lecomte
 * @param <T>
 *            {@link Collection}
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RandomCollectionGenerator<T extends Collection> extends AbstractContainerGenerator<T> {

	public RandomCollectionGenerator(Class<T> collectionType) {
		super(collectionType);
	}

	public RandomCollectionGenerator(Class<T> collectionType, Class<?> elementsType) {
		super(collectionType, elementsType);
	}

	@Override
	protected boolean append(T container, int elementIndex) {
		Object element = getToolkit().generate(getElementTypes()[0]);
		boolean success = container.add(element);
		return success;
	}

	@Override
	protected T newContainer(int size) {
		if (!getObjectClass().isInterface()) {
			try {
				return getObjectClass().getConstructor().newInstance();
			} catch (final Exception e) {
				throw new RuntimeException("Unable to instantiate container type '" + getObjectClass().getName() + "'",
						e);
			}
		}
		Object instance = null;
		if (SortedSet.class.isAssignableFrom(getObjectClass())) {
			instance = new TreeSet<Object>();
		} else if (Set.class.isAssignableFrom(getObjectClass())) {
			instance = new HashSet<Object>(size);
		} else if (List.class.isAssignableFrom(getObjectClass())) {
			instance = new ArrayList<Object>(size);
		} else if (Queue.class.isAssignableFrom(getObjectClass())) {
			instance = new LinkedList<Object>();
		} else {
			// default
			instance = new ArrayList<Object>(size);
		}

		return (T) instance;
	}

	@Override
	protected int size(T container) {
		return container.size();
	}
}

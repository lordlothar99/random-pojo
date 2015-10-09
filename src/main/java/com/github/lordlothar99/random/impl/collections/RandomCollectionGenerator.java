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

import com.github.lordlothar99.random.api.Generator;

/**
 * Random {@link Collection} generator
 * 
 * @author Francois Lecomte
 * @param <T>
 *            {@link Collection}
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RandomCollectionGenerator<T extends Collection> extends AbstractContainerGenerator<T> {

	public RandomCollectionGenerator(Class<? extends T> collectionType) {
		super(collectionType);
	}

	public RandomCollectionGenerator(Class<? extends T> collectionType, Class<?> elementsType) {
		super(collectionType, elementsType);
	}

	public RandomCollectionGenerator(Class<? extends T> collectionType, Generator<?> elementsGenerators) {
		super(collectionType, elementsGenerators);
	}

	@Override
	protected boolean append(T container, int elementIndex, Object element) {
		boolean success = container.add(element);
		return success;
	}

	@Override
	protected T newContainer(int size) {
		Class<? extends T> containerClass = getObjectClass();
		logger.debug("Instantiating a new container for type '{}' for {} elements", containerClass, size);
		if (!containerClass.isInterface()) {
			try {
				return containerClass.getConstructor().newInstance();
			} catch (final Exception e) {
				throw new RuntimeException("Unable to instantiate container type '" + containerClass.getName() + "'",
						e);
			}
		}
		Object instance = null;
		if (SortedSet.class.isAssignableFrom(containerClass)) {
			instance = new TreeSet<Object>();
		} else if (Set.class.isAssignableFrom(containerClass)) {
			instance = new HashSet<Object>(size);
		} else if (List.class.isAssignableFrom(containerClass)) {
			instance = new ArrayList<Object>(size);
		} else if (Queue.class.isAssignableFrom(containerClass)) {
			instance = new LinkedList<Object>();
		} else {
			// default
			instance = new ArrayList<Object>(size);
		}
		logger.debug("Instantiated container : {}", instance);

		return (T) instance;
	}

	@Override
	protected int size(T container) {
		return container.size();
	}
}

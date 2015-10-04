/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.collections;

import java.lang.reflect.Array;
import java.util.Collection;

import com.github.lordlothar99.random.impl.AbstractContainerGenerator;

/**
 * Random {@link Collection} generator
 * 
 * @author Francois Lecomte
 * @param <T>
 *            array type
 */
@SuppressWarnings("unchecked")
public class RandomArrayGenerator<T> extends AbstractContainerGenerator<T> {

	public RandomArrayGenerator(Class<T> arrayClass) {
		super(arrayClass, arrayClass.getComponentType());
		if (!arrayClass.isArray()) {
			throw new IllegalArgumentException("Class must be array-class : " + arrayClass);
		}
	}

	@Override
	protected boolean append(T container, int elementIndex, Object element) {
		Array.set(container, elementIndex, element);
		return true;
	}

	@Override
	protected T newContainer(int size) {
		T array = (T) Array.newInstance(getElementsTypes()[0], size);
		return array;
	}

	@Override
	protected int size(T container) {
		int size = Array.getLength(container);
		return size;
	}

}

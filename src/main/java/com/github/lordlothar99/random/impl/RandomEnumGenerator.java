/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import org.apache.commons.lang.math.RandomUtils;

/**
 * Random {@link Enum} generator
 * 
 * @author Francois Lecomte
 * @param <E>
 *            {@link Enum}
 */
public class RandomEnumGenerator<E extends Enum<E>> extends AbstractGenerator<E> {

	public RandomEnumGenerator(Class<E> clazz) {
		super(clazz);
	}

	public E create() {
		final E[] enumConstants = getObjectClass().getEnumConstants();
		final int index = RandomUtils.nextInt(enumConstants.length);

		return enumConstants[index];
	}
}

package com.github.lordlothar99.random.impl.element;

import static org.apache.commons.collections.CollectionUtils.size;

import org.apache.commons.collections.CollectionUtils;

import com.github.lordlothar99.random.impl.AbstractGenerator;

public class RandomElementGenerator<T> extends AbstractGenerator<T> {

	private Object container;

	public RandomElementGenerator(Object container) {
		this.container = container;
	}

	@SuppressWarnings("unchecked")
	public T create() {
		int size = size(container);
		int index = toolkit().intValue(0, size - 1);
		return (T) CollectionUtils.get(container, index);
	}

}

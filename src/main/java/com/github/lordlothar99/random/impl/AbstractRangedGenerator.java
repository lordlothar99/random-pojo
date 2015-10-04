package com.github.lordlothar99.random.impl;

import com.github.lordlothar99.random.api.RangedGenerator;

public abstract class AbstractRangedGenerator<T> extends AbstractGenerator<T>implements RangedGenerator<T> {

	private T min;
	private T max;

	public AbstractRangedGenerator(T min, T max, Class<? extends T> clazz) {
		super(clazz);
		this.min = min;
		this.max = max;
	}

	public AbstractRangedGenerator(T min, T max) {
		super();
		this.min = min;
		this.max = max;
	}

	public void setMin(T min) {
		this.min = min;
	}

	public void setMax(T max) {
		this.max = max;
	}

	public T getMin() {
		return min;
	}

	public T getMax() {
		return max;
	}
}

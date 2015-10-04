package com.github.lordlothar99.random.api;

public interface RangedGenerator<T> extends Generator<T> {

	void setMin(T min);

	void setMax(T max);

	T getMin();

	T getMax();
}

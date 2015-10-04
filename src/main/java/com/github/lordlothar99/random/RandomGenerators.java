package com.github.lordlothar99.random;

import com.github.lordlothar99.random.api.RangedGenerator;

public class RandomGenerators {

	private RandomGeneratorsRegistry registry;

	public RandomGenerators() {
		this(new RandomGeneratorsRegistry());
	}

	public RandomGenerators(RandomGeneratorsRegistry registry) {
		super();
		this.registry = registry;
	}

	public RangedGenerator<Integer> integer() {
		return (RangedGenerator<Integer>) registry.getGenerator(Integer.class);
	}

	public RangedGenerator<Integer> integer(int min, int max) {
		RangedGenerator<Integer> generator = integer();
		generator.setMin(min);
		generator.setMax(max);
		return generator;
	}
}

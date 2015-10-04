package com.github.lordlothar99.random;

import com.github.lordlothar99.random.api.Generator;
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

	public RangedGenerator<Integer> integerGenerator() {
		return (RangedGenerator<Integer>) registry.getGenerator(Integer.class);
	}

	public RangedGenerator<Integer> integerGenerator(int min, int max) {
		RangedGenerator<Integer> generator = integerGenerator();
		generator.setMin(min);
		generator.setMax(max);
		return generator;
	}

	public RangedGenerator<Long> longGenerator(long min, long max) {
		RangedGenerator<Long> generator = longGenerator();
		generator.setMin(min);
		generator.setMax(max);
		return generator;
	}

	public RangedGenerator<Long> longGenerator() {
		return (RangedGenerator<Long>) registry.getGenerator(Long.class);
	}
}

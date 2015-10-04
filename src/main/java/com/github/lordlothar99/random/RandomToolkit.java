/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random;

import com.github.lordlothar99.random.api.Generator;

/**
 * Toolkit for {@link Generator}.
 * 
 * @author Francois Lecomte
 */
public final class RandomToolkit {

	private static final RandomToolkit INSTANCE = new RandomToolkit();

	private RandomGeneratorsRegistry registry;
	private RandomGenerators generators;

	public RandomToolkit() {
		this(new RandomGeneratorsRegistry());
	}

	public RandomToolkit(RandomGeneratorsRegistry registry) {
		super();
		this.registry = registry;
		this.generators = new RandomGenerators(registry);
	}

	public static RandomToolkit get() {
		return INSTANCE;
	}

	public <T> T generate(Class<T> type) {
		Generator<T> generateur = registry.getGenerator(type);
		return generateur.create();
	}

	public Object[] generate(Class<?>[] types) {
		Object[] values = new Object[types.length];
		for (int i = 0; i < values.length; i++) {
			Class<?> type = types[i];
			values[i] = generate(type);
		}
		return values;
	}

	public RandomGenerators generators() {
		return generators;
	}

	public RandomGeneratorsRegistry registry() {
		return registry;
	}

	public int integerValue() {
		return generators.integerGenerator().create();
	}

	public int integerValue(int min, int max) {
		return generators.integerGenerator(min, max).create();
	}

	public long longValue() {
		return generators.longGenerator().create();
	}

	public long longValue(long min, long max) {
		return generators.longGenerator(min, max).create();
	}
}

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

	private RandomGeneratorsRegistry registry = new RandomGeneratorsRegistry();

	public RandomToolkit() {
	}

	public static RandomToolkit get() {
		return INSTANCE;
	}

	public void setRegistry(RandomGeneratorsRegistry registry) {
		this.registry = registry;
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
}

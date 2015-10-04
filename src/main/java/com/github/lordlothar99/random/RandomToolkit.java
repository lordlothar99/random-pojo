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

	private RandomGenerators registry = new RandomGenerators();

	public RandomToolkit() {
	}

	public static RandomToolkit get() {
		return INSTANCE;
	}

	public void setRegistry(RandomGenerators registry) {
		this.registry = registry;
	}

	public <T> T generate(Class<T> type) {
		Generator<T> generateur = registry.getInstance(type);
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

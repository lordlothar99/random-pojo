/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.collections.IterableMap;
import org.apache.commons.collections.map.LinkedMap;

import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.impl.AbstractContainerGenerator;

/**
 * Random {@link Map} generator
 * 
 * @author Francois Lecomte
 * @param <T>
 *            {@link Map}
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RandomMapGenerator<T extends Map> extends AbstractContainerGenerator<T> {

	public RandomMapGenerator(Class<? extends T> mapClass) {
		super(mapClass);
	}

	public RandomMapGenerator(Class<? extends T> mapClass, Class<?> keyType, Class<?> valueType) {
		super(mapClass, keyType, valueType);
	}

	protected RandomMapGenerator(Class<? extends T> mapClass, Generator<?> keyGenerator, Generator<?> valueGenerator) {
		super(mapClass, keyGenerator, valueGenerator);
	}

	@Override
	protected boolean append(T container, int elementIndex, Object element) {
		Object key = elementGenerator(0).create();
		Object value = elementGenerator(1).create();
		container.put(key, value);
		return true;
	}

	@Override
	protected T newContainer(int size) {
		Class<? extends T> mapClass = getObjectClass();
		if (!mapClass.isInterface()) {
			try {
				return mapClass.getConstructor().newInstance();
			} catch (final Exception e) {
				throw new RuntimeException("Unable to instantiate container type  '" + mapClass.getName() + "'", e);
			}
		}
		Object instance = null;
		if (SortedMap.class.isAssignableFrom(mapClass)) {
			instance = new TreeMap<Object, Object>();
		} else if (IterableMap.class.isAssignableFrom(mapClass)) {
			instance = new LinkedMap();
		} else {
			// default
			instance = new HashMap<Object, Object>();
		}

		return (T) instance;
	}

	@Override
	protected int size(T container) {
		return container.size();
	}

	// public T create() {
	// // new instance
	// final T map = newInstance();
	//
	// // generate values if possible
	// final Class<?>[] genericTypes = getElementTypes();
	// if (genericTypes != null && genericTypes.length > 1) {
	// final Class<?> genericTypeKey = genericTypes[0];
	// final Class<?> genericTypeValue = genericTypes[1];
	// // random size
	// final RandomIntegerGenerator randomIntegerGenerator = new
	// RandomIntegerGenerator(5);
	// final int elementsCount = randomIntegerGenerator.create() + 2;
	// // random fill
	// for (int i = 0; i < elementsCount; i++) {
	// map.put(RandomToolkit.get().generate(genericTypeKey),
	// RandomToolkit.get().generate(genericTypeValue));
	// }
	// }
	// return map;
	// }

}

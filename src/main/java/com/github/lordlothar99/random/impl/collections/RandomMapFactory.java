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

import com.github.lordlothar99.random.RandomFactoryToolkit;
import com.github.lordlothar99.random.impl.AbstractGenericFactory;
import com.github.lordlothar99.random.impl.numeric.RandomIntegerFactory;

/**
 * Generateur aleatoire de {@link Map}.
 * 
 * @author Francois Lecomte
 * @param <O> {@link Map}
 */
@SuppressWarnings({"unchecked", "rawtypes" })
public class RandomMapFactory<O extends Map> extends AbstractGenericFactory<O> {

    /**
     * Constructeur
     * 
     * @param theClazz {@link Class} de {@link Map}
     */
    public RandomMapFactory(Class<O> theClazz) {
        super(theClazz);
    }

    /**
     * Constructeur
     * 
     * @param clazz {@link Class} de {@link Map}
     * @param genericTypeKey Type generique pour les cles de la {@link Map}
     * @param genericTypeValue Type generique pour les valeurs de la {@link Map}
     */
    public RandomMapFactory(Class<O> clazz, Class< ? > genericTypeKey, Class< ? > genericTypeValue) {
        super(clazz, genericTypeKey, genericTypeValue);
    }

    /**
     * Constructeur
     * 
     * @param clazz {@link Class} de {@link Map}
     * @param genericTypes Types generiques
     */
    protected RandomMapFactory(Class<O> clazz, Class< ? >... genericTypes) {
        super(clazz, genericTypes);
    }

    /**
     * {@inheritDoc}
     */
    public O create() {
        // new instance
        final O map = newInstance();

        // generate values if possible
        final Class< ? >[] genericTypes = getGenericTypes();
        if (genericTypes != null && genericTypes.length > 1) {
            final Class< ? > genericTypeKey = genericTypes[0];
            final Class< ? > genericTypeValue = genericTypes[1];
            // random size
            final RandomIntegerFactory randomIntegerFactory = new RandomIntegerFactory(5);
            final int elementsCount = randomIntegerFactory.create() + 2;
            // random fill
            for (int i = 0; i < elementsCount; i++) {
                map.put(RandomFactoryToolkit.generate(genericTypeKey), RandomFactoryToolkit.generate(genericTypeValue));
            }
        }
        return map;
    }

    /**
     * @return new instance of {@link O}
     */
    protected O newInstance() {
        if (!getObjectClass().isInterface()) {
            try {
                return getObjectClass().getConstructor().newInstance();
            } catch (final Exception e) {
                throw new RuntimeException("Impossible d'instantier un objet de type '" + getObjectClass().getName()
                                + "'", e);
            }
        }
        Object instance = null;
        if (SortedMap.class.isAssignableFrom(getObjectClass())) {
            instance = new TreeMap<Object, Object>();
        } else if (IterableMap.class.isAssignableFrom(getObjectClass())) {
            instance = new LinkedMap();
        } else {
            // default
            instance = new HashMap<Object, Object>();
        }

        return (O ) instance;
    }

}

/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.github.lordlothar99.random.RandomToolkit;
import com.github.lordlothar99.random.impl.AbstractGenericGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomIntegerGenerator;

/**
 * Generateur aleatoire de {@link Collection}.
 * 
 * @author Francois Lecomte
 * @param <O> {@link Collection}
 */
@SuppressWarnings({"unchecked", "rawtypes" })
public class RandomCollectionGenerator<O extends Collection> extends AbstractGenericGenerator<O> {

    /**
     * max elements count in collection ; default is 5
     */
    private int maxElementsCount = 5;

    /**
     * Constructeur
     * 
     * @param theClazz {@link Class} de {@link Collection}
     */
    public RandomCollectionGenerator(Class<O> theClazz) {
        super(theClazz);
    }

    /**
     * Constructeur
     * 
     * @param clazz {@link Class} de {@link Collection}
     * @param genericType Type generique des donnees de la {@link Collection}.
     */
    public RandomCollectionGenerator(Class<O> clazz, Class< ? > genericType) {
        super(clazz, genericType);
    }

    /**
     * {@inheritDoc}
     */
    public O create() {
        // new instance
        final O collection = newInstance();

        // generate values if possible
        final Class< ? >[] genericTypes = getGenericTypes();
        if (genericTypes != null && genericTypes.length > 0) {
            final Class< ? > genericType = genericTypes[0];
            // random size
            final RandomIntegerGenerator randomIntegerGenerator = new RandomIntegerGenerator(maxElementsCount);
            final int elementsCount = Math.max(randomIntegerGenerator.create(), 2);
            // random fill
            for (int i = 0; i < elementsCount; i++) {
                collection.add(RandomToolkit.get().generate(genericType));
            }
        }
        return collection;
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
        if (SortedSet.class.isAssignableFrom(getObjectClass())) {
            instance = new TreeSet<Object>();
        } else if (Set.class.isAssignableFrom(getObjectClass())) {
            instance = new HashSet<Object>();
        } else if (List.class.isAssignableFrom(getObjectClass())) {
            instance = new ArrayList<Object>();
        } else if (Queue.class.isAssignableFrom(getObjectClass())) {
            instance = new LinkedList<Object>();
        } else {
            // default
            instance = new ArrayList<Object>();
        }

        return (O ) instance;
    }

    /**
     * @param maxElementsCount the maxElementsCount to set
     */
    public void setMaxElementsCount(int maxElementsCount) {
        this.maxElementsCount = maxElementsCount;
    }
}
/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.collections;

import java.lang.reflect.Array;
import java.util.Collection;

import com.github.lordlothar99.random.RandomToolkit;
import com.github.lordlothar99.random.impl.AbstractGenerator;
import com.github.lordlothar99.random.impl.numeric.RandomIntegerGenerator;

/**
 * Random {@link Collection} generator
 * 
 * @author Francois Lecomte
 * @param <O> Type du tableau
 */
public class RandomArrayGenerator<O> extends AbstractGenerator<O> {

    /**
     * max elements count in array ; default is 5
     */
    private int maxElementsCount = 5;

    /**
     * Constructeur
     * 
     * @param arrayClass Type du tableau
     */
    public RandomArrayGenerator(Class<O> arrayClass) {
        super(arrayClass);
        if (!arrayClass.isArray()) {
            throw new IllegalArgumentException("Class must be array-class : " + arrayClass);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public O create() {
        // random size
        final RandomIntegerGenerator randomIntegerGenerator = new RandomIntegerGenerator(maxElementsCount);
        final int elementsCount = Math.max(randomIntegerGenerator.create(), 2);
        final Object array = Array.newInstance(getObjectClass().getComponentType(), elementsCount);
        // random fill
        for (int i = 0; i < elementsCount; i++) {
            Array.set(array, i, RandomToolkit.get().generate(getObjectClass().getComponentType()));
        }
        return (O ) array;
    }

    /**
     * @param maxElementsCount the maxElementsCount to set
     */
    public void setMaxElementsCount(int maxElementsCount) {
        this.maxElementsCount = maxElementsCount;
    }
}

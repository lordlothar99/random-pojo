/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import org.apache.commons.lang.math.RandomUtils;

/**
 * Generateur aleatoire d'enum.
 * 
 * @author Francois Lecomte
 * @param <E> {@link Enum}
 */
public class RandomEnumGenerator<E extends Enum<E>> extends AbstractGenerator<E> {

    /**
     * Constructeur
     * 
     * @param clazz {@link Class}
     */
    public RandomEnumGenerator(Class<E> clazz) {
        super(clazz);
    }

    /**
     * {@inheritDoc}
     */
    public E create() {

        // recuperation des valeurs possibles
        final E[] enumConstants = getObjectClass().getEnumConstants();

        // generation d'un index aleatoire
        final int index = RandomUtils.nextInt(enumConstants.length);

        return enumConstants[index];
    }
}

/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lothar.random;

import org.apache.commons.lang.math.RandomUtils;

/**
 * Generateur aleatoire de {@link Boolean}.
 * 
 * @author Francois Lecomte
 */
public class RandomBooleanFactory implements Factory<Boolean> {

    /**
     * Constructeur
     */
    public RandomBooleanFactory() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public Boolean create() {
        return RandomUtils.nextBoolean();
    }

}

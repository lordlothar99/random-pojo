/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Factory;

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

/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Generator;

/**
 * Generateur aleatoire de {@link Boolean}.
 * 
 * @author Francois Lecomte
 */
public class RandomBooleanGenerator implements Generator<Boolean> {

    /**
     * Constructeur
     */
    public RandomBooleanGenerator() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    public Boolean create() {
        return RandomUtils.nextBoolean();
    }

}

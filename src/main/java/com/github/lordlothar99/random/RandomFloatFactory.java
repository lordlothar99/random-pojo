/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random;

import org.apache.commons.lang.math.RandomUtils;

/**
 * Generateur aleatoire de {@link Float}.
 * 
 * @author Francois Lecomte
 */
public class RandomFloatFactory implements Factory<Float> {

    /**
     * {@inheritDoc}
     */
    public Float create() {
        return RandomUtils.nextFloat();
    }

}

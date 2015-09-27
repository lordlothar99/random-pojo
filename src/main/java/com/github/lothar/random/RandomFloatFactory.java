/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lothar.random;

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

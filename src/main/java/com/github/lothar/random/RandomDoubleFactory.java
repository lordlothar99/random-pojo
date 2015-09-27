/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lothar.random;

import org.apache.commons.lang.math.RandomUtils;

/**
 * Generateur aleatoire de {@link Double}.
 * 
 * @author Francois Lecomte
 */
public class RandomDoubleFactory implements Factory<Double> {

    /**
     * {@inheritDoc}
     */
    public Double create() {
        return RandomUtils.nextDouble();
    }

}

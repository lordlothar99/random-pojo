/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Generator;

/**
 * Random {@link Double} generator
 * 
 * @author Francois Lecomte
 */
public class RandomDoubleGenerator implements Generator<Double> {

    /**
     * {@inheritDoc}
     */
    public Double create() {
        return RandomUtils.nextDouble();
    }

}

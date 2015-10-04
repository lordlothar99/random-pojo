/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Generator;

/**
 * Random {@link Float} generator
 * 
 * @author Francois Lecomte
 */
public class RandomFloatGenerator implements Generator<Float> {

    /**
     * {@inheritDoc}
     */
    public Float create() {
        return RandomUtils.nextFloat();
    }

}

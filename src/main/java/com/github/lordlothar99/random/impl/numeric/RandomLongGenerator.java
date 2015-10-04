/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Generator;

/**
 * Random {@link Long} generator
 * 
 * @author Francois Lecomte
 */
public class RandomLongGenerator implements Generator<Long> {

    /**
     * {@inheritDoc}
     */
    public Long create() {
        return RandomUtils.nextLong();
    }

}

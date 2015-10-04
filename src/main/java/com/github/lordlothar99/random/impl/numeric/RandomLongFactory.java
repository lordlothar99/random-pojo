/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Factory;

/**
 * Generateur aleatoire de {@link Long}.
 * 
 * @author Francois Lecomte
 */
public class RandomLongFactory implements Factory<Long> {

    /**
     * {@inheritDoc}
     */
    public Long create() {
        return RandomUtils.nextLong();
    }

}

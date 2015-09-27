/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lothar.random;

import org.apache.commons.lang.math.RandomUtils;

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

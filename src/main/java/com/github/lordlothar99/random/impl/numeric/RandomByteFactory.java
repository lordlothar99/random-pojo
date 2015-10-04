/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Factory;

/**
 * Generateur aleatoire de {@link Byte}.
 * 
 * @author Francois Lecomte
 */
public class RandomByteFactory implements Factory<Byte> {

    /**
     * {@inheritDoc}
     */
    public Byte create() {
        return (byte ) RandomUtils.nextInt(Byte.MAX_VALUE);
    }

}

/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import java.math.BigInteger;

import org.apache.commons.lang.math.RandomUtils;

import com.github.lordlothar99.random.api.Generator;

/**
 * Generateur aleatoire de {@link BigInteger}.
 * 
 * @author Francois Lecomte
 */
public class RandomBigIntegerGenerator implements Generator<BigInteger> {

    /**
     * {@inheritDoc}
     */
    public BigInteger create() {
        return new BigInteger(String.valueOf(RandomUtils.nextInt()));
    }

}

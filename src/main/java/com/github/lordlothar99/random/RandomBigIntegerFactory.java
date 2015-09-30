/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random;

import java.math.BigInteger;

import org.apache.commons.lang.math.RandomUtils;

/**
 * Generateur aleatoire de {@link BigInteger}.
 * 
 * @author Francois Lecomte
 */
public class RandomBigIntegerFactory implements Factory<BigInteger> {

    /**
     * {@inheritDoc}
     */
    public BigInteger create() {
        return new BigInteger(String.valueOf(RandomUtils.nextInt()));
    }

}

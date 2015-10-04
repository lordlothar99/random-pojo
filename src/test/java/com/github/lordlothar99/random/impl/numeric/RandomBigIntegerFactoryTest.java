/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link RandomBigIntegerFactory}.
 * 
 * @author Francois Lecomte
 */
public class RandomBigIntegerFactoryTest {

    /**
     * Test method for
     * {@link fr.generali.maestro.framework.core.random.RandomBigIntegerFactory#create()}
     * .
     */
    @Test
    public void test() {
        RandomBigIntegerFactory factory = new RandomBigIntegerFactory();
        BigInteger result = factory.create();
        Assert.assertNotNull(result);
    }

}

/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link RandomBigIntegerGenerator}.
 * 
 * @author Francois Lecomte
 */
public class RandomBigIntegerGeneratorTest {

    /**
     * Test method for
     * {@link RandomBigIntegerGenerator.generali.maestro.framework.core.random.RandomBigIntegerFactory#create()}
     * .
     */
    @Test
    public void test() {
        RandomBigIntegerGenerator factory = new RandomBigIntegerGenerator();
        BigInteger result = factory.create();
        Assert.assertNotNull(result);
    }

}

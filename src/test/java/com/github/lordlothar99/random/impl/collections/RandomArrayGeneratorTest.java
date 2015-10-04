/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.collections;

import org.junit.Assert;
import org.junit.Test;

import com.github.lordlothar99.random.Foo;
import com.github.lordlothar99.random.impl.collections.RandomArrayGenerator;

/**
 * Test for {@link RandomArrayGenerator}.
 * 
 * @author Francois Lecomte
 */
public class RandomArrayGeneratorTest {

    /**
     * Test method for {@link fr.generali.maestro.framework.core.random.RandomArrayGenerator#create()}.
     */
    @Test
    public void testPrimitiveBoolean() {
        RandomArrayGenerator<boolean[]> factory = new RandomArrayGenerator<boolean[]>(boolean[].class);
        boolean[] results = factory.create();
        Assert.assertNotNull("null array", results);
        Assert.assertFalse("empty array", results.length == 0);
    }

    /**
     * Test method for
     * {@link fr.generali.maestro.framework.core.random.RandomArrayGenerator#create()}
     * .
     */
    @Test
    public void testBoolean() {
        RandomArrayGenerator<Boolean[]> factory = new RandomArrayGenerator<Boolean[]>(Boolean[].class);
        Boolean[] results = factory.create();
        Assert.assertNotNull("null array", results);
        Assert.assertFalse("empty array", results.length == 0);
    }

    /**
     * Test method for
     * {@link fr.generali.maestro.framework.core.random.RandomArrayGenerator#create()}
     * .
     */
    @Test
    public void testString() {
        RandomArrayGenerator<String[]> factory = new RandomArrayGenerator<String[]>(String[].class);
        String[] results = factory.create();
        Assert.assertNotNull("null array", results);
        Assert.assertFalse("empty array", results.length == 0);
    }

    /**
     * Test method for
     * {@link fr.generali.maestro.framework.core.random.RandomArrayGenerator#create()}
     * .
     */
    @Test
    public void testFoo() {
        RandomArrayGenerator<Foo[]> factory = new RandomArrayGenerator<Foo[]>(Foo[].class);
        Foo[] results = factory.create();
        Assert.assertNotNull("null array", results);
        Assert.assertFalse("empty array", results.length == 0);
    }

}

/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test for {@link RandomCollectionFactory}.
 * 
 * @author Francois Lecomte
 */
@SuppressWarnings({"unchecked", "rawtypes" })
public class RandomCollectionFactoryTest {

    /**
     * Test method for
     * {@link fr.generali.maestro.framework.core.random.RandomCollectionFactory#create()}
     * .
     */
    @Test
    public void testBoolean() {
        RandomCollectionFactory<List> factory = new RandomCollectionFactory<List>(List.class, Boolean.class);
        List<Boolean> results = factory.create();
        Assert.assertNotNull("null collection", results);
        Assert.assertFalse("empty collection", results.isEmpty());
    }

    /**
     * Test method for
     * {@link fr.generali.maestro.framework.core.random.RandomCollectionFactory#create()}
     * .
     */
    @Test
    public void testFoo() {
        RandomCollectionFactory<List> factory = new RandomCollectionFactory<List>(List.class, Foo.class);
        List<Foo> results = factory.create();
        Assert.assertNotNull("null collection", results);
        Assert.assertFalse("empty collection", results.isEmpty());
    }
}

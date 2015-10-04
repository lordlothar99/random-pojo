/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.collections;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.lordlothar99.random.Foo;

/**
 * Test for {@link RandomCollectionGenerator}.
 * 
 * @author Francois Lecomte
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RandomCollectionGeneratorTest {

	@Test
	public void testBoolean() {
		RandomCollectionGenerator<List> factory = new RandomCollectionGenerator<List>(List.class, Boolean.class);
		List<Boolean> results = factory.create();
		Assert.assertNotNull("null collection", results);
		Assert.assertFalse("empty collection", results.isEmpty());
	}

	@Test
	public void testFoo() {
		RandomCollectionGenerator<List> factory = new RandomCollectionGenerator<List>(List.class, Foo.class);
		List<Foo> results = factory.create();
		Assert.assertNotNull("null collection", results);
		Assert.assertFalse("empty collection", results.isEmpty());
	}
}

/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.lordlothar99.random.Foo;
import com.github.lordlothar99.random.RandomGenerators;

/**
 * Test for {@link RandomCollectionGenerator}.
 * 
 * @author Francois Lecomte
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RandomCollectionGeneratorTest {

	private RandomGenerators generators = new RandomGenerators();

	@Test
	public void should_list_be_specified_length() {
		RandomCollectionGenerator<List> generator = new RandomCollectionGenerator<List>(List.class, Integer.class);
		generator.setSize(20);
		List<Integer> list = generator.create();
		assertEquals(20, list.size());
	}

	@Test
	public void should_list_contain_specified_integers() {
		RandomCollectionGenerator<List> generator = new RandomCollectionGenerator<List>(List.class, Integer.class);
		generator.setSize(20);
		generator.setElementsGenerators(generators.integerGenerator(50, 100));
		List<Integer> list = generator.create();
		assertEquals(20, list.size());
		for (Integer integer : list) {
			assertTrue("integers are not generated correctly : " + integer, integer >= 50 && integer <= 100);
		}
	}

	@Test
	public void testBoolean() {
		RandomCollectionGenerator<List> generator = new RandomCollectionGenerator<List>(List.class, Boolean.class);
		List<Boolean> results = generator.create();
		Assert.assertNotNull("null collection", results);
		Assert.assertFalse("empty collection", results.isEmpty());
	}

	@Test
	public void testFoo() {
		RandomCollectionGenerator<List> generator = new RandomCollectionGenerator<List>(List.class, Foo.class);
		List<Foo> results = generator.create();
		Assert.assertNotNull("null collection", results);
		Assert.assertFalse("empty collection", results.isEmpty());
	}
}

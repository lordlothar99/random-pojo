/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import com.github.lordlothar99.random.Foo;
import com.github.lordlothar99.random.RandomGenerators;

/**
 * Test for {@link RandomArrayGenerator}.
 * 
 * @author Francois Lecomte
 */
public class RandomArrayGeneratorTest {
	
	private RandomGenerators generators = new RandomGenerators();
	@Test
	public void should_array_be_specified_length() {
		RandomArrayGenerator<Integer[]> generator = new RandomArrayGenerator<Integer[]>(Integer[].class);
		generator.setSize(20);
		Integer[] array = generator.create();
		assertEquals(20, array.length);
	}

	@Test
	public void should_array_contain_specified_integers() {
		RandomArrayGenerator<Integer[]> generator = new RandomArrayGenerator<Integer[]>(Integer[].class);
		generator.setSize(20);
		generator.setElementsGenerators(generators.integer(50, 100));
		Integer[] array = generator.create();
		assertEquals(20, array.length);
		for (Integer integer : array) {
			assertTrue("integers are not generated correctly : " + integer, integer >= 50 && integer <= 100);
		}
	}

	@Test
	public void testPrimitiveBoolean() {
		RandomArrayGenerator<boolean[]> factory = new RandomArrayGenerator<boolean[]>(boolean[].class);
		boolean[] results = factory.create();
		Assert.assertNotNull("null array", results);
		Assert.assertFalse("empty array", results.length == 0);
	}

	@Test
	public void testBoolean() {
		RandomArrayGenerator<Boolean[]> factory = new RandomArrayGenerator<Boolean[]>(Boolean[].class);
		Boolean[] results = factory.create();
		Assert.assertNotNull("null array", results);
		Assert.assertFalse("empty array", results.length == 0);
	}

	@Test
	public void testString() {
		RandomArrayGenerator<String[]> factory = new RandomArrayGenerator<String[]>(String[].class);
		String[] results = factory.create();
		Assert.assertNotNull("null array", results);
		Assert.assertFalse("empty array", results.length == 0);
	}

	@Test
	public void testFoo() {
		RandomArrayGenerator<Foo[]> factory = new RandomArrayGenerator<Foo[]>(Foo[].class);
		Foo[] results = factory.create();
		Assert.assertNotNull("null array", results);
		Assert.assertFalse("empty array", results.length == 0);
	}

}

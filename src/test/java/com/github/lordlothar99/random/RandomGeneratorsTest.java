package com.github.lordlothar99.random;

import org.junit.Assert;
import org.junit.Test;

public class RandomGeneratorsTest {

	private RandomGenerators generators = new RandomGenerators();

	@Test
	public void should_return_integer_generator() {
		Assert.assertNotNull(generators.integerGenerator());
	}

	@Test
	public void should_return_string_generator() {
		Assert.assertNotNull(generators.stringGenerator());
	}

	@Test
	public void should_return_foo_generator() {
		Assert.assertNotNull(generators.integerGenerator());
	}

	@Test
	public void should_return_list_generator() {
		Assert.assertNotNull(generators.integerGenerator());
	}

	@Test
	public void should_return_enum_generator() {
		Assert.assertNotNull(generators.integerGenerator());
	}

	@Test
	public void should_return_array_generator() {
		Assert.assertNotNull(generators.integerGenerator());
	}

	@Test
	public void should_return_array_of_objects_generator() {
		Assert.assertNotNull(generators.integerGenerator());
	}
}

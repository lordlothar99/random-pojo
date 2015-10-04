package com.github.lordlothar99.random;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.impl.RandomObjectGenerator;

public class RandomGeneratorsRegistryTest {

	private RandomGeneratorsRegistry registry = new RandomGeneratorsRegistry();

	@Test
	public void should_return_integer_generator() {
		Generator<Integer> generator = registry.getGenerator(Integer.class);
		assertNotNull("generator not found for type '" + Integer.class.getName() + "'", generator);
	}

	@Test
	public void should_return_string_generator() {
		Generator<String> generator = registry.getGenerator(String.class);
		assertNotNull("generator not found for type '" + String.class.getName() + "'", generator);
	}

	@Test
	public void should_return_foo_generator() {
		Generator<Foo> generator = registry.getGenerator(Foo.class);
		assertNotNull("generator not found for type '" + Foo.class.getName() + "'", generator);
		Assert.assertTrue(generator instanceof RandomObjectGenerator);
	}

	@Test
	public void should_return_list_generator() {
		Generator<List> generator = registry.getGenerator(List.class);
		assertNotNull("generator not found for type '" + List.class.getName() + "'", generator);
	}
}

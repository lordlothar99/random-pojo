package com.github.lordlothar99.random;

import java.util.Map;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

import com.github.lordlothar99.random.api.ContainerGenerator;

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
		Assert.assertNotNull(generators.objectGenerator(Foo.class));
	}

	@Test
	public void should_return_list_generator() {
		Assert.assertNotNull(generators.listGenerator(Integer.class));
	}

	@Test
	public void should_return_enum_generator() {
		Assert.assertNotNull(generators.enumGenerator(MyEnum.class));
	}

	@Test
	public void should_return_array_generator() {
		Assert.assertNotNull(generators.arrayGenerator(int[].class));
	}

	@Test
	public void should_return_map_generator() {
		ContainerGenerator<Map<String, Integer>> generator = generators.mapGenerator(String.class, Integer.class);
		Assert.assertNotNull(generator);
		Map<String, Integer> map = generator.create();
		Assert.assertTrue("wrong type", map instanceof Map);
		Assert.assertFalse("empty map", map.isEmpty());
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			Assert.assertTrue(entry.getKey() instanceof String);
			Assert.assertTrue(entry.getValue() instanceof Integer);
		}
	}

	@Test
	public void should_return_treemap_generator() {
		Assert.assertNotNull(generators.mapGenerator(TreeMap.class, String.class, Integer.class));
	}
}

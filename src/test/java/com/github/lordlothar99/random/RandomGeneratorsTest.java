package com.github.lordlothar99.random;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.junit.Assert;
import org.junit.Test;

import com.github.lordlothar99.random.api.ContainerGenerator;
import com.github.lordlothar99.random.api.Generator;

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
		Generator<List<Integer>> generator = generators.listGenerator(Integer.class);
		Assert.assertNotNull(generator);
		List<Integer> list = generator.create();
		Assert.assertTrue("wrong type", list instanceof List);
		Assert.assertFalse("empty map", list.isEmpty());
		for (Integer entry : list) {
			Assert.assertTrue(entry instanceof Integer);
		}
	}

	@Test
	public void should_return_set_generator() {
		Generator<Set<Integer>> generator = generators.setGenerator(Integer.class);
		Assert.assertNotNull(generator);
		Set<Integer> set = generator.create();
		Assert.assertTrue("wrong type", set instanceof Set);
		Assert.assertFalse("empty map", set.isEmpty());
		for (Integer entry : set) {
			Assert.assertTrue(entry instanceof Integer);
		}
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
		ContainerGenerator<TreeMap<String, Integer>> generator = generators.treemapGenerator(String.class,
				Integer.class);
		Map<String, Integer> map = generator.create();
		Assert.assertTrue("wrong type", map instanceof TreeMap);
		Assert.assertFalse("empty map", map.isEmpty());
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			Assert.assertTrue(entry.getKey() instanceof String);
			Assert.assertTrue(entry.getValue() instanceof Integer);
		}
	}
}

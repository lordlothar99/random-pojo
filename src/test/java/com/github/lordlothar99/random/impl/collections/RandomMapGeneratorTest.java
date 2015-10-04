package com.github.lordlothar99.random.impl.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import com.github.lordlothar99.random.RandomGenerators;
import com.github.lordlothar99.random.api.ContainerGenerator;
import com.github.lordlothar99.random.api.Generator;
import com.github.lordlothar99.random.api.RangedGenerator;

public class RandomMapGeneratorTest {

	private RandomGenerators generators = new RandomGenerators();

	@Test
	public void should_map_be_specified_length() {
		ContainerGenerator<Map<String, Integer>> generator = generators.mapGenerator(String.class, Integer.class, 20);
		Map<String, Integer> map = generator.create();
		assertEquals(20, map.size());
	}

	@Test
	public void should_map_contain_specified_integers() {
		RangedGenerator<Integer> integerGenerator = generators.integerGenerator(50, 100);
		Generator<String> stringGenerator = generators.stringGenerator();
		ContainerGenerator<Map<String, Integer>> generator = generators.mapGenerator(stringGenerator, integerGenerator,
				20);
		Map<String, Integer> map = generator.create();
		assertEquals(20, map.size());
		for (Entry<String, Integer> entry : map.entrySet()) {
			Integer integer = entry.getValue();
			assertTrue("integers are not generated correctly : " + integer, integer >= 50 && integer <= 100);
		}
	}

	@Test
	public void testBoolean() {
		ContainerGenerator<Map<String, Boolean>> generator = generators.mapGenerator(String.class, Boolean.class);
		Map<String, Boolean> results = generator.create();
		Assert.assertNotNull("null map", results);
		Assert.assertFalse("empty map", results.size() == 0);
	}

	@Test
	public void testString() {
		ContainerGenerator<Map<String, String>> generator = generators.mapGenerator(String.class, String.class);
		Map<String, String> results = generator.create();
		Assert.assertNotNull("null map", results);
		Assert.assertFalse("empty map", results.size() == 0);
	}

}

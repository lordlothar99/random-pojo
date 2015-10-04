package com.github.lordlothar99.random.impl.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Test;

import com.github.lordlothar99.random.RandomGenerators;

public class RandomMapGeneratorTest {

	private RandomGenerators generators = new RandomGenerators();

	@Test
	public void should_map_be_specified_length() {
		RandomMapGenerator<Map<String, Integer>> generator = new RandomMapGenerator<Map<String, Integer>>(
				(Class<? extends Map<String, Integer>>) HashMap.class);
		generator.setSize(20);
		generator.setElementsTypes(String.class, Integer.class);
		Map<String, Integer> map = generator.create();
		assertEquals(20, map.size());
	}

	@Test
	public void should_map_contain_specified_integers() {
		RandomMapGenerator<Map<String, Integer>> generator = new RandomMapGenerator<Map<String, Integer>>(
				(Class<? extends Map<String, Integer>>) HashMap.class);
		generator.setSize(20);
		generator.setElementsTypes(String.class, null);
		generator.setElementsGenerators(null, generators.integer(50, 100));
		Map<String, Integer> map = generator.create();
		assertEquals(20, map.size());
		for (Entry<String, Integer> entry : map.entrySet()) {
			Integer integer = entry.getValue();
			assertTrue("integers are not generated correctly : " + integer, integer >= 50 && integer <= 100);
		}
	}

	@Test
	public void testBoolean() {
		RandomMapGenerator<Map<String, Boolean>> generator = new RandomMapGenerator<Map<String, Boolean>>(
				(Class<? extends Map<String, Boolean>>) HashMap.class, String.class, Boolean.class);
		Map<String, Boolean> results = generator.create();
		Assert.assertNotNull("null map", results);
		Assert.assertFalse("empty map", results.size() == 0);
	}

	@Test
	public void testString() {
		RandomMapGenerator<Map<String, String>> generator = new RandomMapGenerator<Map<String, String>>(
				(Class<? extends Map<String, String>>) HashMap.class, String.class, String.class);
		Map<String, String> results = generator.create();
		Assert.assertNotNull("null map", results);
		Assert.assertFalse("empty map", results.size() == 0);
	}

}

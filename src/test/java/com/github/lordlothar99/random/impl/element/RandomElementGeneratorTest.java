package com.github.lordlothar99.random.impl.element;

import static org.apache.commons.lang.ArrayUtils.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.github.lordlothar99.random.RandomToolkit;

public class RandomElementGeneratorTest {

	private RandomToolkit randomToolkit = new RandomToolkit();

	@Test
	public void should_a_randomly_picked_element_be_returned_from_array_of_ints() {
		int[] anArray = randomToolkit.array(int[].class);
		int element = randomToolkit.element(anArray);
		assertNotNull(element);
		assertTrue("Element is not in container", contains(anArray, element));
	}

	@Test
	public void should_a_randomly_picked_element_be_returned_from_array_of_strings() {
		String[] anArray = randomToolkit.array(String[].class);
		String element = randomToolkit.element(anArray);
		assertNotNull(element);
		assertTrue("Element is not in container", contains(anArray, element));
	}

	@Test
	public void should_a_randomly_picked_element_be_returned_from_map() {
		Map<String, Integer> map = randomToolkit.map(String.class, Integer.class);
		Entry<String, Integer> element = randomToolkit.element(map);
		assertNotNull(element);
		Integer value = map.get(element.getKey());
		assertEquals("Element is not in container", element.getValue(), value);
	}

}

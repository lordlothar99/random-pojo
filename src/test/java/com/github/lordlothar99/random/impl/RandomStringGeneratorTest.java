package com.github.lordlothar99.random.impl;

import static org.apache.commons.lang.StringUtils.containsAny;
import static org.apache.commons.lang.StringUtils.containsOnly;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RandomStringGeneratorTest {

	@Test
	public void should_random_string_be_not_null() {
		RandomStringGenerator randomGenerator = new RandomStringGenerator();
		String result = randomGenerator.create();
		assertNotNull(result);
	}

	@Test
	public void should_random_string_be_specified_length() {
		RandomStringGenerator randomGenerator = new RandomStringGenerator(50);
		String randomString = randomGenerator.create();
		assertEquals(50, randomString.length());
	}

	@Test
	public void should_random_string_contain_letters_only() {
		RandomStringGenerator randomGenerator = new RandomStringGenerator(50, true, false);
		String randomString = randomGenerator.create();
		assertFalse(containsAny(randomString, "0123456789"));
	}

	@Test
	public void should_random_string_contain_numbers_only() {
		RandomStringGenerator randomGenerator = new RandomStringGenerator(50, false, true);
		String randomString = randomGenerator.create();
		assertTrue(containsOnly(randomString, "0123456789"));
	}

	@Test
	public void should_random_string_contain_specified_chars_only() {
		RandomStringGenerator randomGenerator = new RandomStringGenerator(50, new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' });
		String randomString = randomGenerator.create();
		assertTrue(containsOnly(randomString, "ABCDEFGH"));
	}
}

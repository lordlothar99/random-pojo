package com.github.lordlothar99.random.impl.string;

import static org.apache.commons.lang.StringUtils.containsAny;
import static org.apache.commons.lang.StringUtils.containsOnly;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.CharUtils;
import org.junit.Test;

import com.github.lordlothar99.random.impl.string.RandomCharacterGenerator;

public class RandomCharacterGeneratorTest {

	@Test
	public void should_random_character_be_not_null() {
		RandomCharacterGenerator randomGenerator = new RandomCharacterGenerator();
		char result = randomGenerator.create();
		assertNotNull(result);
	}

	@Test
	public void should_random_character_contain_letters_only() {
		RandomCharacterGenerator randomGenerator = new RandomCharacterGenerator(true, false);
		char result = randomGenerator.create();
		assertFalse(containsAny(CharUtils.toString(result), "0123456789"));
	}

	@Test
	public void should_random_character_contain_numbers_only() {
		RandomCharacterGenerator randomGenerator = new RandomCharacterGenerator(false, true);
		char result = randomGenerator.create();
		assertTrue(containsOnly(CharUtils.toString(result), "0123456789"));
	}

	@Test
	public void should_random_character_contain_specified_chars_only() {
		RandomCharacterGenerator randomGenerator = new RandomCharacterGenerator(
				new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' });
		char result = randomGenerator.create();
		assertTrue(containsOnly(CharUtils.toString(result), "ABCDEFGH"));
	}
}

/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl;

import static org.apache.commons.lang.RandomStringUtils.random;

import org.apache.commons.lang.ArrayUtils;

/**
 * Random {@link String} generator
 * 
 * @author Francois Lecomte
 */
public class RandomStringGenerator extends AbstractGenerator<String> {

	private int length;
	private boolean letters;
	private boolean numbers;
	private char[] chars;

	public RandomStringGenerator() {
		this(10, true, true);
	}

	public RandomStringGenerator(int length) {
		this(length, true, true);
	}

	public RandomStringGenerator(int length, boolean letters, boolean numbers) {
		this.length = length;
		this.letters = letters;
		this.numbers = numbers;
	}

	public RandomStringGenerator(int length, char[] chars) {
		this(length, true, true);
		this.chars = chars;
	}

	public int length() {
		return length;
	}

	public boolean letters() {
		return letters;
	}

	public boolean numbers() {
		return numbers;
	}

	public char[] chars() {
		return chars;
	}

	public String create() {
		String randomString = random(length, 0, chars == null ? 0 : chars.length, letters, numbers, chars);
		logger.info("Generated string '{}' under constraints : ", randomString, constraints());
		return randomString;
	}

	private String constraints() {
		String string = "length=" + length + " ; letters=" + letters + " ; numbers=" + numbers;
		if (chars != null) {
			string += " ; chars=" + ArrayUtils.toString(chars);
		}
		return string;
	}
}

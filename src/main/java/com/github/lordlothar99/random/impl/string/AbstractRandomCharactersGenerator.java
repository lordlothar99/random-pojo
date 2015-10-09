/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.string;

import static org.apache.commons.lang.RandomStringUtils.random;

import org.apache.commons.lang.ArrayUtils;

import com.github.lordlothar99.random.impl.AbstractGenerator;

/**
 * Random {@link Character} generator
 *
 * @author Francois Lecomte
 */
public abstract class AbstractRandomCharactersGenerator<T> extends AbstractGenerator<T> {

	private boolean letters;
	private boolean numbers;
	private char[] chars;

	public AbstractRandomCharactersGenerator(Class<T> type) {
		this(type, true, true);
	}

	public AbstractRandomCharactersGenerator(Class<T> type, char[] chars) {
		this(type, true, true);
		this.chars = chars;
	}

	public AbstractRandomCharactersGenerator(Class<T> type, boolean letters, boolean numbers) {
		super(type);
		this.letters = letters;
		this.numbers = numbers;
	}

	public boolean isLetters() {
		return letters;
	}

	public void setLetters(boolean letters) {
		this.letters = letters;
	}

	public boolean isNumbers() {
		return numbers;
	}

	public void setNumbers(boolean numbers) {
		this.numbers = numbers;
	}

	public char[] getChars() {
		return chars;
	}

	public void setChars(char[] chars) {
		this.chars = chars;
	}

	protected String createString() {
		String randomChar = random(getLength(), 0, chars == null ? 0 : chars.length, letters, numbers, chars);
		logger.info("Generated string '{}' under constraints : {}", randomChar, constraints());
		return randomChar;
	}

	protected abstract int getLength();

	protected String constraints() {
		String string = "letters=" + letters + " ; numbers=" + numbers;
		if (chars != null) {
			string += " ; chars=" + ArrayUtils.toString(chars);
		}
		return string;
	}
}

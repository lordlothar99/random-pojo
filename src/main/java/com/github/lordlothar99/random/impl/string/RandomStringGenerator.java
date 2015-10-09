/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.string;

/**
 * Random {@link String} generator
 *
 * @author Francois Lecomte
 */
public class RandomStringGenerator extends AbstractRandomCharactersGenerator<String> {

	private int length = 10;

	public RandomStringGenerator() {
		super(String.class);
	}

	public RandomStringGenerator(int length) {
		super(String.class);
		this.length = length;
	}

	public RandomStringGenerator(char[] chars) {
		super(String.class, chars);
	}

	public RandomStringGenerator(int length, char[] chars) {
		super(String.class, chars);
		this.length = length;
	}

	public RandomStringGenerator(boolean letters, boolean numbers) {
		super(String.class, letters, numbers);
	}

	public RandomStringGenerator(int length, boolean letters, boolean numbers) {
		super(String.class, letters, numbers);
		this.length = length;
	}

	public RandomStringGenerator(Case letterCase) {
		super(String.class, letterCase);
	}

	public RandomStringGenerator(int length, Case letterCase) {
		super(String.class, letterCase);
		this.length = length;
	}

	public RandomStringGenerator(char[] chars, Case letterCase) {
		super(String.class, chars, letterCase);
	}

	public RandomStringGenerator(int length, char[] chars, Case letterCase) {
		super(String.class, chars, letterCase);
		this.length = length;
	}

	public RandomStringGenerator(boolean letters, boolean numbers, Case letterCase) {
		super(String.class, letters, numbers, letterCase);
	}

	public RandomStringGenerator(int length, boolean letters, boolean numbers, Case letterCase) {
		super(String.class, letters, numbers, letterCase);
		this.length = length;
	}

	@Override
	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String create() {
		String randomString = createString();
		return randomString;
	}

	@Override
	protected String constraints() {
		return "length=" + length + " ; " + super.constraints();
	}
}

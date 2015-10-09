/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.string;

/**
 * Random {@link Character} generator
 *
 * @author Francois Lecomte
 */
public class RandomCharacterGenerator extends AbstractRandomCharactersGenerator<Character> {

	public RandomCharacterGenerator() {
		super(Character.class);
	}

	public RandomCharacterGenerator(char[] chars) {
		super(Character.class, chars);
	}

	public RandomCharacterGenerator(boolean letters, boolean numbers) {
		super(Character.class, letters, numbers);
	}

	public Character create() {
		String randomString = createString();
		return randomString.charAt(0);
	}

	@Override
	protected int getLength() {
		return 1;
	}
}

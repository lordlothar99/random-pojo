package com.github.lordlothar99.random.impl.string;

public class RandomEmailGenerator extends RandomStringGenerator {

	public RandomEmailGenerator() {
		super(Case.LOWER);
		checkLength();
	}

	public RandomEmailGenerator(boolean letters, boolean numbers) {
		super(letters, numbers, Case.LOWER);
		checkLength();
	}

	public RandomEmailGenerator(char[] chars) {
		super(chars, Case.LOWER);
		checkLength();
	}

	public RandomEmailGenerator(int length) {
		super(length, Case.LOWER);
		checkLength();
	}

	public RandomEmailGenerator(int length, boolean letters, boolean numbers) {
		super(length, letters, numbers, Case.LOWER);
		checkLength();
	}

	public RandomEmailGenerator(int length, char[] chars) {
		super(length, chars, Case.LOWER);
		checkLength();
	}

	private void checkLength() {
		if (getLength() < 6) {
			throw new RuntimeException("Minimum length is 6 characters");
		}
	}

	@Override
	public String create() {
		String string = super.create();

		String extension = string.substring(0, 2);
		string = string.substring(3);

		int half = string.length() / 2;
		String username = string.substring(0, half);
		String domain = string.substring(half + 1);

		String email = username + "@" + domain + "." + extension;
		logger.info("Generated email : {}", email);
		return email;
	}
}

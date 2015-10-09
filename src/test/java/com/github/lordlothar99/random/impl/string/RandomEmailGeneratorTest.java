package com.github.lordlothar99.random.impl.string;

import org.junit.Test;

public class RandomEmailGeneratorTest {

	@Test(expected = RuntimeException.class)
	public void should_email_be_at_least_6_chars() {
		new RandomEmailGenerator(5);
	}

	@Test
	public void should_email_be_generated() {
		RandomEmailGenerator generator = new RandomEmailGenerator();
		generator.create();
	}

}

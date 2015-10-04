/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

/**
 * Test for {@link RandomShortGenerator}.
 * 
 * @author Francois Lecomte
 */
public class RandomShortGeneratorTest extends AbstractRandomNumericGeneratorTest<Short, RandomShortGenerator> {

	@Override
	protected RandomShortGenerator newGenerator() {
		return new RandomShortGenerator();
	}

	@Override
	protected Short max() {
		return 15298;
	}

	@Override
	protected Short min() {
		return -5665;
	}
}

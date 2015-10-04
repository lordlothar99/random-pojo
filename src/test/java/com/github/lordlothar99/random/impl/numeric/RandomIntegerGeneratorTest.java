/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

/**
 * Test for {@link RandomIntegerGenerator}.
 * 
 * @author Francois Lecomte
 */
public class RandomIntegerGeneratorTest extends AbstractRandomNumericGeneratorTest<Integer, RandomIntegerGenerator> {

	@Override
	protected RandomIntegerGenerator newGenerator() {
		return new RandomIntegerGenerator();
	}

	@Override
	protected Integer max() {
		return 15298;
	}

	@Override
	protected Integer min() {
		return -5665;
	}
}

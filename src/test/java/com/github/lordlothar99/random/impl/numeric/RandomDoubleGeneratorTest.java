/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

/**
 * Test for {@link RandomDoubleGenerator}.
 * 
 * @author Francois Lecomte
 */
public class RandomDoubleGeneratorTest extends AbstractRandomNumericGeneratorTest<Double, RandomDoubleGenerator> {

	@Override
	protected RandomDoubleGenerator newGenerator() {
		return new RandomDoubleGenerator();
	}

	@Override
	protected Double max() {
		return 15298.652168375d;
	}

	@Override
	protected Double min() {
		return -5665.856d;
	}
}

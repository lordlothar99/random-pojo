/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

/**
 * Test for {@link RandomFloatGenerator}.
 * 
 * @author Francois Lecomte
 */
public class RandomFloatGeneratorTest extends AbstractRandomNumericGeneratorTest<Float, RandomFloatGenerator> {

	@Override
	protected RandomFloatGenerator newGenerator() {
		return new RandomFloatGenerator();
	}

	@Override
	protected Float max() {
		return 15298.652168375f;
	}

	@Override
	protected Float min() {
		return -5665.856f;
	}
}

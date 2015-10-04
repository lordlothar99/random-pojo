/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

/**
 * Test for {@link RandomByteGenerator}.
 * 
 * @author Francois Lecomte
 */
public class RandomByteGeneratorTest extends AbstractRandomNumericGeneratorTest<Byte, RandomByteGenerator> {

	@Override
	protected RandomByteGenerator newGenerator() {
		return new RandomByteGenerator();
	}

	@Override
	protected Byte max() {
		return Byte.MAX_VALUE;
	}

	@Override
	protected Byte min() {
		return Byte.MIN_VALUE;
	}
}

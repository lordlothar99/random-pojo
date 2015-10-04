/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

/**
 * Test for {@link RandomLongGenerator}.
 * 
 * @author Francois Lecomte
 */
public class RandomLongGeneratorTest extends AbstractRandomNumericGeneratorTest<Long, RandomLongGenerator> {

	@Override
	protected RandomLongGenerator newGenerator() {
		return new RandomLongGenerator();
	}

	@Override
	protected Long max() {
		return 15298L;
	}

	@Override
	protected Long min() {
		return -5665L;
	}
}

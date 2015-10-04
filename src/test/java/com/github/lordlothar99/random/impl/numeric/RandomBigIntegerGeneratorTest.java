/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random.impl.numeric;

import java.math.BigInteger;

/**
 * Test for {@link RandomBigIntegerGenerator}.
 * 
 * @author Francois Lecomte
 */
public class RandomBigIntegerGeneratorTest
		extends AbstractRandomNumericGeneratorTest<BigInteger, RandomBigIntegerGenerator> {

	@Override
	protected RandomBigIntegerGenerator newGenerator() {
		return new RandomBigIntegerGenerator();
	}

	@Override
	protected BigInteger max() {
		return new BigInteger("1526575");
	}

	@Override
	protected BigInteger min() {
		return new BigInteger("-56856");
	}
}

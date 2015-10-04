package com.github.lordlothar99.random.impl.numeric;

import java.math.BigDecimal;

public class RandomBigDecimalGeneratorTest
		extends AbstractRandomNumericGeneratorTest<BigDecimal, RandomBigDecimalGenerator> {

	@Override
	protected RandomBigDecimalGenerator newGenerator() {
		return new RandomBigDecimalGenerator();
	}

	@Override
	protected BigDecimal max() {
		return new BigDecimal("152.6575");
	}

	@Override
	protected BigDecimal min() {
		return new BigDecimal("-56.856");
	}
}
